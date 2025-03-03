import java.time.LocalDate;
import java.time.LocalTime;

public class Gara {
    protected String idGara;
    protected LocalTime ora;

    public Gara(String idGara, LocalDate dataGara) {
        this.idGara = idGara;
        this.ora = ora;
    }

    public String getIdGara() {
        return idGara;
    }

    public void setIdGara(String idGara) {
        this.idGara = idGara;
    }

    public LocalTime getOra() {
        return ora;
    }

    public void setOra(LocalTime ora) {
        this.ora = ora;
    }

}
