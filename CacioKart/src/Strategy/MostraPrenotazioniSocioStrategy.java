package Strategy;

import Enums.Query;
import Logic.DBConnector;

import java.util.List;
import java.util.Map;

public class MostraPrenotazioniSocioStrategy implements VisualizzazionePrenotazioniStrategy{
    private String cf;

    public MostraPrenotazioniSocioStrategy(String cf) {
        this.cf = cf;
    }

    @Override
    public List<Map<String, Object>> eseguiQuery(DBConnector db) {
        String query = Query.MOSTRA_PRENOTAZIONI_SOCIO.getQuery(cf);
        return db.executeReturnQuery(query);
    }

    @Override
    public String[] getColonneDaMostrare() {
        return new String[]{"dataG", "fasciaO", "tipologia"};
    }
}

