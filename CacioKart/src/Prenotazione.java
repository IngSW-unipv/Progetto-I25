import java.net.Socket;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Prenotazione {
    private final int MAX=20;
    private String SELECT;
    private String INSERT;
    LocalDate dataG;
    LocalTime orarioI;
    LocalTime orarioF;
    private DBConnector db;
    private PHPResponseHandler responder;
    private int queryIndicator;
    private List<Map<String, Object>> result;
    private Gara g;

    public void prenotation(String tipologia,LocalDate dataGara, LocalTime oraI,LocalTime oraF, Socket clientSocket) throws SQLException {
        db = new DBConnector();
        String idP = "",idG;
        int nPartecipanti = 0;
        double costo = 0;
        g = new Gara("0",null);
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        responder = new PHPResponseHandler();
        SELECT = "SELECT count(*) as count FROM caciokart.prenotazione WHERE dataG = '"
                + dataGara + "' AND fasciaO= '"
                + oraI + "'";
        result = db.executeReturnQuery(SELECT);
        if(result == null|| result.isEmpty()){
            nPartecipanti = 0;
        } else {
            nPartecipanti = Integer.parseInt(String.valueOf(result.get(0).get("count")));
        }

        if (nPartecipanti == 0) {
            nPartecipanti++;
        } else {
            String SELECT = "SELECT COALESCE(MAX(idP), 0) as max FROM PRENOTAZIONE";
            result = db.executeReturnQuery(SELECT);
            // Controlla che il risultato non sia null o vuoto
            if (result != null && !result.isEmpty() && result.get(0).get("max") != null) {
                idP =result.get(0).toString();
            } else {
                idP="0"; // Se non ci sono prenotazioni, partiamo da 0
            }
            idP =String.valueOf(Integer.parseInt(idP + 1)) ; // Incrementa l'ID per il nuovo record
        }
        if(nPartecipanti < MAX){
            do{
                costo = 30 + (random.nextDouble() * (50 - 30)); // Genera un numero tra 30 e 50
                if(costo<30||costo>50){
                    System.out.println("Impossibile generare il prezzo\n");
                }else {
                    System.out.printf("Prezzo generato: %.2f €%n", costo);
                }
            }while(costo<30||costo>50);

            INSERT = "INSERT INTO prenotazione (idP, dataG , fasciaO, tipologia, costo, numP) VALUES('" +
                    idP + "', '" +
                    dataGara +"', '" +
                    oraI +"', '" +
                    tipologia + "', '" +
                    costo +"', '" +
                    1 + "')";

            queryIndicator = db.executeUpdateQuery(INSERT);
            responder.sendResponse(clientSocket, Integer.toString(queryIndicator));
            System.out.println("La prenotazione della gara libera\n");
        }else{
            System.out.println("Nessun posto disponibile\n!");
        }
        //conteggio degli utenti che vogliono fare la gara in quel giorno in quella ora
        SELECT = "SELECT count(*) as count FROM caciokart.prenotazione WHERE dataG = '"
                + dataGara + "' AND fasciaO= '"
                + oraI + "'";
        result = db.executeReturnQuery(SELECT);
//      Controllo per evitare errori di null o lista vuota
        if (result != null || !result.isEmpty() || result.get(0).get("count") != null) {
            nPartecipanti = Integer.parseInt(String.valueOf(result.get(0)));
        } else {
            nPartecipanti = 0; // Se il risultato è nullo o vuoto, impostiamo 0
        }

        if(nPartecipanti >= 1 || nPartecipanti < MAX){
            //creazione della gara
            switch (tipologia){

                case "secca":
                    SELECT="SELECT MAX(idG) as max from garaS";
                    result = db.executeReturnQuery(SELECT);
                    if(result != null && !result.isEmpty() && result.get(0).get("max") != null) {
                        idG = String.valueOf(result.get(0).get("max"));
                        idG=String.valueOf(Integer.parseInt(idG+1));
                    }else{
                        idG="1";
                    }
                    g.setIdGara(idG);
                    g.setOra(oraI);

                    INSERT = "INSERT INTO garaS (idGara, ora, nPart, btempo, idC, idP) VALUES('" +
                            g.getIdGara() + "', '" +
                            g.getOra() + "', '" +
                            nPartecipanti + "', '" +
                            null + "', '" +
                            null + "', '" +
                            idP + "')";

                    queryIndicator = db.executeUpdateQuery(INSERT);
                    responder.sendResponse(clientSocket, Integer.toString(queryIndicator));
                    break;

                case "libera":
                    SELECT="SELECT MAX(idG) as max from garaL";
                    result = db.executeReturnQuery(SELECT);
                    if(result != null && !result.isEmpty() && result.get(0).get("max") != null) {
                        idG =String.valueOf(result.get(0).get("max"));
                        idG=String.valueOf(Integer.parseInt(idG+1));
                    }else{
                        idG="1";
                    }
                    g.setIdGara(idG);
                    g.setOra(oraI);

                    INSERT="INSERT INTO garaL (idGara, ora, idC, idP) VALUES('" +
                            g.getIdGara() + "', '" +
                            g.getOra() + "', '" +
                            null + "', '" +
                            idP + "')";

                    queryIndicator = db.executeUpdateQuery(INSERT);
                    responder.sendResponse(clientSocket, Integer.toString(queryIndicator));
                    break;
            }
        }else{
            System.out.println("Gara non disputata\n!");
        }

    }


}
