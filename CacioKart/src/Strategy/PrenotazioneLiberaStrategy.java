package Strategy;

/*
    Simile a secca in quanto alla gestione dei socket
 */

import Enums.Query;
import Logic.DBConnector;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class PrenotazioneLiberaStrategy implements PrenotazioneStrategy {

    @Override
    public int eseguiPrenotazione(
            String idPrenotazione,
            String cf,
            LocalDate dataGara,
            LocalTime fasciaOraria,
            LocalDate dataO,
            DBConnector db
    ) {
        int costo = 15;
        String[] insertIterator = new String[2];

        String select = Query.SELEZIONA_DIPENDENTE_PRENOTAZIONE.getQuery(cf);
        List<Map<String, Object>> result = db.executeReturnQuery(select);

        insertIterator[0] = Query.PRENOTAZIONE_GENERICA_INSERIMENTO.getQuery(idPrenotazione, dataGara, fasciaOraria, "libera", costo);

        if (!result.isEmpty() && cf.equals(result.getFirst().get("dip"))) {
            insertIterator[1] = Query.PRENOTAZIONE_LIBERA_INSERIMENTO_NULL.getQuery(idPrenotazione, dataO);
        } else {
            insertIterator[1] = Query.PRENOTAZIONE_LIBERA_INSERIMENTO.getQuery(idPrenotazione, cf, dataO);
        }

        for (String query : insertIterator) {
            String indicator = db.executeUpdateQuery(query);
            if (indicator.equals("0")) {
                return 1;
            }
        }
        return 0;
    }
}