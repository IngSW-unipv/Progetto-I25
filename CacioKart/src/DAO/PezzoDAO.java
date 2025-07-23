package Logic;

import Enums.Query;
import Objects.Pezzo;

public class PezzoDAO {
    private DBConnector db = new DBConnector();

    public void insertPezzo(Pezzo p) {
        String query = Query.INSERIMENTO_NUOVI_PEZZI.getQuery(p.getQuantita(), p.getIdProdotto());
        db.executeUpdateQuery(query);
    }
}

