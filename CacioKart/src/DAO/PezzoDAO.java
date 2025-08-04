package DAO;

import Objects.Pezzo;
import Logic.DBConnector;
import Enums.Query;

/**
 * Implementazione concreta di PezzoDAOInterface.
 */
public class PezzoDAO implements PezzoDAOInterface {

    @Override
    public void insertPezzo(Pezzo p, int quantita) {
        String query = String.format(
                Query.INSERIMENTO_NUOVI_PEZZI.getQuery(),
                quantita,
                p.getIdProdotto()
        );
        DBConnector.getInstance().executeUpdateQuery(query);
    }
}
