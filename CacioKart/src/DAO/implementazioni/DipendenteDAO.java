package DAO.implementazioni;

import DAO.interfacce.DipendenteDAOInterface;
import Enums.Query;
import Logic.DBConnector;
import Objects.Dipendente;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Implementazione concreta di DipendenteDAOInterface.
 */
public class DipendenteDAO implements DipendenteDAOInterface {
    private final DBConnector db;

    public DipendenteDAO(DBConnector db) {
        this.db = db;
    }

    @Override
    public void inserisciDipendente(Dipendente d) {
        String dataNascita = d.getDataNascita().toString();
        String oreL = d.getOreL().toString();
        String query = String.format(
                Query.AGGIUNTA_DIPENDENTE_PROPRIETARIO.getQuery(),
                d.getCf(),
                d.getNome(),
                d.getCognome(),
                d.getMail(),
                d.getPassword(),
                dataNascita,
                d.getRuolo(),
                oreL,
                d.getStipendio()
        );
        db.executeUpdateQuery(query);
    }

    @Override
    public void rimuoviDipendente(String cf) {
        String query = String.format(
                Query.RIMOZIONE_DIPENDENTE_PROPRIETARIO.getQuery(),
                cf
        );
        db.executeUpdateQuery(query);
    }

    @Override
    public List<Dipendente> mostraDipendenti() {
        String query = Query.MOSTRA_DIPENDENTI_PROPRIETARIO.getQuery();
        List<Map<String, Object>> result = db.executeReturnQuery(query);
        List<Dipendente> lista = new ArrayList<>();
        if (result != null) {
            for (Map<String, Object> row : result) {
                Dipendente d = new Dipendente();
                d.setNome(row.get("nome").toString());
                d.setCognome(row.get("cognome").toString());
                d.setMail(row.get("mail").toString());
                d.setPassword(row.get("passw").toString());
                d.setCf(row.get("dip").toString());
                d.setDataNascita(java.time.LocalDate.parse(row.get("dataN").toString()));
                d.setRuolo(row.get("ruolo").toString());
                d.setOreL(java.time.LocalTime.parse(row.get("oreL").toString()));
                d.setStipendio(Double.parseDouble(row.get("stipendio").toString()));
                lista.add(d);
            }
        }
        return lista;
    }
}
