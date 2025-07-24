package DAO;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import Objects.Kart;
import Logic.DBConnector;
import Enums.Query;

public class KartDAO {
    private static KartDAO instance;
    private KartDAO() {}
    public static KartDAO getInstance() {
        if (instance == null) instance = new KartDAO();
        return instance;
    }

    public List<Kart> getAllKart() {
        List<Kart> lista = new ArrayList<>();
        DBConnector db = DBConnector.getInstance();
        String query = Query.MOSTRA_RIMUOVI_KART_MECCANICO.getQuery();
        List<Map<String, Object>> result = db.executeReturnQuery(query);

        for (Map<String, Object> r : result) {
            String targa = (String) r.get("targa");
            int cilindrata = Integer.parseInt(r.get("cilindrata").toString());
            double serbatoio = Double.parseDouble(r.get("serbatoio").toString());
            lista.add(new Kart(targa, cilindrata, serbatoio));
        }
        return lista;
    }



    // Restituisce solo i kart disponibili per aggiunta (usa la query filtrata che hai già)
    public List<Kart> getAllKartDisponibiliPerAggiunta() {
        List<Kart> lista = new ArrayList<>();
        DBConnector db = DBConnector.getInstance();
        String query = Query.MOSTRA_AGGIUNTA_KART_MECCANICO.getQuery();
        List<Map<String, Object>> result = db.executeReturnQuery(query);

        for (Map<String, Object> r : result) {
            String targa = (String) r.get("targa");
            int cilindrata = Integer.parseInt(r.get("cilindrata").toString());
            double serbatoio = Double.parseDouble(r.get("serbatoio").toString());
            lista.add(new Kart(targa, cilindrata, serbatoio));
        }
        return lista;
    }


    // Inserisce un nuovo kart nella tabella
    public boolean insertKart(Kart k) {
        DBConnector db = DBConnector.getInstance();
        String query = Query.INSERIMENTO_KART_CONCESSIONARIA.getQuery(
                k.getTarga(),
                k.getCilindrata(),
                k.getSerbatoio()
        );
        String result = db.executeUpdateQuery(query);
        return result.equals("OK");
    }

    // Elimina un kart dalla tabella (usando la targa)
    public boolean deleteKart(Kart k) {
        DBConnector db = DBConnector.getInstance();
        String query = Query.RIMUOVI_KART_MECCANICO.getQuery(k.getTarga());
        String result = db.executeUpdateQuery(query);
        return result.equals("OK");
    }

    // Esegue il pieno (serbatoio = 20) su un kart
    public boolean refillKart(Kart k) {
        DBConnector db = DBConnector.getInstance();
        String query = Query.AGGIUNTA_BENZINA_MECCANICO.getQuery(k.getTarga());
        String result = db.executeUpdateQuery(query);
        return result.equals("OK");
    }

    public List<Map<String, Object>> getKartManutenzione() {
        DBConnector db = DBConnector.getInstance();
        String query = Query.MOSTRA_KART_MANUTENZIONE.getQuery();
        return db.executeReturnQuery(query);
    }

}
