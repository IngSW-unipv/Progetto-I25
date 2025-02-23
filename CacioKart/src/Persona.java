import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Persona {
    private String nome;
    private String cognome;
    private LocalDate dataNascita;
    private String cF;
    private String mail;
    private String password;
    private String SELECT;
    private ResultSet rs;

    public Persona( String nome, String cognome, LocalDate dataNascita, String cF, String mail, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.cF = cF;
        this.mail = mail;
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getcF() {
        return cF;
    }

    public void setcF(String cF) {
        this.cF = cF;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ResultSet login(){
        DBConnector db = new DBConnector();
        SELECT = "SELECT cf,pass FROM caciokart.socio WHERE cf = '" + this.getcF() + "' AND pass = '" + this.getPassword() +"'";
        try {
            rs = db.executeReturnQuery(SELECT);

            //MANCA LA LOGICA, SE NON TROVA RISULTATI SPEDISCE 0 TRAMITE PHPRESPONSE
            //SE TROVA RISULTATI DOBBIAMO ASSOCIARE I VALORI AI RUOLI

            if(rs != null){

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }
}