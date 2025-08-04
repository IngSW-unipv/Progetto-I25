package DAO;

import Enums.Query;
import Logic.DBConnector;
import java.util.List;
import java.util.Map;

/**
 * Implementazione concreta di BilancioDAOInterface.
 */
public class BilancioDAO implements BilancioDAOInterface {
    private final DBConnector db;

    public BilancioDAO(DBConnector db) {
        this.db = db;
    }

    @Override
    public double getEntrate() {
        String query = Query.BILANCIO_ENTRATE_PROPRIETARIO.getQuery();
        List<Map<String, Object>> result = db.executeReturnQuery(query);
        return estraiDouble(result);
    }

    @Override
    public double getUscite() {
        String query = Query.BILANCIO_USCITE_PROPRIETARIO.getQuery();
        List<Map<String, Object>> result = db.executeReturnQuery(query);
        return estraiDouble(result);
    }

    @Override
    public double getSaldo() {
        String query = Query.BILANCIO_SALDO_TOTALE.getQuery();
        List<Map<String, Object>> result = db.executeReturnQuery(query);
        return estraiDouble(result);
    }

    private double estraiDouble(List<Map<String, Object>> result) {
        if (result == null || result.isEmpty()) return 0;
        Object val = result.get(0).values().iterator().next();
        if (val == null) return 0;
        try {
            return Double.parseDouble(val.toString());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
