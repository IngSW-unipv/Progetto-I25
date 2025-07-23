package Logic;

import Enums.Query;

public class ClassificaUtenteStrategy implements  ClassificaStrategy{
    private final Socio socio;

    public ClassificaUtenteStrategy(Socio socio) {
        this.socio = socio;
    }

    @Override
    public String getQuery() {
        return Query.CLASSIFICA_UTENTE.getQuery(socio.getCf());
    }

    @Override
    public String[] getColumns() {
        return new String[]{"idGara", "targa", "bGiro", "tempTot"};
    }
}

