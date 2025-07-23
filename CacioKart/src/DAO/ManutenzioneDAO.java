package DAO;

import Objects.Kart;
import Logic.DBConnector;
import java.time.LocalDate;

public class ManutenzioneDAO {
    private static ManutenzioneDAO instance;
    private ManutenzioneDAO() {}
    public static ManutenzioneDAO getInstance() {
        if (instance == null) instance = new ManutenzioneDAO();
        return instance;
    }

    public boolean insertManutenzione(Kart kart, String tipoInt, double costo) {
        DBConnector db = new DBConnector();
        String dataM = LocalDate.now().toString();

        // idM viene gestito direttamente dal DB (AUTO_INCREMENT)
        String query = String.format(
                "INSERT INTO manutenzione (tipoInt, costo, dataM, targa) VALUES ('%s', '%s', '%s', '%s')",
                tipoInt, costo, dataM, kart.getTarga()
        );
        String result = db.executeUpdateQuery(query);
        return "OK".equals(result);
    }
}
