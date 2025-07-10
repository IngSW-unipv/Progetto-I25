package Logic;

import WebTalker.PHPResponseHandler;

import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;

public class MostraPezziCommand implements RequestCommand{
    @Override
    public void execute(String in, Socket clientSocket) throws Exception {
        ConcessionariaService service = new ConcessionariaService();
        List<Map<String, Object>> result = service.mostraPezzi();
        PHPResponseHandler responder = new PHPResponseHandler();
        if (result != null && !result.isEmpty()) {
            TableMaker maker = new TableMaker();
            responder.sendResponse(clientSocket, maker.stringTableMaker(
                    result, "idProdotto", "tipol", "quantita", "prezzo"
            ));
        } else {
            responder.sendResponse(clientSocket, "end");
        }
    }
}
