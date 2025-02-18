public class Pezzo {
    private String nome;
    private int quantita;
    private String descrizione;

    public Pezzo(String nome, int quantita, String descrizione) {
        this.nome = nome;
        this.quantita = quantita;
        this.descrizione = descrizione;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
