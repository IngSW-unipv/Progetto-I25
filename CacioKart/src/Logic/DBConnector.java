package Logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe per istanziare connessioni con il database, eseguire query e restituire risultati
 * Implementazione Singleton: si può avere solo un'istanza di DBConnector
 */
public class DBConnector {
    // Istanza unica della classe (Singleton)
    private static DBConnector instance = null;

    private BufferedReader fileReader;
    private final static String URL = "jdbc:mysql://localhost:3306/caciokart";
    private static String USER, PASSWORD;
    private Connection conn;

    private final List<Map<String, Object>> resultList = new ArrayList<>();
    private ResultSet rs;
    private ResultSetMetaData rsmd;

    private Statement stmt;

    private int columnCount;
    private String columnName, columnValue;

    // Costruttore privato
    private DBConnector() {
    }

    // Metodo statico per ottenere l'istanza unica
    public static DBConnector getInstance() {
        if (instance == null) {
            instance = DBConnector.getInstance();
        }
        return instance;
    }

    /** Metodo per aprire la connessione al database */
    private void dbOpenConnection() {
        try {
            fileReader = new BufferedReader(new FileReader("../Progetto-I25/CacioKart/Java docs/credenzialiDB.txt"));
            USER = fileReader.readLine();
            PASSWORD = fileReader.readLine();
            fileReader.close();
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (SQLException | IOException e) {
            System.out.println("L'apertura della connessione al db non è andata a buon fine: " + e.getMessage());
        }
    }

    /** Metodo per chiudere la connessione al database */
    private void dbCloseConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("La chiusura della connessione al db non è andata a buon fine: " + e.getMessage());
        }
    }

    /**
     * Metodo per eseguire query di lettura dati.
     * @param query La query da eseguire
     * @return Una Map con dentro i risultati della lettura
     */
    public List<Map<String, Object>> executeReturnQuery(String query) {
        try {
            this.dbOpenConnection();
            stmt = this.conn.createStatement();
            rs = stmt.executeQuery(query);
            System.out.println("Eseguo la query di lettura richiesta: " + query);
            rsmd = rs.getMetaData();
            columnCount = rsmd.getColumnCount();

            // Pulisce la lista prima di aggiungere nuovi risultati
            resultList.clear();

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    columnName = rsmd.getColumnName(i);
                    columnValue = rs.getString(i);
                    row.put(columnName, columnValue);
                }
                resultList.add(row);
            }
            this.dbCloseConnection();
            return resultList;

        } catch (SQLException e) {
            System.out.println("La lettura non è andata a buon fine: " + e.getMessage());
            this.dbCloseConnection();
            return null;
        }

    }

    /** Metodo per eseguire query di scrittura dati */
    public String executeUpdateQuery(String query) {
        try {
            this.dbOpenConnection();
            stmt = this.conn.createStatement();
            int affectedRows = stmt.executeUpdate(query);
            System.out.println("Eseguo la query di modifica richiesta: " + query);
            this.dbCloseConnection();
            if (affectedRows > 0) {
                return "OK";
            } else {
                System.out.println("Nessuna riga modificata/eliminata.");
                return "0";
            }
        } catch (SQLException e) {
            System.out.println("L'inserimento non è andato a buon fine: " + e.getMessage());
            this.dbCloseConnection();
            return "0";
        }
    }

    public Connection getConnection() {
        return conn;
    }
}
