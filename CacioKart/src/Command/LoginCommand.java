package Command;


import DAO.implementazioni.PersonaDAO;
import Enums.Ruoli;
import Objects.Persona;
import WebTalker.PHPResponseHandler;

import java.net.Socket;
import java.util.List;
import java.util.Map;

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
            PHPResponseHandler responder = new PHPResponseHandler();

            PersonaDAO PDAO = new PersonaDAO();
            List<Map<String, Object>> result = PDAO.login(utente.getCf(), utente.getPassword());

            if (result.isEmpty()) {
                responder.sendResponse(clientSocket, "0 0 0");

            } else {
                Object nomeObj = result.getFirst().get("nome");
                String nome = (nomeObj == null) ? "" : nomeObj.toString();
                Integer usr = Integer.parseInt(result.get(1).get("usr").toString());
                if(usr == 1){
                    responder.sendResponse(clientSocket, "1 0 " + nome);
                }else{
                    String ruolo = result.getFirst().get("ruolo").toString();
                    Ruoli ruoloConverter = Ruoli.requestedRole(ruolo);

                    switch (ruoloConverter) {
                        case MECCANICO:
                            responder.sendResponse(clientSocket, "1 1 " + nome);
                            break;

                        case GESTORE:
                            responder.sendResponse(clientSocket, "1 2 " + nome);
                            break;

                        case ARBITRO:
                            responder.sendResponse(clientSocket, "1 3 " + nome);
                            break;

                        case ORGANIZZATORE:
                            responder.sendResponse(clientSocket, "1 4 " + nome);
                            break;

                        case PROPRIETARIO:
                            responder.sendResponse(clientSocket, "1 5 " + nome);
                            break;

                        default:
                            /* Il PHP invierà sempre risposte esatte, questa risposta non è
                             * praticamente mai necessaria.
                             */
                            responder.sendResponse(clientSocket, "0 0 0");
                            System.out.println("Ruolo non trovato\n");
                            break;
                    }
                }
            }

        } catch (Exception e) {
            new PHPResponseHandler().sendResponse(clientSocket, "Errore login: " + e.getMessage());
        }
    }

}


