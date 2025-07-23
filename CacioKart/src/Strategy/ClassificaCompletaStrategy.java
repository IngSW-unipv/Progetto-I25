package Strategy;

import Enums.Query;

public class ClassificaCompletaStrategy implements ClassificaStrategy {
    @Override
    public String getQuery() {
        return Query.CLASSIFICA_GENERALE.getQuery();
    }

    @Override
    public String[] getColumns() {
        return new String[]{"idGara", "nome", "cognome", "targa", "bGiro", "tempTot"};
    }
}
