import java.sql.SQLException;
import java.time.LocalDate;

public class Socio extends Persona implements Iinventario{
    private String INSERT;

    public Socio(String nome, String cognome, LocalDate dataNascita, String cF, String mail, String password) {
        super(nome, cognome, dataNascita, cF, mail, password);
    }

    //metodo classifica gara?

    public void registrazione(){
        DBConnector db = new DBConnector();
        INSERT = "INSERT INTO socio (cf, nome, cognome, mail, pass, dataN) VALUES('" +
                this.getcF() + "', '" +
                this.getNome() + "', '" +
                this.getCognome() +"', '" +
                this.getMail() +"', '" +
                this.getPassword() +"', '" +
                this.getDataNascita() +"')";
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
