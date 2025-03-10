import java.time.LocalDate;

public class Secca extends Gara{
    private int partecipanti;

    public Secca(String idGara,LocalDate dataGara,int partecipanti) {
        super(idGara,dataGara);
        this.partecipanti = partecipanti;
    }

    public int getPartecipanti() {
        return partecipanti;
    }

    public void setPartecipanti(int partecipanti) {
        this.partecipanti = partecipanti;
    }
}
