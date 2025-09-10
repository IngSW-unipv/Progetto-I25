package Logic;

import Objects.Kart;
import DAO.implementazioni.KartDAO;
import DAO.implementazioni.ConcessionariaDAO;

import java.util.List;
import java.util.Map;


public class ConcessionariaService {
    private final KartDAO kartDAO = KartDAO.getInstance();
    private final ConcessionariaDAO concessionariaDAO = ConcessionariaDAO.getInstance();

    public void inserisciKartInConcessionaria(Kart kart, int prezzo) {
        kartDAO.insertKart(kart);
        String idProdotto = concessionariaDAO.getNextProductId();
        concessionariaDAO.insertConcessionariaItem(idProdotto, kart.getTarga(), 1, prezzo);
    }

    public List<Map<String, Object>> mostraPezzi() {
        return concessionariaDAO.getPezzi();
    }
}
