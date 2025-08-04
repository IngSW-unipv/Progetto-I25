package DAO;

import Logic.DBConnector;
import Enums.Query;
import java.util.List;
import java.util.Map;

/**
 * Implementazione concreta di ConcessionariaDAOInterface.
 */
public class ConcessionariaDAO implements ConcessionariaDAOInterface {
    private static ConcessionariaDAO instance;

    private ConcessionariaDAO() {}

    public static ConcessionariaDAO getInstance() {
        if (instance == null) instance = new ConcessionariaDAO();
        return instance;
    }

    @Override
    public String getNextProductId() {
        DBConnector db = DBConnector.getInstance();
        String query = Query.INSERIMENTO_KART_CONCESSIONARIA_MAX_ID.getQuery();
        List<Map<String, Object>> result = db.executeReturnQuery(query);
        if (!result.isEmpty() && result.get(0).get("max") != null) {
            int maxId = Integer.parseInt(result.get(0).get("max").toString());
            return String.valueOf(maxId + 1);
        } else {
            return "1";
        }
    }

    @Override
    public boolean insertConcessionariaItem(String idProdotto, String tipol, int quantita, int prezzo) {
        DBConnector db = DBConnector.getInstance();
        String query = Query.INSERIMENTO_KART_CONCESSIONARIA_TABELLA_CONCESSIONARIA.getQuery(
                idProdotto, tipol, quantita, prezzo
        );
        String result = db.executeUpdateQuery(query);
        return result.equals("OK");
    }

    @Override
    public List<Map<String, Object>> getPezzi() {
        DBConnector db = DBConnector.getInstance();
        String query = Query.MOSTRA_PEZZI_CONCESSIONARIA.getQuery();
        return db.executeReturnQuery(query);
    }

    @Override
    public boolean aggiungiPezzi(String idProdotto, int quantitaDaAggiungere) {
        DBConnector db = DBConnector.getInstance();
        String query = Query.INSERIMENTO_NUOVI_PEZZI.getQuery(
                quantitaDaAggiungere, idProdotto
        );
        String result = db.executeUpdateQuery(query);
        return result.equals("OK");
    }

    @Override
    public boolean decrementaQuantitaConcessionaria(String tipol) {
        DBConnector db = DBConnector.getInstance();
        String query = Query.AGGIORNA_QUANTITA_CONCESSIONARIA.getQuery(tipol);
        String result = db.executeUpdateQuery(query);
        return "OK".equals(result) || "1".equals(result);
    }
}
