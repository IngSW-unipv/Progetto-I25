package Logic;

import java.util.List;
import java.util.Map;

public class TableMaker {
    private StringBuilder returnString;

    public TableMaker() {

    }

    /**
     * Metodo per rispondere alla richiesta di table.
     * Richieste come la classifica ad esempio vogliono ricevere una stringa
     * unica separata da spazi per delimitare i dati, terminatori di linea
     * per delimitare le righe e "end" per indicare il termine dei dati.
     * Questo metodo fornisce a queste classi una funzione unica a cui fare riferimento
     * per creare tabelle.
     *
     * @param inputMap La Map che contiene i risultati della query al db
     *                 da interpretare.
     * @param attributi Il nome delle colonne della query in esame. Dato che deve coincidere
     *                  ogni volta con una query diversa, il chiamante deve avere la
     *                  responsabilità di specificare tutte le colonne manualmente.
     *                  È sotto forma di String... perché il numero di attributi
     *                  può variare.
     * @return Lo StringBuilder sotto forma di String con il messaggio pronto ad essere spedito.
     */
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
