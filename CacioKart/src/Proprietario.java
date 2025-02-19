import java.time.LocalDate;

public class Proprietario extends Dipendente {

    public Proprietario(String nome, String cognome, LocalDate dataNascita, String cF, String mail, String password, double stipendio) {
        super(nome, cognome, dataNascita, cF, mail, password, stipendio);
    }

    public void visioneBilancio(){

    };

    public void aggiuntaDipendenti(){

    };

    public void rimozioneDipendenti(){

    };
}
