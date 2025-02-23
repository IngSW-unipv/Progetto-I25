import java.net.Socket;
import java.sql.SQLException;
import java.time.LocalDate;

public class Socio extends Persona implements Iinventario{
    private String INSERT;
    private PHPResponseHandler responder;

    public Socio(String nome, String cognome, LocalDate dataNascita, String cF, String mail, String password) {
        super(nome, cognome, dataNascita, cF, mail, password);
    }

    //metodo classifica gara?

    public void registrazione(Socket clientSocket){
        DBConnector db = new DBConnector();
        responder = new PHPResponseHandler();
        INSERT = "INSERT INTO socio (cf, nome, cognome, mail, pass, dataN) VALUES('" +
                this.getcF() + "', '" +
                this.getNome() + "', '" +
                this.getCognome() +"', '" +
                this.getMail() +"', '" +
                this.getPassword() +"', '" +
                this.getDataNascita() +"')";
        responder.sendResponse(clientSocket, "1");
        System.out.println("Ho mandato la risposta\n");
        try {
            db.executeUpdateQuery(INSERT);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void comprareKart(){

    }


    //Override metodi Iinventario
}
