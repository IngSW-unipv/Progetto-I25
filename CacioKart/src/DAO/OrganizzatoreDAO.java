package DAO;

import Logic.DBConnector;
import Enums.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Implementazione concreta di OrganizzatoreDAOInterface.
 */
public class OrganizzatoreDAO implements OrganizzatoreDAOInterface {
    private final DBConnector db;

    public OrganizzatoreDAO(DBConnector db) {
        this.db = db;
    }

    @Override
    public List<Map<String, Object>> mostraSociInserimentoCampionato() {
        String query = Query.MOSTRA_SOCI_INSERIMENTO_CAMPIONATO.getQuery();
        return db.executeReturnQuery(query);
    }

    @Override
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

    @Override
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

    @Override
    public List<Map<String, Object>> mostraGareInserimento() {
        String query = Query.MOSTRA_GARE_INSERIMENTO.getQuery();
        return db.executeReturnQuery(query);
    }

    @Override
    public String aggiungiGaraACampionato(String idGara, String idCampionato) {
        String query = String.format(Query.AGGIUNGI_GARA_PARTECIPA_CAMPIONATO.getQuery(), idGara, idCampionato);
        System.out.println("[DEBUG] Query eseguita: " + query);
        return db.executeUpdateQuery(query);
    }

    @Override
    public List<Map<String, Object>> mostraPrenotazioniOrganizzatore() {
        String query = Query.MOSTRA_PRENOTAZIONI_ORGANIZZATORE.getQuery();
        return db.executeReturnQuery(query);
    }

    @Override
    public List<Map<String, Object>> mostraSociAggiuntaPrenotazione() {
        String query = Query.SELEZIONA_SOCIO_AGGIUNTA_PRENOTAZIONE.getQuery();
        return db.executeReturnQuery(query);
    }

    @Override
    public String inserisciSocioInPrenotazione(String idP, String socio, String data) {
        String query = String.format(Query.INSERIMENTO_SOCIO_GARA.getQuery(), idP, socio, data);
        return db.executeUpdateQuery(query);
    }
}
