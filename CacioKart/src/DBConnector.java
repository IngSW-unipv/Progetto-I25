import java.sql.*;

/** Classe per istanziare connessioni con il database, eseguire query e restituire risultati
 * Ver 1.0
 */
public class DBConnector {
    private final static String URL = "jdbc:mysql://localhost:3306/caciokart";
    private final static String USER = "root";
    private final static String PASSWORD = "";
    private Connection conn;

    public DBConnector() {
    }

    /** Metodo per aprire la connessione al database.
     * Utilizzabile solo in questa classe dai metodi per
     * eseguire le varie query
     *
     * @throws SQLException
     */
    private void dbOpenConnection() throws SQLException {
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /** Metodo per chiudere la connessione al database.
     * Utilizzabile solo in questa classe dai metodi per
     * eseguire le varie query
     *
     * @throws SQLException
     */
    private void dbCloseConnection() throws SQLException {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /** Metodo per eseguire query di lettura dati.
     * ResultSet permette alla classe chiamante di ricevere
     * i dati richiesti. La presenza del throws e del try-catch
     * costringe la classe chiamante a gestire un'eventuale eccezione
     *
     * @param query
     * @return
     * @throws SQLException
     */
    public ResultSet executeReturnQuery(String query) throws SQLException {
        try {
            DBConnector db = new DBConnector();
            db.dbOpenConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            db.dbCloseConnection();
            return rs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /** Metodo per eseguire query di scritture dati.
     * Tramite la stringa in ingresso, il metodo esegue query
     * che modificano i dati presenti nel database, cancellando
     * o aggiungendone di nuovi. La presenza del throws e del try-catch
     * costringe la classe chiamante a gestire un'eventuale eccezione
     *
     * @param query
     * @throws SQLException
     */
    public void executeUpdateQuery(String query) throws SQLException {
        try {
            DBConnector db = new DBConnector();
            db.dbOpenConnection();
            Statement stmt = db.conn.createStatement();
            System.out.println("Executing query: " + query);
            stmt.executeUpdate(query);
            db.dbCloseConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
