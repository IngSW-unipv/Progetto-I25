package Strategy;

import Enums.Query;

public class ClassificaPenalitaStrategy implements ClassificaStrategy{
    private final String idGara;

    public ClassificaPenalitaStrategy(String idGara) {
        this.idGara = idGara;
    }

    @Override
    public String getQuery() {
        return Query.MOSTRA_CLASSIFICA_PENALITA.getQuery(idGara);
    }

    @Override
    public String[] getColumns() {
        return new String[]{"idGara", "socio", "targa", "bGiro", "tempTot"};
    }
}
