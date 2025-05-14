package Objects;

import Logic.Socio;

public class Team {
    private String nome;
    private String colore;
    private final Socio[] soci;

    /** Costruttore della classe Team.
     * In ingresso si ha: il nome, il colore e i due codici fiscali.
     * I cf vengono gestiti creando due oggetti di tipo Socio.
     *
     * @param nome
     * @param colore
     * @param cf1
     * @param cf2
     */
    public Team(String nome, String colore, String cf1, String cf2) {
        this.nome = nome;
        this.colore = colore;
        this.soci = new Socio[2];
        Socio Socio1 = new Socio();
        Socio Socio2 = new Socio();
        Socio1.setCf(cf1);
        Socio2.setCf(cf2);
        this.soci[0] = Socio1;
        this.soci[1] = Socio2;
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

    public Socio[] getSoci() {
        return soci;
    }
}
