public class Main {

    public static void main(String[] args) {
        //PHPRequestHandler p = new PHPRequestHandler();
        //p.handleRequests();
        WebConnector w = new WebConnector();
        w.createServer();
    }

}