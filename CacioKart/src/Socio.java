import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;
import java.util.Scanner;

public class Socio extends Persona implements Iinventario{
    private final int MAX=20;
    private String INSERT;
    private String SELECT;
    private PHPResponseHandler responder;
    private int queryIndicator;
    private DBConnector db;

    public Socio(String nome, String cognome, LocalDate dataNascita, String cF, String mail, String password) {
        super(nome, cognome, dataNascita, cF, mail, password);
    }

    //metodo classifica gara?

    /**Metodo per registrare un utente nel db
     * La query viene eseguita tramite il metodo sendResponse della classe DBConnector
     *
     * @param clientSocket
     */
    public void registrazione(Socket clientSocket) throws SQLException {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        INSERT = "INSERT INTO socio (socio, nome, cognome, mail, passw, dataN) VALUES('" +
                this.getcF() + "', '" +
                this.getNome() + "', '" +
                this.getCognome() +"', '" +
                this.getMail() +"', '" +
                this.getPassword() +"', '" +
                this.getDataNascita() +"')";

        queryIndicator = db.executeUpdateQuery(INSERT);
        responder.sendResponse(clientSocket, Integer.toString(queryIndicator));

    }
    public int prenotation(LocalDate dataGara, LocalTime ora, Socket clientSocket) throws SQLException {

        db = new DBConnector();
        int idP, nPartecipanti = 0;
        double costo = 0;
        String input, tipologia = "";
        LocalDate dataP;
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        responder = new PHPResponseHandler();
        SELECT=SELECT = "SELECT count(*) FROM caciokart.prenotazioni WHERE dataG = '"
                + dataGara + "' AND fasciaO= '"
                + ora + "'";
        input = db.executeReturnQuery(SELECT).toString();
        nPartecipanti = Integer.parseInt(input);
        if(nPartecipanti > MAX){
            System.out.println("Nessun posto disponibile\n!");
            return 0;
        }else{
            SELECT="SELECT MAX(idP) from PRENOTAZIONI";
            input = db.executeReturnQuery(SELECT).toString();
            idP = Integer.parseInt(input);
            idP=idP+1;

            do{
                System.out.print("inserire la data della prenotazione:(FORMATO(YYYY/MM/GG) ");
                input=scanner.nextLine();
                dataP=LocalDate.parse(input);
                if(dataP.isAfter(dataGara)||dataP==null){
                    System.out.println("impossibile,reinserire la data della prenotazione\n");
                }else {
                    System.out.print("data della prenotazione corretta\n ");
                }
            }while(dataP.isAfter(dataGara)||dataP==null);

            do{
                System.out.print("inserire la tipologia\n ");
                input=scanner.nextLine();
                if (input.equalsIgnoreCase("secca")||input.equalsIgnoreCase("libera")) {
                  System.out.print("tipologia gara accettata\n ");
                  tipologia=input;
                }else{
                    System.out.print("tipologia non accettata,reinserire\n ");
                }
            }while(tipologia.equalsIgnoreCase("secca")||tipologia.equalsIgnoreCase("libera"));

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
                    dataP + "', '" +
                    dataGara +"', '" +
                    ora +"', '" +
                    tipologia + "', '" +
                    costo +"', '" +
                    nPartecipanti + "')";
            queryIndicator = db.executeUpdateQuery(INSERT);
            responder.sendResponse(clientSocket, Integer.toString(queryIndicator));
        }
        return 1;
    }
    public void comprareKart(){

    }

    //public int

    //Override metodi Iinventario


}
