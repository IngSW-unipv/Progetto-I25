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
        int idP  = 0,idG  = 0, nPartecipanti = 0;
        double costo = 0;
        g = new Gara(0,null);
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        responder = new PHPResponseHandler();
        SELECT = "SELECT count(*) FROM caciokart.prenotazioni WHERE dataG = '"
                + dataGara + "' AND fasciaO= '"
                + oraI + "'";
        result = db.executeReturnQuery(SELECT);

        if(result == null){
            nPartecipanti = 0;
            idP=idP+1;
        } else {
            nPartecipanti = Integer.parseInt(result.get(0).toString());
        }

        if(nPartecipanti < MAX){
            SELECT = "SELECT MAX(idP) from PRENOTAZIONI";
            result = db.executeReturnQuery(SELECT);
            idP = Integer.parseInt(result.get(0).toString());
            idP=idP+1;

            do{
                costo = 30 + (random.nextDouble() * (50 - 30)); // Genera un numero tra 30 e 50
                if(costo<30||costo>50){
                    System.out.println("Impossibile generare il prezzo\n");
                }else {
                    System.out.printf("Prezzo generato: %.2f €%n", costo);
                }
            }while(costo<30||costo>50);

            INSERT = "INSERT INTO prenotazioni (idP, dataG , fasciaO, tipologia, costo, numP) VALUES('" +
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
        SELECT = "SELECT count(*) FROM caciokart.prenotazioni WHERE dataG = '"
                + dataGara + "' AND fasciaO= '"
                + oraI + "'";
        result = db.executeReturnQuery(SELECT);
        nPartecipanti = Integer.parseInt(result.get(0).toString());

        if(nPartecipanti >= 1 || nPartecipanti < MAX){
            //creazione della gara

            switch (tipologia){

                case "secca":
                    SELECT="SELECT MAX(idG) from garaS";
                    result = db.executeReturnQuery(SELECT);
                    idG = Integer.parseInt(result.get(0).toString());
                    idG=idG+1;
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
                    SELECT="SELECT MAX(idG) from garaL";
                    result = db.executeReturnQuery(SELECT);
                    idG = Integer.parseInt(result.get(0).toString());
                    idG=idG+1;
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
        }

    }
}
