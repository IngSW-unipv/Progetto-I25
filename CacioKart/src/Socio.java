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

    /*
    public int prenotation(LocalDate dataGara, LocalTime ora, Socket clientSocket) throws SQLException {
        int nPartecipanti = 0;
    db = new DBConnector();
    Connection conn = db.getConnection(); //Non serve, la connessione è interamente gestita da DBConnector
    int idP;
    double costo;
    String input,tipologia;
    LocalDate dataP;
    Scanner scanner = new Scanner(System.in); //Non ho capito a cosa serve
    responder = new PHPResponseHandler();

    // Ottieni l'ID massimo per la nuova prenotazione
    String SELECT_ID = "SELECT MAX(idP) FROM prenotazione";
    idP = db.executeReturnQuery(SELECT_ID) + 1; //Il valore di ritorno deve essere una map di tipo List<Map<String, Object>>

    // Controllo disponibilità partecipanti
    String SELECT_COUNT = "SELECT COUNT(*) FROM prenotazione WHERE dataG = ? AND fasciaO = ?"; //Questa query non penso vada, magari si
    try (PreparedStatement pstmt = conn.prepareStatement(SELECT_COUNT)) { //Gestito da DBConn, te pensa a iterare i dati e basta, non preoccuparti della connessione
        pstmt.setDate(1, java.sql.Date.valueOf(dataGara));
        pstmt.setTime(2, java.sql.Time.valueOf(ora));
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                nPartecipanti = rs.getInt(1);
            }
        }
    }

    if (nPartecipanti >= MAX) {
        return 0;
    } else {
        //verifica il giorno della prenotazione
        do {
            System.out.println("Inserisci una data della prenotazione (formato yyyy-MM-dd): ");
            input = scanner.nextLine(); //Il resto del progetto passa
            try {
                dataP = LocalDate.parse(input);
                if (dataP.isAfter(dataGara)) {
                    System.out.println("Errore: La data di prenotazione deve essere successiva alla data della gara.\n");
                }
            } catch (Exception e) {
                System.out.println("Formato data non valido. Riprova.\n");
                dataP = null; // Reset della variabile
            }
        } while (dataP == null || dataP.isAfter(dataGara));
        // Calcola il costo casuale tra 20 e 50 euro, arrotondato a due decimali
        Random random = new Random();
        costo = Math.round((20 + random.nextDouble() * 30) * 100.0) / 100.0;
        nPartecipanti = 1;
        // stabilisce la tipologia della gara
        do {
            System.out.println("Inserisci la tipologia della gara (secca/libera):");
            input = scanner.nextLine();
            try {
                tipologia = input.toLowerCase();  // Puoi anche usare toLowerCase() per essere insensibile al caso
                if (tipologia.equals("secca") || tipologia.equals("libera")) {
                    // Se la tipologia è "secca" o "libera", esci dal ciclo
                    break;  // Uscita dal ciclo do-while
                } else {
                    System.out.println("Tipologia non valida. Devi scegliere 'secca' o 'libera'. Riprova.");
                }
            } catch (Exception e) {
                System.out.println("Errore: Formato non valido. Riprova.");
            }
        } while (true);
        // Esegui l'inserimento in modo sicuro
        INSERT = "INSERT INTO prenotazione (idP, dataP, dataG, fasciaO, tipologia, costo, numP) VALUES (?, ?, ?, ?, ?, ?, ?)"; //Stesso discorso della query di prima
        try (PreparedStatement pstmt = conn.prepareStatement(INSERT)) { //Stesso discorso di prima, non ti preoccupare per la logica di connessione db
            pstmt.setInt(1, idP);
            pstmt.setDate(2, java.sql.Date.valueOf(dataP)); //Se spedisci al db date in formato stringa le riceve lo stesso
            pstmt.setDate(3, java.sql.Date.valueOf(dataGara));
            pstmt.setTime(4, java.sql.Time.valueOf(ora));
            pstmt.setString(5, tipologia);
            pstmt.setDouble(6, costo);
            pstmt.setInt(7, nPartecipanti);
            queryIndicator = pstmt.executeUpdate();
        }

        responder.sendResponse(clientSocket, Integer.toString(queryIndicator));
        return 1;
    }
}*/

    public void comprareKart(){

    }

    //public int

    //Override metodi Iinventario


}
