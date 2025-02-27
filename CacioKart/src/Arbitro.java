import java.time.LocalDate;
import java.time.LocalTime;

public class Arbitro extends Dipendente {

    //penalità????

    public Arbitro(String nome, String cognome, LocalDate dataNascita, String cF, String mail, String password, double stipendio, String ruolo, LocalTime oreL) {
        super(nome, cognome, dataNascita, cF, mail, password, stipendio, ruolo, oreL);
    }

    public void visioneTempi(){

    };

    public void inserimentoPenalità(){

    };
}
