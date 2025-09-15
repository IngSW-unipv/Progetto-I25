package Strategy;

import Logic.DBConnector;

import java.util.List;
import java.util.Map;

public interface VisualizzazionePrenotazioniStrategy {
    List<Map<String, Object>> eseguiQuery(DBConnector db);
    String[] getColonneDaMostrare();
}
