import java.util.List;
import java.util.ArrayList;

public class Concessionaria implements Iinventario{
    List<Kart> kart;
    List<Pezzo> pezzi;

    public Concessionaria(List<Pezzo> pezzi, List<Kart> kart) {
        this.pezzi = pezzi;
        this.kart = kart;
    }

    //Override metodi Iinventario

    public void venditaKart(){

    };

    public void inserimentoKart(){

    };

    public void inserimentoPezzo(){

    };
}
