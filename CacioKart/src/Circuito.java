public class Circuito {
    private String idCircuito;
    private String nomeCircuito;
    private double km;

    public Circuito(String idCircuito, String nomeCircuito, double km) {
        this.idCircuito = idCircuito;
        this.nomeCircuito = nomeCircuito;
        this.km = km;
    }

    public String getIdCircuito() {
        return idCircuito;
    }

    public void setIdCircuito(String idCircuito) {
        this.idCircuito = idCircuito;
    }

    public String getNomeCircuito() {
        return nomeCircuito;
    }

    public void setNomeCircuito(String nomeCircuito) {
        this.nomeCircuito = nomeCircuito;
    }

    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }
}
