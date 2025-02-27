import java.io.File;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        WebConnector w = new WebConnector();
        w.createServer();
    }

}