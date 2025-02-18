import java.sql.Timestamp;
import java.time.LocalDate;

public class Iscrizione {
    private LocalDate dataP;
    private Timestamp ora;
    private String tipologia;
    private int npartecipanti;
    private double costo;

    public Iscrizione(LocalDate dataP, Timestamp ora, String tipologia, int npartecipanti, double costo) {
        this.dataP = dataP;
        this.ora = ora;
        this.tipologia = tipologia;
        this.npartecipanti = npartecipanti;
        this.costo = costo;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public int getNpartecipanti() {
        return npartecipanti;
    }

    public void setNpartecipanti(int npartecipanti) {
        this.npartecipanti = npartecipanti;
    }

    public LocalDate getDataP() {
        return dataP;
    }

    public void setDataP(LocalDate dataP) {
        this.dataP = dataP;
    }

    public Timestamp getOra() {
        return ora;
    }

    public void setOra(Timestamp ora) {
        this.ora = ora;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

}
