package DAO.implementazioni;

import DAO.interfacce.PersonaDAOInterface;
import Enums.Query;
import Logic.DBConnector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonaDAO implements PersonaDAOInterface {

    @Override
    public List<Map<String, Object>> login(String username, String password) {
        DBConnector db = DBConnector.getInstance();
        String query = Query.LOGIN_SOCIO.getQuery(username, password);

        List<Map<String, Object>> result = db.executeReturnQuery(query);
        Map<String, Object> val = new HashMap<String, Object>();


        if(result.isEmpty()){
            String query2 = Query.LOGIN_DIPENDENTE.getQuery(username, password);
            result = db.executeReturnQuery(query2);

            val.put("usr", "0");
        } else{
            val.put("usr", "1");
        }

        result.add(val);
        return result;
    }

    @Override
    public int registrazione(String username, String password) {
        return 0;
    }
}
