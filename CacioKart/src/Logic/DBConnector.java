package Logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**Classe per istanziare connessioni con il database, eseguire query e restituire risultati*/

public class DBConnector {
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

    public DBConnector() {
    }

    /** Metodo per aprire la connessione al database.
     * Utilizzabile solo in questa classe dai metodi per
     * eseguire le varie query
     */
    private void dbOpenConnection() {
        try {
            fileReader = new BufferedReader(new FileReader("../Progetto-I25/CacioKart/Java docs/credenzialiDB.txt"));
            USER = fileReader.readLine();
            PASSWORD = fileReader.readLine();
            fileReader.close();
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (SQLException | IOException e) {
            System.out.println("L'apertura' della connessione al db non è andata a buon fine: " + e.getMessage());
        }
    }

    /** Metodo per chiudere la connessione al database.
     * Utilizzabile solo in questa classe dai metodi per
     * eseguire le varie query
     */
    private void dbCloseConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("La chiusura della connessione al db non è andata a buon fine: " + e.getMessage());
        }
    }

    /**
     * Metodo per eseguire query di lettura dati.
     * Dopo aver creato un'istanza di DBConnector, il metodo
     * istanzia una connessione aprendo anche un canale di comunicazione.
     * Viene creato uno statement, un oggetto da utilizzare per comunicare
     * con il db.
     * <p>
     * Tramite l'stmt appena creato eseguo la query richiesta dal chiamante.
     * Finché l'oggetto resultSet (classe utilizzata per ottenere i risultati
     * di una lettura da db di una determinata connessione) ha una riga che segue,
     * i risultati vengono copiati dentro a una struttura di tipo List Map<String, Object>>,
     * aggiungendoli di riga in riga.
     * <p>
     * Questa struttura è sviluppata in questo modo: ogni riga (numerata da 0 in avanti)
     * ha al suo interno delle chiavi (i nomi degli attributi) a cui sono associati
     * i valori (il valore di quella colonna in quella specifica riga).
     * Ad esempio nel caso in cui la query richieda le colonne "A, B, C" e vengano restituite
     * 3 righe, esse saranno strutturate in questo modo: <P>
     * Riga 0: A -> "Valore 1" B -> "Valore 2" C -> "Valore 3" <P>
     * Riga 1: A -> "Valore 4" B -> "Valore 5" C -> "Valore 6" <P>
     * Riga 2: A -> "Valore 7" B -> "Valore 8" C -> "Valore 9"
     * <p>
     * Per accedere ai valori della Map si utilizzano le chiavi, se volessi
     * sapere che cosa è contenuto in "Valore 5" utilizzerei:
     * Map.get(1).get("B")
     * <p>
     * Una volta finita la copiatura, si chiude la connessione e si restituisce
     * la Map al chiamante.
     *
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

    /** Metodo per eseguire query di scritture dati.
     * Il metodo esegue query che modificano i dati presenti nel database, cancellandone
     * o aggiungendone di nuovi.
     * In caso di errore (generalmente PK duplicata) dà come valore di risposta 0, altrimenti 1.
     *
     * @param query La query da eseguire
     */
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
