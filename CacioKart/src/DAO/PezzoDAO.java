package DAO;

import Enums.Query;
import Logic.DBConnector;
import Objects.Pezzo;

public class PezzoDAO {
    private DBConnector db = DBConnector.getInstance();

    public void insertPezzo(Pezzo p) {
        String query = Query.INSERIMENTO_NUOVI_PEZZI.getQuery(p.getQuantita(), p.getIdProdotto());
        db.executeUpdateQuery(query);
    }
}

