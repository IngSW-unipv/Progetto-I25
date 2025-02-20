public class Team {
    private String nome;
    private String colore;
    private Kart[] karts;
    private Socio[] soci;

    public Team(String nome, String colore) {
        this.nome = nome;
        this.colore = colore;
        this.karts = new Kart[2];
        this.soci = new Socio[2];
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getColore() {
        return colore;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }
}
