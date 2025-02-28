import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class Concessionaria implements Iinventario{
    //List<Kart> kart;
    //List<Pezzo> pezzi;
    private DBConnector db;
    private PHPResponseHandler responder;
    private String INSERT;
    private String targa;
    private int cilindrata;
    private double serbatoio;
    private int insertError;

    public Concessionaria(/*List<Pezzo> pezzi, List<Kart> kart*/) {
        //this.pezzi = pezzi;
        //this.kart = kart;
    }

    //Override metodi Iinventario

    public void venditaKart(){

    };

    /**Metodo per inserire i kart nella concessionaria.
     * In ingresso prendiamo i dati del kart e il socket per spedire la risposta.
     * Creo un oggetto di tipo Kart in modo da poterlo manipolare con i vari get,
     * dopodiché utilizzo il metodo di DBConnector per inserire il nuovo kart e
     * il metodo di ResponseHandler per mandare una risposta
     *
     * @param nuovoKart
     * @param clientSocket
     */
    public void inserimentoKart(Kart nuovoKart, Socket clientSocket) throws SQLException {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        INSERT = "INSERT INTO kart (targa, cilindrata, serbatoio) VALUES('" +
                nuovoKart.getTarga() + "', '" +
                nuovoKart.getCilindrata() + "', '" +
                nuovoKart.getSerbatoio() +"')";

        insertError = db.executeUpdateQuery(INSERT);
        responder.sendResponse(clientSocket, Integer.toString(insertError));

    };

    public void inserimentoPezzo(){

    };
}
