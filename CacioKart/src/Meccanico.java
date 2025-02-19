import java.time.LocalDate;

public class Meccanico extends Dipendente implements Iinventario {

    public Meccanico(String nome, String cognome, LocalDate dataNascita, String cF, String mail, String password, double stipendio) {
        super(nome, cognome, dataNascita, cF, mail, password, stipendio);
    }

    //aggiunta kart noleggio??
    //interfaccia rimozione kart
    //Override metodi Iinventario

    public void aggiornamentoManutenzione(){

    };

    public void aggiuntaBenzina(){

    };
}
