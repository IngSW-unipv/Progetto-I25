package DAO;

import Enums.Query;
import Logic.DBConnector;
import Objects.Kart;
import Objects.Pezzo;
import Strategy.PrenotazioneStrategy;
import Strategy.PrenotazioneStrategyFactory;
import WebTalker.PHPResponseHandler;

import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class SocioDAO implements InterfacciaSocioDAO{
    private final DBConnector db;
    private String query;
    private List<Map<String, Object>> res;
    private String select;
    private String update;

    public SocioDAO(DBConnector db) {
        this.db = db;
    }

/**metodo per ottenere il kart posseduto dall'utente*/
    @Override
    public Kart getKart() {
        query = Query.MOSTRA_KART_SOCIO.getQuery();
        res = db.executeReturnQuery(query);
        if (res.isEmpty()) {
            return null;
        }
        Kart val = null;
        for(Map<String, Object> r : res){
            val = new Kart(r.get("targa").toString(), (Integer) r.get("cilindrata"), (Double) r.get("serbatoio"));
        }
        return val;
    }

    /**controllo della prenotazione
    (eventualmente inglobabile sotto, bisogna vedere)*/
    public int checkPrenotazione(String username, LocalDate data, LocalTime intervallo , LocalDate dataCorr){

        query = Query.PRENOTAZIONE_CONTEGGIO_POSTI_RIMASTI.getQuery(data, intervallo);
        res = db.executeReturnQuery(query);
        String prenotazioniConcorrenti = res.get(0).get("concurrent").toString();

        return (prenotazioniConcorrenti.equals("20")) ? 1 : 0;
    }

/**inserimento prenotazione Libera utente
 * @param dataCorr data corrente*/
    @Override
    public int prenotazione(String username, LocalDate data, LocalTime intervallo, LocalDate dataCorr){
        Socket clientSocket = null; //temp
        PHPResponseHandler responder = null; //!!!temporaneo, errore
        query = Query.PRENOTAZIONE_MAX_ID.getQuery();
        res = db.executeReturnQuery(query);
        String idPrenotazione;
        idPrenotazione = res.get(0).get("max").toString();
        idPrenotazione = (!idPrenotazione.equals("0")) ? String.valueOf(Integer.parseInt(idPrenotazione) + 1) : "1";
        PrenotazioneStrategy strategy = PrenotazioneStrategyFactory.getStrategy("libera");

        return strategy.eseguiPrenotazione(idPrenotazione, username, data, intervallo, data, db, responder, clientSocket);
    }

/**metodo per effettuare la registrazione*/
    @Override
    public int registrazione(String nome, String cognome, LocalDate dataNascita, String cF, String mail, String password) {

        query = Query.REGISTRAZIONE_SOCIO.getQuery(cF, nome, cognome, mail, password, dataNascita);

        String out = db.executeUpdateQuery(query);
        if(out.equals("0"))
            return 1; //preferisco usare 1 per segnalare un errore e
        return 0;     // 0 quando non ci sono errori
    }

/**metodo per effettuare l'acquisto di un kart
 * @param ora ora corrente*/
    @Override
    public int acquistaKart(String targa, String cF, LocalDateTime ora) {//LocalDateTime.now());
        update = Query.ACQUISTO_KART_UTENTE_TABELLA_SOCIO.getQuery(targa, cF);
        select = Query.ACQUISTO_KART_UTENTE_TROVA_ID_PRODOTTO.getQuery(targa);

        String queryIndicator = db.executeUpdateQuery(update);
        if (queryIndicator.equals("0")) {
            return 1;
        }

        List<Map<String, Object>> idProdotto = db.executeReturnQuery(select);
        query = Query.ACQUISTO_KART_UTENTE_TABELLA_ACQUISTA.getQuery(cF, idProdotto.get(0).get("idProdotto").toString(), ora);
        queryIndicator = db.executeUpdateQuery(query);

        return (queryIndicator.equals("0")) ? 1 : 0;
    }

/**Metodo per ottenere i pezzi acquistabili*/
    @Override
    public List<Pezzo> ottieniPezzi() {
        select = Query.MOSTRA_PEZZI_CONCESSIONARIA.getQuery();
        res = db.executeReturnQuery(select);
        List<Pezzo> result = null;
        for(Map<String, Object> i : res){
            result.add (
                new Pezzo(i.get("idProdotto").toString(), Integer.parseInt(i.get("quantita").toString()), i.get("descrizione").toString())
            );
        }
        return result;
    }

/**metodo per acquistare i pezzi ricevuti in precedenza
 * @param ora ora corrente*/
    @Override
    public int acquistaPezzi(String IDProdotto, int quantita, String username, LocalTime ora) {

        update = Query.ACQUISTA_PEZZI_TABELLA_CONCESSIONARIA.getQuery(IDProdotto);
        query = Query.ACQUISTA_PEZZI_TABELLA_ACQUISTA.getQuery(username, IDProdotto, ora);
        String[] queries = new String[2];
        queries[0] = update;
        queries[1] = query;

        String check;

        for (String prodotto : queries) {
            check = db.executeUpdateQuery(prodotto);

            if (check.equals("0")) {
                return 1;
            }
        }
        return 0;
    }

}
