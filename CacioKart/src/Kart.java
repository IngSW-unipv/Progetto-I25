public class Kart {
    private String targa;
    private int cilindrata;
    private double serbatoio;

    public Kart(String targa, int cilindrata, double serbatoio) {
        this.targa = targa;
        this.cilindrata = cilindrata;
        this.serbatoio = serbatoio;
    }

    public Kart(){

    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public int getCilindrata() {
        return cilindrata;
    }

    public void setCilindrata(int cilindrata) {
        this.cilindrata = cilindrata;
    }

    public double getSerbatoio() {
        return serbatoio;
    }

    public void setSerbatoio(double serbatoio) {
        this.serbatoio = serbatoio;
    }

}
