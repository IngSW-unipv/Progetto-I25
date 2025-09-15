package DAO.implementazioni;

import DAO.interfacce.PezzoDAOInterface;
import Objects.Pezzo;
import Logic.DBConnector;
import Enums.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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

    public boolean acquistaPezzo(Pezzo pezzo, String cfSocio) {
        DBConnector db = DBConnector.getInstance();

        String insertPezzoQuery = String.format(
                Query.ACQUISTA_PEZZI_TABELLA_CONCESSIONARIA.getQuery(),
                pezzo.getIdProdotto()
        );

        String updateSocioQuery = String.format(
                Query.ACQUISTA_PEZZI_TABELLA_ACQUISTA.getQuery(),
                cfSocio,
                pezzo.getIdProdotto(),
                LocalDate.now()
        );

        int insertResult = Integer.parseInt(db.executeUpdateQuery(insertPezzoQuery));
        int updateResult = Integer.parseInt(db.executeUpdateQuery(updateSocioQuery));

        return insertResult > 0 && updateResult > 0;
    }

    public List<Map<String, Object>> mostraPezziDisponibili() {
        DBConnector db = DBConnector.getInstance();
        String query = Query.MOSTRA_PEZZI_SOCIO.getQuery();
        return db.executeReturnQuery(query);
    }
}
