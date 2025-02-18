import java.time.LocalDate;

public class Secca extends Gara{
    private int partecipanti;
    public Secca(String idGara, String nomeGara, LocalDate dataGara, Circuito [] array) {
        super(idGara,nomeGara,dataGara,array);
        this.partecipanti = partecipanti;
    }

    public int getPartecipanti() {
        return partecipanti;
    }

    public void setPartecipanti(int partecipanti) {
        this.partecipanti = partecipanti;
    }
}
