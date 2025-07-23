package Command;


import Logic.Persona;
import WebTalker.PHPResponseHandler;

import java.net.Socket;

public class LoginCommand implements RequestCommand{
    public void execute(String loginInput, Socket clientSocket) throws Exception {
        try {
            System.out.println("Login ricevuto: " + loginInput);
            String[] loginData = loginInput.split(" ");

            if (loginData.length != 3) {
                new PHPResponseHandler().sendResponse(clientSocket, "Formato login non valido");
                return;
            }



            String cf = loginData[1];
            String password = loginData[2];

            Persona utente = new Persona();
            utente.setCf(cf);
            utente.setPassword(password);
            utente.login(clientSocket); // gestisce la risposta

        } catch (Exception e) {
            new PHPResponseHandler().sendResponse(clientSocket, "Errore login: " + e.getMessage());
        }
    }

}


