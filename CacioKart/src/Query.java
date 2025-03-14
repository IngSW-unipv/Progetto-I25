public enum Query {

    LOGIN_SOCIO("SELECT * FROM caciokart.socio WHERE socio = '%s' AND passw = '%S'"),

    LOGIN_DIPENDENTE("SELECT * FROM caciokart.dipendente WHERE dip = '%s' AND passw = '%s'"),

    CLASSIFICA_ARBITRO("SELECT DISTINCT idGara FROM caciokart.classifica ORDER BY idGara"),

    CLASSIFICA_GENERALE("SELECT c.idGara, s.nome, s.cognome, c.targa, c.bGiro, c.tempTot " +
            "FROM caciokart.classifica AS c " +
            "JOIN (SELECT idGara, MIN(tempTot) AS tempo_migliore FROM caciokart.classifica GROUP BY idGara) AS agg " +
            "ON c.idGara = agg.idGara AND c.tempTot = agg.tempo_migliore " +
            "JOIN caciokart.socio AS s ON c.socio = s.socio " +
            "ORDER BY c.tempTot DESC " +
            "LIMIT 10"),

    CLASSIFICA_UTENTE("SELECT c.idGara, " +
            "       c.targa, " +
                    "       c.bGiro, " +
                    "       c.tempTot " +
                    "FROM caciokart.classifica AS c " +
                    "JOIN caciokart.socio AS s ON c.socio = s.socio " +
                    "WHERE s.nome = '%s' " +
                    "ORDER BY c.tempTot DESC " +
                    "LIMIT 10"),

    REGISTRAZIONE_SOCIO("INSERT INTO socio (socio, nome, cognome, mail, passw, dataN) " +
            "VALUES('%s', '%s', '%s', '%s', '%s', '%s')"),

    PRENOTAZIONE_CONTEGGIO_POSTI_RIMASTI("SELECT count(*) FROM caciokart.prenotazione " +
            "WHERE dataG = '%s' AND fasciaO = '%s'"),

    PRENOTAZIONE_MAX_ID("SELECT MAX(idP) FROM PRENOTAZIONE"),

    PRENOTAZIONE_LIBERA_INSERIMENTO_1("INSERT INTO prenotazione (idP, dataG , fasciaO, tipologia, costo) " +
            "VALUES('%s', '%s', '%s', '%s', '%s')"),

    PRENOTAZIONE_LIBERA_INSERIMENTO_2("INSERT INTO prenota (idP, socio,data) " +
            "VALUES ('%s', '%s', '%s')"),

    INSERIMENTO_KART_CONCESSIONARIA_TABELLA_KART("INSERT INTO kart (targa, cilindrata, serbatoio) " +
            "VALUES('%s', '%s', '%s')"),

    INSERIMENTO_KART_CONCESSIONARIA_MAX_ID("SELECT MAX(idProdotto) FROM concessionaria"),

    INSERIMENTO_KART_CONCESSIONARIA_TABELLA_CONCESSIONARIA("INSERT INTO concessionaria (idProdotto, tipol, quantita, prezzo) " +
            "VALUES('%s', '%s', '%s', '%s')"),

    INSERIMENTO_KART_MECCANICO("DELETE FROM caciokart.concessionaria " +
            "WHERE tipol = '%s'"),

    MOSTRA_AGGIUNTA_KART_MECCANICO("SELECT * FROM caciokart.kart WHERE kart.targa NOT IN " +
            "(SELECT socio.targa FROM socio WHERE socio.targa IS NOT NULL)" +
            "AND kart.targa IN (SELECT concessionaria.tipol FROM concessionaria)"),

    RIMUOVI_KART_MECCANICO("DELETE FROM caciokart.kart WHERE targa = '%s'"),

    MOSTRA_RIMUOVI_KART_MECCANICO("SELECT * FROM caciokart.kart WHERE targa NOT IN (" +
            "SELECT tipol FROM caciokart.concessionaria WHERE tipol IS NOT NULL) " +
            "AND targa NOT IN (SELECT targa FROM caciokart.socio WHERE targa IS NOT NULL)"),

    MOSTRA_KART_MANUTENZIONE("SELECT " +
            "    k.*, " +
            "    COALESCE( " +
            "        CASE " +
            "            WHEN MAX(m.dataM) IS NULL THEN 'MAI_FATTA' " +
            "            ELSE CAST(DATEDIFF(CURRENT_DATE, MAX(m.dataM)) AS CHAR) " +
            "        END, 'MAI_FATTA' " +
            "    ) AS giorniDallaManutenzione, " +
            "    MAX(m.dataM) AS ultimaManutenzione " +
            "FROM caciokart.kart k " +
            "LEFT JOIN caciokart.manutenzione m ON k.targa = m.targa " +
            "GROUP BY k.targa;"),

    AGGIUNTA_DIPENDENTE_PROPRIETARIO("INSERT INTO dipendente (dip, nome, cognome, mail, passw, dataN, ruolo, oreL, stipendio) " +
            "VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')"),

    RIMOZIONE_DIPENDENTE_PROPRIETARIO("DELETE FROM caciokart.dipendente WHERE dip = '%s'"),

    MOSTRA_DIPENDENTI_PROPRIETARIO("SELECT * FROM caciokart.dipendente"),

    AGGIUNTA_BENZINA_MECCANICO("UPDATE caciokart.kart " +
            "SET kart.serbatoio = '20' WHERE kart.targa = '%s'"),
    ;

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
