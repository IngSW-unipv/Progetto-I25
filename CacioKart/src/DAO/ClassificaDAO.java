package DAO;

import Logic.DBConnector;
import Enums.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClassificaDAO {
    private final DBConnector db;

    public ClassificaDAO(DBConnector db) {
        this.db = db;
    }

    // Metodo per ottenere la lista delle gare svolte (idGara)
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

    // Metodo per inserire una penalità (aggiornare tempTot)
    public void inserisciPenalita(String socio, String idGara, String tempo) {
        String query = String.format(
                Query.INSERIMENTO_PENALITA_ARBITRO.getQuery(),
                socio, idGara, tempo
        );
        db.executeUpdateQuery(query);
    }

    // Metodo per mostrare la classifica/penalità di una gara specifica
    public List<Map<String, Object>> mostraClassificaPenalita(String idGara) {
        String query = String.format(Query.MOSTRA_CLASSIFICA_PENALITA.getQuery(), idGara);
        return db.executeReturnQuery(query);
    }
}
