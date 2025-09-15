package Logic;

import Objects.Kart;
import java.util.List;
import java.util.Map;

public class TableMaker {
    private StringBuilder returnString;

    public TableMaker() {

    }

    public String kartListToString(List<Kart> lista) {
        if (lista == null || lista.isEmpty()) {
            return "Nessun kart trovato\nend";
        }
        StringBuilder sb = new StringBuilder();
        for (Kart k : lista) {
            sb.append(k.getTarga()).append(" ")
                    .append(k.getCilindrata()).append(" ")
                    .append(k.getSerbatoio()).append("\n");
        }
        sb.append("end");
        return sb.toString();
    }

    public String stringTableMaker(List<Map<String, Object>> inputMap, String... attributi) {

        returnString = new StringBuilder();

        for (Map<String, Object> row : inputMap) {
            for (String key : attributi) {
                returnString.append(row.get(key).toString()).append(" ");
            }
            returnString.append("\n");
        }
        returnString.append("end");
        return returnString.toString();
    }
}