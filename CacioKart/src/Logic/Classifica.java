package Logic;

import Strategy.ClassificaStrategy;
import WebTalker.PHPResponseHandler;

import java.net.Socket;
import java.util.List;
import java.util.Map;

public class Classifica {
    private DBConnector db;
    private PHPResponseHandler responder;
    private List<Map<String, Object>> result;
    private String SELECT;
    private TableMaker maker;

    public Classifica() {
    }

    public void generaClassifica(ClassificaStrategy strategy, Socket clientSocket) {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        result = db.executeReturnQuery(strategy.getQuery());

        if (result != null) {
            maker = new TableMaker();
            responder.sendResponse(clientSocket, maker.stringTableMaker(result, strategy.getColumns()));
        } else {
            responder.sendResponse(clientSocket, "end");
        }
    }
}
