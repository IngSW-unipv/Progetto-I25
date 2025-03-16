package Objects;

public class Pezzo {
    private String idProdotto, descrizione;
    private int quantita;

    public Pezzo(String idProdotto, int quantita, String descrizione) {
        this.idProdotto = idProdotto;
        this.quantita = quantita;
        this.descrizione = descrizione;
    }

    public Pezzo(){

    }

    public String getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(String idProdotto) {
        this.idProdotto = idProdotto;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }


}
