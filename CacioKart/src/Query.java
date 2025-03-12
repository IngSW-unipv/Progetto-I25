public enum Query {

    LOGIN_SOCIO("SELECT * FROM caciokart.socio WHERE socio = '%s' AND passw = '%S'"),
    LOGIN_DIPENDENTE("SELECT * FROM caciokart.dipendente WHERE dip = '%s' AND passw = '%s'");

    private final String query;

    Query(String query) {
        this.query = query;
    }

    // Metodo per ottenere la descrizione
    public String getQuery(Object... args) {
        if(args.length == 0) {
            return query;
        }
        return String.format(query, args);
    }


}
