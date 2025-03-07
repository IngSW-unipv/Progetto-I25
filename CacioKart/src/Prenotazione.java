import java.net.Socket;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Prenotazione {
    private final int MAX=20;
    private String SELECT;
    private String INSERT;
    private DBConnector db;
    private PHPResponseHandler responder;
    private int queryIndicator;
    private List<Map<String, Object>> result;
    private Gara g;
    private String idPrenotazione;
    private String idGara;
    private int nPartecipanti;
    private double costo;

    public void prenotazione(String cf, String tipologia, LocalDate dataGara, LocalTime fasciaOraria, Socket clientSocket) throws SQLException {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        Random random = new Random();
        nPartecipanti = 0;
        costo = 0;

        g = new Gara("0",null); //Da rivedere

        SELECT = "SELECT count(*) FROM caciokart.prenotazione WHERE dataG = '"
                + dataGara + "' AND fasciaO = '"
                + fasciaOraria + "'";

        result = db.executeReturnQuery(SELECT);

        if(result == null|| result.isEmpty()){
            nPartecipanti = 0;
        } else {
            nPartecipanti =Integer.parseInt(result.get(0).toString().replace("\"", ""));
        }
        if (nPartecipanti!=0) {
            String SELECT = "SELECT MAX(idP) FROM PRENOTAZIONE";
            result = db.executeReturnQuery(SELECT);
            if (result != null && !result.isEmpty() && result.get(0) != null) {
                idPrenotazione = result.get(0).toString().replaceAll("\\D", "");
                idPrenotazione = String.valueOf(Integer.parseInt(idPrenotazione )+1) ;
            } else {
                idPrenotazione = "1"; // Se non ci sono prenotazioni, partiamo da 1
            }
        }
        if(nPartecipanti>1 && nPartecipanti<MAX){
            do{
                costo = 30 + (random.nextDouble() * (50 - 30)); // Genera un numero tra 30 e 50
                if(costo<30||costo>50){
                    System.out.println("Impossibile generare il prezzo\n");
                }else {
                    System.out.printf("Prezzo generato: %.2f €%n", costo);
                }
            }while(costo<30||costo>50);

            INSERT = "INSERT INTO prenotazione (idP, dataG , fasciaO, tipologia, costo, numP,socio) VALUES('" +
                    idPrenotazione + "', '" +
                    dataGara +"', '" +
                    fasciaOraria +"', '" +
                    tipologia + "', '" +
                    costo + "', '" +
                    1 + "', '" +
                    cf + "')";
            queryIndicator = db.executeUpdateQuery(INSERT);
            responder.sendResponse(clientSocket,Integer.toString(queryIndicator));
            System.out.println("prenotazione avvenuta con successo\n");
        }else{
            System.out.println("Nessun posto disponibile\n!");
        }
        //conteggio degli utenti che vogliono fare la gara in quel giorno in quella ora
        SELECT = "SELECT count(*) FROM caciokart.prenotazione WHERE dataG = '"
                + dataGara + "' AND fasciaO= '"
                + fasciaOraria + "'";
        result = db.executeReturnQuery(SELECT);
//      Controllo per evitare errori di null o lista vuota
        if (result != null || !result.isEmpty() || result.get(0)!= null) {
            nPartecipanti =Integer.parseInt(result.get(0).toString().replaceAll("\\D", ""));
        } else {
            nPartecipanti = 0; // Se il risultato è nullo o vuoto, impostiamo 0
        }
        if(nPartecipanti>1 && nPartecipanti<MAX){
            //creazione della gara
            switch (tipologia){
                case "secca":
                    SELECT="SELECT MAX(idG) from garaS";
                    result = db.executeReturnQuery(SELECT);
                    if(result != null || !result.isEmpty() || result.get(0)!= null) {
                        idGara = result.get(0).toString().replaceAll("\\D", "");
                        idGara=String.valueOf(Integer.parseInt(idGara)+1);
                    }else{
                        idGara="1";
                    }
                    g.setIdGara(idGara);
                    g.setOra(fasciaOraria);

                    INSERT = "INSERT INTO garaS (idGara, ora, nPart, btempo, idC, idP) VALUES('" +
                            g.getIdGara() + "', '" +
                            g.getOra() + "', '" +
                            nPartecipanti + "', '" +
                            null + "', '" +
                            null + "', '" +
                            idPrenotazione + "')";

                    queryIndicator = db.executeUpdateQuery(INSERT);
                    responder.sendResponse(clientSocket, Integer.toString(queryIndicator));
                    break;

                case "libera":
                    SELECT="SELECT MAX(idG) from garaL";
                    result = db.executeReturnQuery(SELECT);
                    if(result != null || !result.isEmpty() || result.get(0) != null) {
                        idGara =result.get(0).toString().replaceAll("\\D", "");
                        idGara=String.valueOf(Integer.parseInt(idGara)+1);
                    }else{
                        idGara="1";
                    }
                    g.setIdGara(idGara);
                    g.setOra(fasciaOraria);

                    INSERT="INSERT INTO garaL (idGara, ora, idC, idP) VALUES('" +
                            g.getIdGara() + "', '" +
                            g.getOra() + "', '" +
                            null + "', '" +
                            idPrenotazione + "')";

                    queryIndicator = db.executeUpdateQuery(INSERT);
                    responder.sendResponse(clientSocket, Integer.toString(queryIndicator));
                    break;

            }
        }else{
            System.out.println("Gara non disputata\n!");
        }

    }


}
