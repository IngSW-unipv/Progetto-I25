package Logic;

import Objects.Kart;

import java.util.List;
import java.util.Map;

public class ConcessionariaService {
    private KartDAO kartDAO = new KartDAO();
    private ConcessionariaDAO concessionariaDAO = new ConcessionariaDAO();

    public void inserisciKartInConcessionaria(Kart kart, int prezzo) {
        kartDAO.insertKart(kart);
        String idProdotto = kartDAO.getNextProductId();
        concessionariaDAO.insertConcessionariaKart(idProdotto, kart.getTarga(), 1, prezzo);
    }

    public List<Map<String, Object>> mostraPezzi() {
        return concessionariaDAO.getPezzi();
    }
}
