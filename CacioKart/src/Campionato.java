public class Campionato {
    private Secca [] camp;
    private Kart [] karts;
    private int anno;

    public Campionato(Kart [] karts,Secca [] camp,int anno) {
        this.karts = karts.clone();
        this.camp = camp.clone();
        this.anno = anno;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }
}
