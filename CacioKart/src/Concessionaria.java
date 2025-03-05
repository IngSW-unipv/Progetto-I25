import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Concessionaria implements Iinventario{
    //List<Kart> kart;
    //List<Pezzo> pezzi;
    private DBConnector db;
    private PHPResponseHandler responder;
    private String INSERT;
    private String SELECT;
    private String targa;
    private int cilindrata;
    private double serbatoio;
    private int queryResult;
    private List<Map<String, Object>> maxIDProdotto;
    private int idProdotto;
    private String ultimoProdotto;

    public Concessionaria(/*List<Pezzo> pezzi, List<Kart> kart*/) {
        //this.pezzi = pezzi;
        //this.kart = kart;
    }

    //Override metodi Iinventario

    public void venditaKart(){

    };

    /**
     * Metodo per inserire i kart nella concessionaria.
     * In ingresso prendiamo i dati del kart e il socket per spedire la risposta.
     * Creo un oggetto di tipo Kart in modo da poterlo manipolare con i vari get,
     * dopodiché utilizzo il metodo di DBConnector per inserire il nuovo kart e
     * il metodo di ResponseHandler per mandare una risposta
     *
     * @param nuovoKart
     * @param prezzo
     * @param clientSocket
     */
    public void inserimentoKart(Kart nuovoKart, int prezzo, Socket clientSocket) throws SQLException {
        db = new DBConnector();
        responder = new PHPResponseHandler();
        //devo aggiungere il kart in kart
        INSERT = "INSERT INTO kart (targa, cilindrata, serbatoio) VALUES('" +
                nuovoKart.getTarga() + "', '" +
                nuovoKart.getCilindrata() + "', '" +
                nuovoKart.getSerbatoio() +"')";
        db.executeUpdateQuery(INSERT);

        SELECT = "SELECT MAX(idProdotto) FROM concessionaria";
        maxIDProdotto = db.executeReturnQuery(SELECT);
        //System.out.println(maxIDProdotto.get(0));

        //Logica per rimuovere tutti i caratteri tranne i numeri
        ultimoProdotto = maxIDProdotto.get(0).toString().replaceAll("\\D", "");

        if(ultimoProdotto == ""){
            idProdotto = 1;

        }else {
            idProdotto = Integer.parseInt(ultimoProdotto) + 1;

        }
        //e impostare concessionario tipol
        INSERT = "INSERT INTO concessionaria (idProdotto, tipol, quantita, prezzo) VALUES('" +
                idProdotto + "', '" +
                nuovoKart.getTarga() + "', '" +
                1 + "', '" +
                prezzo +"')";

        queryResult = db.executeUpdateQuery(INSERT);
        responder.sendResponse(clientSocket, Integer.toString(queryResult));

    };

    public void inserimentoPezzo(){

    };
}
