package DAO;

import Logic.DBConnector;
import Enums.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrganizzatoreDAO {
    private final DBConnector db;

    public OrganizzatoreDAO(DBConnector db) {
        this.db = db;
    }

    // Metodo per mostrare i soci che non appartengono a nessun team
    public List<Map<String, Object>> mostraSociInserimentoCampionato() {
        String query = Query.MOSTRA_SOCI_INSERIMENTO_CAMPIONATO.getQuery();
        return db.executeReturnQuery(query);
    }
    public void aggiungiTeam(String nomeTeam, String colore, List<String> soci) {
        // Inserisci il team nella tabella team
        String insertTeam = String.format(
                Query.CREA_TEAM_TABELLA_TEAM.getQuery(),
                nomeTeam, colore
        );
        db.executeUpdateQuery(insertTeam);

        // Per ogni socio inserisci la riga nella tabella appartenenza
        for (String socio : soci) {
            String insertAppartenenza = String.format(
                    Query.CREA_TEAM_TABELLA_APPARTENENZA.getQuery(),
                    socio, nomeTeam
            );
            db.executeUpdateQuery(insertAppartenenza);
        }
    }

    public List<String> mostraCampionati() {
        String query = Query.MOSTRA_CAMPIONATI.getQuery();
        List<Map<String, Object>> result = db.executeReturnQuery(query);
        List<String> campionati = new ArrayList<>();
        if (result != null) {
            for (Map<String, Object> row : result) {
                campionati.add(row.get("idCampionato").toString());
            }
        }
        return campionati;
    }

    public List<Map<String, Object>> mostraGareInserimento() {
        String query = Query.MOSTRA_GARE_INSERIMENTO.getQuery();
        return db.executeReturnQuery(query);
    }

    public void aggiungiGaraACampionato(String idGara, String idCampionato) {
        String query = String.format(Query.AGGIUNGI_GARA_PARTECIPA_CAMPIONATO.getQuery(), idGara, idCampionato);
        db.executeUpdateQuery(query);
    }

    public List<Map<String, Object>> mostraPrenotazioniOrganizzatore() {
        String query = Query.MOSTRA_PRENOTAZIONI_ORGANIZZATORE.getQuery();
        return db.executeReturnQuery(query);
    }

    public List<Map<String, Object>> mostraSociAggiuntaPrenotazione() {
        String query = Query.SELEZIONA_SOCIO_AGGIUNTA_PRENOTAZIONE.getQuery();
        return db.executeReturnQuery(query);
    }


}
