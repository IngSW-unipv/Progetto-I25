package DAO.implementazioni;

import DAO.interfacce.ManutenzioneDAOInterface;
import Objects.Kart;
import Logic.DBConnector;
import java.time.LocalDate;

/**
 * Implementazione concreta di ManutenzioneDAOInterface.
 */
public class ManutenzioneDAO implements ManutenzioneDAOInterface {
    private static ManutenzioneDAO instance;
    private ManutenzioneDAO() {}
    public static ManutenzioneDAO getInstance() {
        if (instance == null) instance = new ManutenzioneDAO();
        return instance;
    }

    @Override
    public boolean insertManutenzione(Kart kart, String tipoInt, double costo) {
        DBConnector db = DBConnector.getInstance();
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
