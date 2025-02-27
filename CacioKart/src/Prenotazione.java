import java.net.Socket;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;
import java.util.Scanner;

public class Prenotazione {
    private final int MAX=20;
    private  String SELECT;
    private String INSERT;
    LocalDate dataG;
    LocalTime orarioI;
    LocalTime orarioF;
    private DBConnector db;
    private PHPResponseHandler responder;
    private int queryIndicator;

    public void prenotation(String tipologia,LocalDate dataGara, LocalTime oraI,LocalTime oraF, Socket clientSocket) throws SQLException {
        db = new DBConnector();
        int idP = 0,idG, nPartecipanti = 0;
        double costo = 0;
        String input;
        Gara g=new Gara(0,null);
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        responder = new PHPResponseHandler();
        SELECT = "SELECT count(*) FROM caciokart.prenotazioni WHERE dataG = '"
                + dataGara + "' AND fasciaO= '"
                + oraI + "'";
        input = db.executeReturnQuery(SELECT).toString();
        nPartecipanti = Integer.parseInt(input);
        if(nPartecipanti > MAX){
            System.out.println("Nessun posto disponibile\n!");
        }else{
            SELECT="SELECT MAX(idP) from PRENOTAZIONI";
            input = db.executeReturnQuery(SELECT).toString();
            idP = Integer.parseInt(input);
            idP=idP+1;

            do{
                System.out.print("il costo per la prenotazione\n ");
                costo = 30 + (random.nextDouble() * (50 - 30)); // Genera un numero tra 30 e 50
                if(costo<30||costo>50){
                    System.out.println("impossibile\n");
                }else {
                    System.out.printf("Prezzo generato: %.2f €%n", costo);
                }
            }while(costo<30||costo>50);
            nPartecipanti=1;
            INSERT="INSERT INTO prenotazioni (idP, dataP,dataG , fasciaO, tipologia, costo,numP) VALUES('" +
                    idP + "', '" +
                    dataGara +"', '" +
                    oraI +"', '" +
                    tipologia + "', '" +
                    costo +"', '" +
                    nPartecipanti + "')";
            queryIndicator = db.executeUpdateQuery(INSERT);
            responder.sendResponse(clientSocket, Integer.toString(queryIndicator));
            System.out.println("La prenotazione della gara libera\n");
        }
        //conteggio degli utenti che vogliono fare la gara in quel giorno in quella ora
        SELECT = "SELECT count(*) FROM caciokart.prenotazioni WHERE dataG = '"
                + dataGara + "' AND fasciaO= '"
                + oraI + "'";
        input = db.executeReturnQuery(SELECT).toString();
        nPartecipanti = Integer.parseInt(input);
        if(nPartecipanti >= 1||nPartecipanti<MAX){
            //creazione della gara

            switch (tipologia){
                case "secca":
                    SELECT="SELECT MAX(idG) from garaS";
                    input = db.executeReturnQuery(SELECT).toString();
                    idG = Integer.parseInt(input);
                    idG=idG+1;
                    g.setIdGara(idG);
                    g.setOra(oraI);
                    INSERT="INSERT INTO garaS (idG,ora,nPart,btempo,,idC,idP) VALUES('" +
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
                    input = db.executeReturnQuery(SELECT).toString();
                    idG = Integer.parseInt(input);
                    idG=idG+1;
                    g.setIdGara(idG);
                    g.setOra(oraI);
                    INSERT="INSERT INTO garaL (idG,ora,idC,idP) VALUES('" +
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
