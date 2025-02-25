import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class Concessionaria implements Iinventario{
    //List<Kart> kart;
    //List<Pezzo> pezzi;
    PHPResponseHandler responder;
    private String INSERT;
    private String targa;
    private int cilindrata;
    private double serbatoio;

    public Concessionaria(/*List<Pezzo> pezzi, List<Kart> kart*/) {
        //this.pezzi = pezzi;
        //this.kart = kart;
    }

    //Override metodi Iinventario

    public void venditaKart(){

    };

    public void inserimentoKart(String kart[], Socket clientSocket){
        targa = kart[0];
        cilindrata = Integer.parseInt(kart[1]);
        serbatoio = Double.parseDouble(kart[2]);

        Kart nuovoKart = new Kart(targa, cilindrata, serbatoio);
        DBConnector db = new DBConnector();
        responder = new PHPResponseHandler();
        INSERT = "INSERT INTO kart (targa, cilindrata, serbatoio) VALUES('" +
                nuovoKart.getTarga() + "', '" +
                nuovoKart.getCilindrata() + "', '" +
                nuovoKart.getSerbatoio() +"')";
        responder.sendResponse(clientSocket, "1");
        System.out.println("Ho mandato la risposta\n");

        try {
            db.executeUpdateQuery(INSERT);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    };

    public void inserimentoPezzo(){

    };
}
