package DAO.implementazioni;

/*
    per ora lascio come variabile di ritorno 1 se è corretta e 0 se
    ci sono errori, ma penso di invertirli nell'interfaccia.
 */

import DAO.interfacce.PrenotazioneDAOInterface;
import Enums.Query;
import Logic.DBConnector;
import Logic.Prenotazione;
import Strategy.PrenotazioneStrategy;
import Strategy.PrenotazioneStrategyFactory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class PrenotazioneDAO implements PrenotazioneDAOInterface {
    private final DBConnector db;

    public PrenotazioneDAO(DBConnector db) {
        this.db = db;
    }

    @Override
    public int prenota(String username, String tipologia, LocalDate data, LocalTime intervallo, LocalDate dataCorr) {

        String query = Query.PRENOTAZIONE_CONTEGGIO_POSTI_RIMASTI.getQuery(data, intervallo);
        List<Map<String, Object>> res = db.executeReturnQuery(query);
        String prenotazioniConcorrenti = res.getFirst().get("concurrent").toString();

        if(prenotazioniConcorrenti.equals("20"))
            return 0;

        query = Query.PRENOTAZIONE_MAX_ID.getQuery();

        res.clear();
        res = db.executeReturnQuery(query);
        String idPrenotazione = res.getFirst().get("max").toString();
        idPrenotazione = (!idPrenotazione.equals("0")) ? String.valueOf(Integer.parseInt(idPrenotazione) + 1) : "1";
        PrenotazioneStrategy strategy = PrenotazioneStrategyFactory.getStrategy(tipologia);
        int var = strategy.eseguiPrenotazione(idPrenotazione, username, data, intervallo, data, db);

        return (var == 0) ? 1 : 0;
    }

    @Override
    public List<Prenotazione> visualizzaPrenotazioni() {
        return List.of();
    }
}
