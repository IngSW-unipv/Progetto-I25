import java.time.LocalDate;

public class Gara {
    protected String idGara;
    protected String nomeGara;
    protected LocalDate dataGara;
    protected Circuito[] array; // Array di Circuito

    public Gara(String idGara, String nomeGara, LocalDate dataGara, Circuito[] array) {
        this.idGara = idGara;
        this.nomeGara = nomeGara;
        this.dataGara = dataGara;
        this.array = array.clone();
    }

    public String getIdGara() {
        return idGara;
    }

    public void setIdGara(String idGara) {
        this.idGara = idGara;
    }

    public String getNomeGara() {
        return nomeGara;
    }

    public void setNomeGara(String nomeGara) {
        this.nomeGara = nomeGara;
    }

    public LocalDate getDataGara() {
        return dataGara;
    }

    public void setDataGara(LocalDate dataGara) {
        this.dataGara = dataGara;
    }

}
