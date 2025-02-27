import java.time.LocalDate;
import java.time.LocalTime;

public class Gara {
    protected int idGara;
    protected LocalTime ora;

    public Gara(int idGara, LocalDate dataGara) {
        this.idGara = idGara;
        this.ora = ora;
    }

    public int getIdGara() {
        return idGara;
    }

    public void setIdGara(int idGara) {
        this.idGara = idGara;
    }

    public LocalTime getOra() {
        return ora;
    }

    public void setOra(LocalTime ora) {
        this.ora = ora;
    }

}
