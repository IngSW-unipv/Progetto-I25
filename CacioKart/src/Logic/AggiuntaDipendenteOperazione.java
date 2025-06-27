package Logic;

import Enums.Query;
import Objects.Dipendente;
import WebTalker.PHPResponseHandler;

import java.net.Socket;

public class AggiuntaDipendenteOperazione  implements OperazioneProprietario {
    private final DBConnector db;
    private final PHPResponseHandler responder;
    private final Dipendente nuovoDip;

    public AggiuntaDipendenteOperazione(DBConnector db, PHPResponseHandler responder, Dipendente nuovoDip) {
        this.db = db;
        this.responder = responder;
        this.nuovoDip = nuovoDip;
    }

    @Override
    public void esegui(Socket clientSocket) {
        String INSERT = Query.AGGIUNTA_DIPENDENTE_PROPRIETARIO.getQuery(
                nuovoDip.getCf(), nuovoDip.getNome(), nuovoDip.getCognome(), nuovoDip.getMail(),
                nuovoDip.getPassword(), nuovoDip.getDataNascita(), nuovoDip.getRuolo(),
                nuovoDip.getOreL(), nuovoDip.getStipendio()
        );
        String risultato = db.executeUpdateQuery(INSERT);
        responder.sendResponse(clientSocket, risultato);
    }
}
