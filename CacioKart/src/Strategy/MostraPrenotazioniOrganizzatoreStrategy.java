package Logic;

import Enums.Query;
import java.util.List;
import java.util.Map;

public class MostraPrenotazioniOrganizzatoreStrategy implements VisualizzazionePrenotazioniStrategy{
    @Override
    public List<Map<String, Object>> eseguiQuery(DBConnector db) {
        String query = Query.MOSTRA_PRENOTAZIONI_ORGANIZZATORE.getQuery();
        return db.executeReturnQuery(query);
    }

    @Override
    public String[] getColonneDaMostrare() {
        return new String[]{"idP"};
    }
}
