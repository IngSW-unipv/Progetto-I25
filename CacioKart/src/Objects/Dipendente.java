package Objects;

import java.time.LocalDate;
import java.time.LocalTime;

public class Dipendente extends Persona {
    private double stipendio;
    private String ruolo;
    private LocalTime oreL;

    public Dipendente(String nome, String cognome, LocalDate dataNascita, String cF, String mail, String password, double stipendio, String ruolo, LocalTime oreL) {
        super(nome, cognome, dataNascita, cF, mail, password);
        this.stipendio = stipendio;
        this.ruolo = ruolo;
        this.oreL = oreL;
    }

    public Dipendente(){

    }

    public LocalTime getOreL() {
        return oreL;
    }

    public void setOreL(LocalTime oreL) {
        this.oreL = oreL;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public double getStipendio() {
        return stipendio;
    }

    public void setStipendio(double stipendio) {
        this.stipendio = stipendio;
    }

}
