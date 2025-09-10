package DAO.interfacce;

import java.util.List;
import java.util.Map;

public interface PersonaDAOInterface {
    List<Map<String, Object>> login(String username, String password);
    int registrazione(String username, String password);
}
