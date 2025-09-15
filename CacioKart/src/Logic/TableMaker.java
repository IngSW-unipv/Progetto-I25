package Logic;

import java.util.List;
import java.util.Map;

public class TableMaker {
    private StringBuilder returnString;

    public TableMaker() {

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