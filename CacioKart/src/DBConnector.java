import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Classe per istanziare connessioni con il database, eseguire query e restituire risultati
 */
public class DBConnector {
    private final static String URL = "jdbc:mysql://localhost:3306/caciokart";
    private static String USER/* = "root"*/;
    private static String PASSWORD/* = ""*/;
    private Connection conn;
    private List<Map<String, Object>> resultList = new ArrayList<>();
    private ResultSet rs;
    private Statement stmt;
    private DBConnector db;
    private ResultSetMetaData rsmd;
    private int columnCount;
    private BufferedReader fileReader;
    private String columnName;
    private Object columnValue;

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
            fileReader = new BufferedReader(new FileReader("../Progetto-I25/CacioKart/credenzialiDB.txt"));
            USER = fileReader.readLine();
            PASSWORD = fileReader.readLine();
            fileReader.close();
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (SQLException | IOException e) {
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
     * Il metodo riceve in ingresso la query richiesta e
     * ha come valore di ritorno una Map contenente i dati trovati
     * dalla query.
     *
     * @param query
     * @return
     * @throws SQLException
     */
    public List<Map<String, Object>> executeReturnQuery(String query) throws SQLException {
        try {
            db = new DBConnector();
            db.dbOpenConnection();
            stmt = db.conn.createStatement();
            rs = stmt.executeQuery(query);
            //System.out.println("Eseguo la query di lettura richiesta: " + query);
            rsmd = rs.getMetaData();
            columnCount = rsmd.getColumnCount();

            while(rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for(int i = 1; i <= columnCount; i++) {
                    columnName = rsmd.getColumnName(i);
                    columnValue = rs.getObject(i);
                    row.put(columnName, columnValue);
                }
                resultList.add(row);
            }
            db.dbCloseConnection();
            return resultList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /** Metodo per eseguire query di scritture dati.
     * Tramite la stringa in ingresso, il metodo esegue query
     * che modificano i dati presenti nel database, cancellando
     * o aggiungendone di nuovi.
     * In caso di errore (principalmente di PK duplicata) dà come
     * valore di risposta 0.
     *
     * @param query
     * @throws SQLException
     */
    public int executeUpdateQuery(String query) throws SQLException {
        try {
            db = new DBConnector();
            db.dbOpenConnection();
            stmt = db.conn.createStatement();
            stmt.executeUpdate(query);
            //System.out.println("Eseguo la query di modifica richiesta: " + query);
            db.dbCloseConnection();
            return 1;

        } catch (SQLException e) {
            System.out.println("L'inserimento non è andato a buon fine: " + e.getMessage());
            db.dbCloseConnection();
            return 0;

        }
    }

    public Connection getConnection() {
        return conn;
    }
}
