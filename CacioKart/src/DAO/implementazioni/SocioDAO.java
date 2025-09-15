package DAO.implementazioni;

import DAO.interfacce.SocioDAOInterface;
import Enums.Query;
import Logic.DBConnector;
import Logic.Socio;
import Objects.Kart;
import Objects.Pezzo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class SocioDAO implements SocioDAOInterface {
    private final DBConnector db;

    public SocioDAO(DBConnector db) {
        this.db = db;
    }

/** Metodo per ottenere il kart posseduto dall'utente*/
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

/** Metodo per effettuare la registrazione*/
    @Override
    public int registrazione(Socio s) {

        String query = Query.REGISTRAZIONE_SOCIO.getQuery(
            s.getCf(), s.getNome(), s.getCognome(), s.getMail(), s.getPassword(), s.getDataNascita()
        );

        String out = db.executeUpdateQuery(query);
        return ((out == "0") ? 0 : 1);
    }


}
