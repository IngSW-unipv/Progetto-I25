package Logic;

import Objects.Kart;
import DAO.KartDAO;
import DAO.ConcessionariaDAO;

import java.util.List;
import java.util.Map;

public class ConcessionariaService {
    private KartDAO kartDAO = KartDAO.getInstance();
    private ConcessionariaDAO concessionariaDAO = ConcessionariaDAO.getInstance();

    public void inserisciKartInConcessionaria(Kart kart, int prezzo) {
        kartDAO.insertKart(kart);
        String idProdotto = concessionariaDAO.getNextProductId();  // <-- CORRETTO!
        concessionariaDAO.insertConcessionariaItem(idProdotto, kart.getTarga(), 1, prezzo);
    }

    public List<Map<String, Object>> mostraPezzi() {
        return concessionariaDAO.getPezzi();
    }
}
