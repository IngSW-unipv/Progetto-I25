package Logic;

import Enums.Query;
import Objects.Kart;
import WebTalker.PHPResponseHandler;

import java.net.Socket;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Meccanico {
    private List<Map<String, Object>> result;
    private DBConnector db;
    private PHPResponseHandler responder;
    private String SELECT, DELETE, UPDATE, INSERT;
    private String[] DELETE_ITERATOR;
    private TableMaker maker;
    private String queryIndicator;

    public Meccanico() {}

    /**
     * Metodo per aggiungere manutenzioni su un determinato kart.
     * Dopo aver calcolato l'id della nuova manutenzione, il metodo
     * aggiorna la tabella delle manutenzioni.
     *
     * @param k Il kart su cui viene effettuata la manutenzione
     * @param text La descrizione della manutenzione
     * @param prezzo Il costo della manutenzione
     * @param clientSocket Il socket di risposta
     */
    public void aggiornamentoManutenzione(Kart k, String text, double prezzo, Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        String idM;
        SELECT = Query.AGGIORNAMENTO_MANUTENZIONE_MAX_ID.getQuery();
        Object val = db.executeReturnQuery(SELECT).get(0).get("max");
        idM = (val == null) ? "0" : val.toString();
        idM = String.valueOf(Integer.parseInt(idM) + 1);

        INSERT = Query.AGGIORNAMENTO_MANUTENZIONE_TABELLA_MANUTENZIONE.getQuery(idM, text, prezzo, LocalDate.now(), k.getTarga());

        queryIndicator = db.executeUpdateQuery(INSERT);
        responder.sendResponse(clientSocket, queryIndicator);
    }

    /**
     * Metodo per aggiungere kart al noleggio,
     * togliendoli dalla concessionaria.
     * Il metodo utilizza un ciclo FOR per ciclare
     * le due query che deve effettuare per rimuovere
     * il kart dalle tabelle acquista e concessionaria.
     *
     * @param k Il kart da aggiungere
     * @param clientSocket Il socket di risposta
     */
    public void aggiuntaKart(Kart k, Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();

        DELETE_ITERATOR = new String[2];
        DELETE_ITERATOR[0] = Query.INSERIMENTO_KART_MECCANICO_TABELLA_ACQUISTA.getQuery(k.getTarga());
        DELETE_ITERATOR[1] = Query.INSERIMENTO_KART_MECCANICO_TABELLA_CONCESSIONARIA.getQuery(k.getTarga());

        for (String delete : DELETE_ITERATOR) {
            queryIndicator = db.executeUpdateQuery(delete);

            if ("0".equals(queryIndicator)) {
                responder.sendResponse(clientSocket, queryIndicator);
                return;
            }
        }
        responder.sendResponse(clientSocket, queryIndicator);
    }

    /**
     * Metodo per riempire completamente il serbatoio di un kart.
     * Il metodo utilizza la targa del kart ricevuta per modificarne il
     * serbatoio.
     *
     * @param k Il kart a cui aggiungere la benzina
     * @param clientSocket Il socket di risposta
     */
    public void aggiuntaBenzina(Kart k, Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();

        UPDATE = Query.AGGIUNTA_BENZINA_MECCANICO.getQuery(k.getTarga());
        queryIndicator = db.executeUpdateQuery(UPDATE);
        responder.sendResponse(clientSocket, queryIndicator);
    }

    /**
     * Metodo generico per mostrare i kart.
     * Tramite la query in ingresso si fa la distinzione tra mostrare i kart disponibili
     * all'aggiunta al noleggio e i kart disponibili alla completa rimozione dal
     * kartodromo.
     *
     * @param query La query da eseguire
     * @param clientSocket Il socket di risposta
     */
    public void mostraKart(String query, Socket clientSocket, String... colonne) {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        result = db.executeReturnQuery(query);

        if (result != null && !result.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Map<String, Object> row : result) {
                // Assicurati che i nomi siano esattamente quelli del database!
                Object targa = row.get("targa");
                Object cilindrata = row.get("cilindrata");
                Object serbatoio = row.get("serbatoio");

                sb.append(targa == null ? "" : targa.toString())
                        .append(" ")
                        .append(cilindrata == null ? "" : cilindrata.toString())
                        .append(" ")
                        .append(serbatoio == null ? "" : serbatoio.toString())
                        .append("\n");
            }
            responder.sendResponse(clientSocket, sb.toString());
        } else {
            responder.sendResponse(clientSocket, "end");
        }
    }


    /**
     * Metodo per rimuovere completamente un kart dal kartodromo.
     *
     * @param k Il kart da rimuovere
     * @param clientSocket Il socket di risposta
     */
    public void rimozioneKart(Kart k, Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        DELETE = Query.RIMUOVI_KART_MECCANICO.getQuery(k.getTarga());
        queryIndicator = db.executeUpdateQuery(DELETE);
        responder.sendResponse(clientSocket, queryIndicator);
    }
}
