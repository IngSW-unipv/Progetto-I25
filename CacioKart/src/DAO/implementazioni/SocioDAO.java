package DAO.implementazioni;

import DAO.interfacce.InterfacciaSocioDAO;
import Enums.Query;
import Logic.DBConnector;
import Logic.Socio;
import Objects.Kart;
import Objects.Pezzo;
import Strategy.PrenotazioneStrategy;
import Strategy.PrenotazioneStrategyFactory;
import WebTalker.PHPResponseHandler;

import java.net.Socket;
import java.net.StandardSocketOptions;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class SocioDAO implements InterfacciaSocioDAO {
    private final DBConnector db;

    public SocioDAO(DBConnector db) {
        this.db = db;
    }

/**metodo per ottenere il kart posseduto dall'utente*/
    @Override
    public Kart getKart() {
        String query = Query.MOSTRA_KART_SOCIO.getQuery();
        List<Map<String, Object>>res = db.executeReturnQuery(query);
        if (res.isEmpty()) {
            return null;
        }
        Kart val = null;
        for(Map<String, Object> r : res){
            val = new Kart(r.get("targa").toString(), (Integer) r.get("cilindrata"), (Double) r.get("serbatoio"));
        }
        return val;
    }

/**metodo per effettuare la registrazione*/
    @Override
    public int registrazione(Socio s) {

        String query = Query.REGISTRAZIONE_SOCIO.getQuery(
            s.getCf(), s.getNome(), s.getCognome(), s.getMail(), s.getPassword(), s.getDataNascita()
        );

        String out = db.executeUpdateQuery(query);
        return ((out == "0") ? 0 : 1);
        /*if(out.equals("0"))
            return 1; //preferisco usare 1 per segnalare un errore e
        return 0;     // 0 quando non ci sono errori*/
    }

/**metodo per effettuare l'acquisto di un kart
 * @param ora ora corrente*/
    @Override
    public int acquistaKart(String targa, String cF, LocalDateTime ora) {//LocalDateTime.now());
        String query = Query.ACQUISTO_KART_UTENTE_TABELLA_SOCIO.getQuery(targa, cF);


        String queryIndicator = db.executeUpdateQuery(query);
        if (queryIndicator.equals("0")) {
            return 1;
        }

        query = Query.ACQUISTO_KART_UTENTE_TROVA_ID_PRODOTTO.getQuery(targa);

        List<Map<String, Object>> idProdotto = db.executeReturnQuery(query);
        query = Query.ACQUISTO_KART_UTENTE_TABELLA_ACQUISTA.getQuery(cF, idProdotto.get(0).get("idProdotto").toString(), ora);
        queryIndicator = db.executeUpdateQuery(query);

        return (queryIndicator.equals("0")) ? 1 : 0;
    }

/**Metodo per ottenere i pezzi acquistabili*/
    @Override
    public List<Pezzo> ottieniPezzi() {
        String query = Query.MOSTRA_PEZZI_CONCESSIONARIA.getQuery();
        List<Map<String, Object>> res = db.executeReturnQuery(query);
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

        String[] queries = new String[2];
        queries[0] = Query.ACQUISTA_PEZZI_TABELLA_CONCESSIONARIA.getQuery(IDProdotto);
        queries[1] = Query.ACQUISTA_PEZZI_TABELLA_ACQUISTA.getQuery(username, IDProdotto, ora);
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
