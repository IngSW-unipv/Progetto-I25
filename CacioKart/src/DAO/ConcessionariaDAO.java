package DAO;

import Logic.DBConnector;
import Enums.Query;
import java.util.List;
import java.util.Map;

public class ConcessionariaDAO {
    private static ConcessionariaDAO instance;
    private ConcessionariaDAO() {}
    public static ConcessionariaDAO getInstance() {
        if (instance == null) instance = new ConcessionariaDAO();
        return instance;
    }

    // 1. Ottieni il prossimo idProdotto (MAX+1) per nuovi inserimenti
    public String getNextProductId() {
        DBConnector db = new DBConnector();
        String query = Query.INSERIMENTO_KART_CONCESSIONARIA_MAX_ID.getQuery();
        List<Map<String, Object>> result = db.executeReturnQuery(query);
        if (!result.isEmpty() && result.get(0).get("max") != null) {
            int maxId = Integer.parseInt(result.get(0).get("max").toString());
            return String.valueOf(maxId + 1);
        } else {
            return "1";
        }
    }

    // 2. Inserisce un nuovo kart o pezzo nella tabella concessionaria
    public boolean insertConcessionariaItem(String idProdotto, String tipol, int quantita, int prezzo) {
        DBConnector db = new DBConnector();
        String query = Query.INSERIMENTO_KART_CONCESSIONARIA_TABELLA_CONCESSIONARIA.getQuery(
                idProdotto, tipol, quantita, prezzo
        );
        String result = db.executeUpdateQuery(query);
        return result.equals("OK");
    }

    // 3. Mostra tutti i pezzi presenti in concessionaria (esclusi i kart)
    public List<Map<String, Object>> getPezzi() {
        DBConnector db = new DBConnector();
        String query = Query.MOSTRA_PEZZI_CONCESSIONARIA.getQuery();
        return db.executeReturnQuery(query);
    }

    // 4. Aggiunge nuovi pezzi ad un idProdotto già presente (aumenta la quantità)
    public boolean aggiungiPezzi(String idProdotto, int quantitaDaAggiungere) {
        DBConnector db = new DBConnector();
        String query = Query.INSERIMENTO_NUOVI_PEZZI.getQuery(
                quantitaDaAggiungere, idProdotto
        );
        String result = db.executeUpdateQuery(query);
        return result.equals("OK");
    }
}
