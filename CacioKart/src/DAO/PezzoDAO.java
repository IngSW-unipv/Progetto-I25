package DAO;

import Objects.Pezzo;
import Logic.DBConnector;
import Enums.Query;


public class PezzoDAO {
    public void insertPezzo(Pezzo p, int quantita) {
        String query = String.format(
                Query.INSERIMENTO_NUOVI_PEZZI.getQuery(),
                quantita, // Quantità giusta!
                p.getIdProdotto()
        );
        DBConnector.getInstance().executeUpdateQuery(query);
    }
}
