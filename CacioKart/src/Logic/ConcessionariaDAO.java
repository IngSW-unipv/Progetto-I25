package Logic;

import Enums.Query;

import java.util.List;
import java.util.Map;

public class ConcessionariaDAO {
    private DBConnector db = new DBConnector();

    public void insertConcessionariaKart(String idProdotto, String targa, int quantita, int prezzo) {
        String query = Query.INSERIMENTO_KART_CONCESSIONARIA_TABELLA_CONCESSIONARIA.getQuery(idProdotto, targa, quantita, prezzo);
        db.executeUpdateQuery(query);
    }

    public List<Map<String, Object>> getPezzi() {
        String query = Query.MOSTRA_PEZZI_CONCESSIONARIA.getQuery();
        return db.executeReturnQuery(query);
    }
}
