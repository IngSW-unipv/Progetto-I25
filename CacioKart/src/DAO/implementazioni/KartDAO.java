package DAO.implementazioni;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import DAO.interfacce.KartDAOInterface;
import Objects.Kart;
import Logic.DBConnector;
import Enums.Query;

/**
 * Implementazione concreta di KartDAOInterface.
 */
public class KartDAO implements KartDAOInterface {
    private static KartDAO instance;
    public KartDAO() {}
    public static KartDAO getInstance() {
        if (instance == null) instance = new KartDAO();
        return instance;
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
    public boolean deleteKart(Kart k) {
        DBConnector db = DBConnector.getInstance();
        String query = Query.RIMUOVI_KART_MECCANICO.getQuery(k.getTarga());
        String result = db.executeUpdateQuery(query);
        return result.equals("OK");
    }

    @Override
    public boolean refillKart(Kart k) {
        DBConnector db = DBConnector.getInstance();
        String query = Query.AGGIUNTA_BENZINA_MECCANICO.getQuery(k.getTarga());
        String result = db.executeUpdateQuery(query);
        return result.equals("OK");
    }

    @Override
    public List<Map<String, Object>> getKartManutenzione() {
        DBConnector db = DBConnector.getInstance();
        String query = Query.MOSTRA_KART_MANUTENZIONE.getQuery();
        return db.executeReturnQuery(query);
    }

    public boolean aggiungiKart(Kart kart, String cfSocio) {
        DBConnector db = DBConnector.getInstance();
        String insertKartQuery = String.format(
                Query.ACQUISTO_KART_UTENTE_TABELLA_ACQUISTA.getQuery(),
                kart.getTarga(), cfSocio
        );

        String updateSocioQuery = String.format(
                Query.ACQUISTO_KART_UTENTE_TABELLA_SOCIO.getQuery(),
                cfSocio
        );
        // Esegui le query
        int insertResult = Integer.parseInt(db.executeUpdateQuery(insertKartQuery));
        int updateResult = Integer.parseInt(db.executeUpdateQuery(updateSocioQuery));

        return insertResult > 0 && updateResult > 0;
    }

    public List<Map<String, Object>> getKartByCf(String cfSocio) {
        DBConnector db = DBConnector.getInstance();
        String query = String.format(
                Query.MOSTRA_KART_SOCIO.getQuery(),
                cfSocio
        );

        return db.executeReturnQuery(query);
    }
}



