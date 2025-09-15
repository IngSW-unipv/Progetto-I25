package Strategy;

/*
    Ho tolto la gestione del socket in quanto questa classe viene usata solo dal DAO
    che non si può occupare di altro a parte dbconn ed ho cambiato la logica del
    result per essere uguale a Libera.
*/

import Enums.Query;
import Logic.DBConnector;
import java.time.LocalDate;
import java.time.LocalTime;

public class PrenotazioneSeccaStrategy implements PrenotazioneStrategy {
    @Override
    public int eseguiPrenotazione(String idPrenotazione, String cf, LocalDate dataGara, LocalTime fasciaOraria,
                                  LocalDate dataO, DBConnector db) {
        int costo = 20;
        String insert = Query.PRENOTAZIONE_GENERICA_INSERIMENTO.getQuery(idPrenotazione, dataGara, fasciaOraria, "secca", costo);
        String result = db.executeUpdateQuery(insert);
        if(result.equals("0"))
            return 1;

        return 0;
    }
}
