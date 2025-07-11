package Logic;

import Enums.Query;
import Objects.Kart;
import java.util.List;
import java.util.Map;

public class KartDAO {
    private DBConnector db = new DBConnector();

    public void insertKart(Kart k) {
        String query = Query.INSERIMENTO_KART_CONCESSIONARIA_TABELLA_KART.getQuery(k.getTarga(), k.getCilindrata(), k.getSerbatoio());
        db.executeUpdateQuery(query);
    }

    public String getNextProductId() {
        String query = Query.INSERIMENTO_KART_CONCESSIONARIA_MAX_ID.getQuery();
        List<Map<String, Object>> result = db.executeReturnQuery(query);
        Object val = result.get(0).get("max");
        String max = (val == null) ? "0" : val.toString();
        return (max == null || max.isEmpty()) ? "1" : String.valueOf(Integer.parseInt(max) + 1);
    }
}
