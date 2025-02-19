import java.time.LocalDate;

public class Dipendente extends Persona {
    private double stipendio;

    public Dipendente(String nome, String cognome, LocalDate dataNascita, String cF, String mail, String password, double stipendio) {
        super(nome, cognome, dataNascita, cF, mail, password);
        this.stipendio = stipendio;
    }

    public double getStipendio() {
        return stipendio;
    }

    public void setStipendio(double stipendio) {
        this.stipendio = stipendio;
    }
}
