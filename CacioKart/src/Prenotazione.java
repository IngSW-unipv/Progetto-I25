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
    private double costo;
    private String prenotazioniConcorrenti;

    public void prenotazione(String cf, String tipologia, LocalDate dataGara, LocalTime fasciaOraria, Socket clientSocket) throws SQLException {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        //Random random = new Random();

        //idP dataG fasciaO tipologia costo numP socio
        //Per mettere una nuova prenotazione devo trovare l'id massimo e capire se ci sono già 20 prenotazioni

        //g = new Gara("0",null); //Non serve

        SELECT = "SELECT count(*) FROM caciokart.prenotazione WHERE dataG = '"
                + dataGara + "' AND fasciaO = '"
                + fasciaOraria + "'";
        result = db.executeReturnQuery(SELECT);
        prenotazioniConcorrenti = result.get(0).toString().replaceAll("\\D", "");

        if(prenotazioniConcorrenti.equals("20")){
            System.out.println("Nessun posto disponibile\n!");
            responder.sendResponse(clientSocket, "0");
            return;
        }

        /*
        // Serve a capire se esiste già almeno una prenotazione
        if(result == null || result.isEmpty()){
            nPartecipanti = 0;
        } else {
            nPartecipanti = Integer.parseInt(result.get(0).toString().replace("\"", ""));
        }*/

        // Stabilisco l'id della prenotazione basandomi su se esiste o meno già almeno una prenotazione
        SELECT = "SELECT MAX(idP) FROM PRENOTAZIONE";
        result = db.executeReturnQuery(SELECT);
        idPrenotazione = result.toString().replaceAll("\\D", "");

        if (!idPrenotazione.equals("0")/*result != null && !result.isEmpty() && result.get(0) != null*/) {
            idPrenotazione = String.valueOf(Integer.parseInt(idPrenotazione) + 1);

        } else {
            idPrenotazione = "1"; // Se non ci sono prenotazioni, partiamo da 1
        }

        costo = 15;
        INSERT = "INSERT INTO prenotazione (idP, dataG , fasciaO, tipologia, costo, socio) VALUES('" +
                idPrenotazione + "', '" +
                dataGara +"', '" +
                fasciaOraria +"', '" +
                tipologia + "', '" +
                costo + "', '" +
                cf + "')";

        queryIndicator = db.executeUpdateQuery(INSERT);
        responder.sendResponse(clientSocket,Integer.toString(queryIndicator));
        System.out.println("Prenotazione avvenuta con successo\n");

        //Controllo strano
        /*
        if(nPartecipanti >= 1 && nPartecipanti < MAX){

            //Generare un prezzo casuale
            costo = 30 + (random.nextDouble() * (50 - 30)); // Genera un numero tra 30 e 50
            //System.out.printf("Prezzo generato: %.2f €%n", costo);

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
            System.out.println("Prenotazione avvenuta con successo\n");
        }else{
            System.out.println("Nessun posto disponibile\n!");
        }*/

        /*
        //Query non necessaria, ho già il numero di utenti presenti in quella fascia in quel giorno
        //conteggio degli utenti che vogliono fare la gara in quel giorno in quella ora
        SELECT = "SELECT count(*) FROM caciokart.prenotazione WHERE dataG = '"
                + dataGara + "' AND fasciaO= '"
                + fasciaOraria + "'";
        result = db.executeReturnQuery(SELECT);

        // Controllo per evitare errori di null o lista vuota
        if (result != null || !result.isEmpty() || result.get(0)!= null) {
            nPartecipanti =Integer.parseInt(result.get(0).toString().replaceAll("\\D", ""));

        } else {
            nPartecipanti = 0; // Se il risultato è nullo o vuoto, impostiamo 0
        }*/


        // Una volta stabiliti i partecipanti
        /*
        if(nPartecipanti > 1 && nPartecipanti < MAX){
            //creazione della gara
            switch (tipologia){

                case "secca":
                    SELECT="SELECT MAX(idG) from garaS";
                    result = db.executeReturnQuery(SELECT);
                    if(result != null || !result.isEmpty() || result.get(0)!= null) {
                        idGara = result.get(0).toString().replaceAll("\\D", "");
                        idGara=String.valueOf(Integer.parseInt(idGara)+1);
                    }else{
                        idGara = "1";
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
        }*/

    }


}
