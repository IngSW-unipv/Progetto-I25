import java.time.LocalDate;
import java.time.LocalTime;

public class Organizzatore extends Dipendente {

    public Organizzatore(String nome, String cognome, LocalDate dataNascita, String cF, String mail, String password, double stipendio, String ruolo, LocalTime oreL) {
        super(nome, cognome, dataNascita, cF, mail, password, stipendio, ruolo, oreL);
    }

    public void creaGara(){

    };

    public void creaTeam(){

    };

    public void creaCampionato(){

    };

    //solo gara campionato??
    public void inserimentoGara(){

    };
}


