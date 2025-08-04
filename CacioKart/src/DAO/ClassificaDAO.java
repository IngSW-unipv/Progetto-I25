package DAO;

import Logic.DBConnector;
import Enums.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Implementazione concreta di ClassificaDAOInterface.
 */
public class ClassificaDAO implements ClassificaDAOInterface {
    private final DBConnector db;

    public ClassificaDAO(DBConnector db) {
        this.db = db;
    }

    @Override
    public List<String> mostraGareSvolte() {
        String query = Query.CLASSIFICA_ARBITRO.getQuery();
        List<Map<String, Object>> result = db.executeReturnQuery(query);
        List<String> gare = new ArrayList<>();
        if (result != null) {
            for (Map<String, Object> row : result) {
                gare.add(row.get("idGara").toString());
            }
        }
        return gare;
    }

    @Override
    public void inserisciPenalita(String socio, String idGara, String tempo) {
        String query = String.format(
                Query.INSERIMENTO_PENALITA_ARBITRO.getQuery(),
                socio, idGara, tempo
        );
        db.executeUpdateQuery(query);
    }

    @Override
    public List<Map<String, Object>> mostraClassificaPenalita(String idGara) {
        String query = String.format(Query.MOSTRA_CLASSIFICA_PENALITA.getQuery(), idGara);
        return db.executeReturnQuery(query);
    }

    @Override
    public List<Map<String, Object>> getClassificaGenerale() {
        String query = Query.CLASSIFICA_GENERALE.getQuery();
        return db.executeReturnQuery(query);
    }

    @Override
    public List<Map<String, Object>> getClassificaUtente(String nome) {
        String query = String.format(Query.CLASSIFICA_UTENTE.getQuery(), nome);
        return db.executeReturnQuery(query);
    }
}
