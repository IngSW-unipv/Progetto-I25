package Enums;

/**
 * Enum per tutte le query SQL dell'applicazione.
 * Usa getQuery(parametri...) per sostituire i %s nella query.
 */
public enum Query {

    // =============================== //
    //             ARBITRO
    // =============================== //

    INSERIMENTO_PENALITA_ARBITRO("UPDATE caciokart.classifica SET tempTot = ADDTIME(tempTot, '%s') WHERE idGara = '%s' AND socio  = '%s'"),

    // =============================== //
    //            CLASSIFICA
    // =============================== //

    CLASSIFICA_ARBITRO("SELECT DISTINCT idGara FROM caciokart.classifica ORDER BY idGara"),
    CLASSIFICA_GENERALE("SELECT c.idGara, s.nome, s.cognome, c.targa, c.bGiro, c.tempTot FROM caciokart.classifica AS c JOIN (SELECT idGara, MIN(tempTot) AS tempo_migliore FROM caciokart.classifica GROUP BY idGara) AS agg ON c.idGara = agg.idGara AND c.tempTot = agg.tempo_migliore JOIN caciokart.socio AS s ON c.socio = s.socio ORDER BY c.tempTot ASC LIMIT 10"),
    CLASSIFICA_UTENTE("SELECT c.idGara, c.targa, c.bGiro, c.tempTot FROM caciokart.classifica AS c JOIN caciokart.socio AS s ON c.socio = s.socio WHERE s.nome = '%s' ORDER BY c.idGara DESC LIMIT 10"),
    MOSTRA_CLASSIFICA_PENALITA("SELECT * FROM caciokart.classifica WHERE idGara = '%s'"),

    // =============================== //
    //         CONCESSIONARIA
    // =============================== //

    INSERIMENTO_KART_CONCESSIONARIA_MAX_ID("SELECT MAX(CAST(idProdotto AS UNSIGNED)) AS max FROM concessionaria"),
    INSERIMENTO_KART_CONCESSIONARIA_TABELLA_CONCESSIONARIA("INSERT INTO concessionaria (idProdotto, tipol, quantita, prezzo) VALUES('%s', '%s', '%s', '%s')"),
    MOSTRA_PEZZI_CONCESSIONARIA("SELECT * FROM concessionaria WHERE tipol NOT LIKE 'KRT%'"),
    INSERIMENTO_NUOVI_PEZZI("UPDATE caciokart.concessionaria SET quantita = quantita + %s WHERE idProdotto = '%s'"),
    INSERIMENTO_KART_CONCESSIONARIA("INSERT INTO kart (targa, cilindrata, serbatoio) VALUES('%s', '%s', '%s')"),


    // =============================== //
    //            MECCANICO
    // =============================== //

    //AGGIORNAMENTO_MANUTENZIONE_TABELLA_MANUTENZIONE("INSERT INTO manutenzione (idM, tipoInt, costo, dataM, targa) VALUES ('%s', '%s', '%s', '%s', '%s')"),
    AGGIORNA_QUANTITA_CONCESSIONARIA("UPDATE caciokart.concessionaria SET quantita = quantita - 1 WHERE tipol = '%s'"),
    AGGIUNTA_BENZINA_MECCANICO("UPDATE caciokart.kart SET kart.serbatoio = '20' WHERE kart.targa = '%s'"),
    RIMUOVI_KART_MECCANICO("UPDATE caciokart.concessionaria SET quantita = quantita + 1 WHERE tipol = '%s'"),
    MOSTRA_AGGIUNTA_KART_MECCANICO("SELECT * FROM caciokart.kart k " + "WHERE k.targa NOT IN (SELECT s.targa FROM caciokart.socio s WHERE s.targa IS NOT NULL) " + "AND k.targa IN (SELECT c.tipol FROM caciokart.concessionaria c WHERE c.quantita >= 1)"),
    MOSTRA_RIMUOVI_KART_MECCANICO("SELECT * FROM caciokart.kart k " + "WHERE k.targa NOT IN (" + "SELECT s.targa FROM caciokart.socio s WHERE s.targa IS NOT NULL" + ") " + "AND EXISTS (" + "SELECT 1 FROM caciokart.concessionaria c WHERE c.tipol = k.targa AND c.quantita < 1" + ")"),
    MOSTRA_KART_MANUTENZIONE("SELECT k.targa, " + "COALESCE(CAST(MAX(m.dataM) AS CHAR), 'MAI_FATTA') AS ultimaManutenzione " + "FROM kart k LEFT JOIN manutenzione m ON k.targa = m.targa " + "GROUP BY k.targa"),

    // =============================== //
    //           ORGANIZZATORE
    // =============================== //

    CREA_TEAM_TABELLA_TEAM("INSERT INTO caciokart.team (nome, colore) VALUES ('%s', '%s')"),
    CREA_TEAM_TABELLA_APPARTENENZA("INSERT INTO caciokart.appartenenza (socio, nome) VALUES ('%s', '%s')"),
    MOSTRA_GARE_INSERIMENTO("SELECT g.idGara, g.ora FROM garas g WHERE NOT EXISTS (SELECT 1 FROM partecipa p WHERE p.idGara = g.idGara)"),
    AGGIUNGI_GARA_PARTECIPA_CAMPIONATO("INSERT INTO partecipa (idGara, idCampionato) VALUES ('%s', '%s')"),
    INSERIMENTO_SOCIO_GARA("INSERT INTO prenota (idP, socio, data) VALUES('%s', '%s', '%s')"),
    MOSTRA_SOCI_INSERIMENTO_CAMPIONATO("SELECT socio, nome, cognome FROM caciokart.socio WHERE socio.socio NOT IN (SELECT socio FROM appartenenza)"),
    MOSTRA_CAMPIONATI("SELECT idCampionato FROM caciokart.campionato"),
    MOSTRA_PRENOTAZIONI_ORGANIZZATORE("SELECT idP FROM prenotazione WHERE dataG > curdate() ORDER BY idP DESC"),
    SELEZIONA_SOCIO_AGGIUNTA_PRENOTAZIONE("SELECT socio, nome, cognome FROM socio"),
    SELEZIONA_DIPENDENTE_PRENOTAZIONE("SELECT dip FROM caciokart.dipendente WHERE dip = '%s'"),

    // =============================== //
    //             PERSONA
    // =============================== //

    LOGIN_SOCIO("SELECT * FROM caciokart.socio WHERE socio = '%s' AND passw = '%s'"),
    LOGIN_DIPENDENTE("SELECT * FROM caciokart.dipendente WHERE dip = '%s' AND passw = '%s'"),

    // =============================== //
    //            PRENOTAZIONE
    // =============================== //

    PRENOTAZIONE_CONTEGGIO_POSTI_RIMASTI("SELECT count(*) AS concurrent FROM caciokart.prenotazione WHERE dataG = '%s' AND fasciaO = '%s'"),
    PRENOTAZIONE_MAX_ID("SELECT MAX(CAST(idP AS UNSIGNED)) AS max FROM PRENOTAZIONE"),
    PRENOTAZIONE_GENERICA_INSERIMENTO("INSERT INTO prenotazione (idP, dataG , fasciaO, tipologia, costo) VALUES('%s', '%s', '%s', '%s', '%s')"),
    PRENOTAZIONE_LIBERA_INSERIMENTO("INSERT INTO prenota (idP, socio, data) VALUES ('%s', '%s', '%s')"),
    PRENOTAZIONE_LIBERA_INSERIMENTO_NULL("INSERT INTO prenota (idP, socio, data) VALUES ('%s', NULL, '%s')"),

    // =============================== //
    //           PROPRIETARIO
    // =============================== //

    MOSTRA_DIPENDENTI_PROPRIETARIO("SELECT * FROM caciokart.dipendente WHERE ruolo != 'proprietario'"),
    AGGIUNTA_DIPENDENTE_PROPRIETARIO("INSERT INTO dipendente (dip, nome, cognome, mail, passw, dataN, ruolo, oreL, stipendio) VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')"),
    RIMOZIONE_DIPENDENTE_PROPRIETARIO("DELETE FROM caciokart.dipendente WHERE dip = '%s'"),
    BILANCIO_ENTRATE_PROPRIETARIO("SELECT COALESCE((SELECT SUM(c.quantita * c.prezzo) FROM acquista a JOIN concessionaria c ON a.idProdotto = c.idProdotto), 0) + COALESCE((SELECT SUM(costo) FROM prenotazione), 0) + COALESCE((SELECT SUM(costo) FROM manutenzione), 0) AS ENTRATE"),
    BILANCIO_USCITE_PROPRIETARIO("SELECT COALESCE(SUM(stipendio), 0) AS USCITE FROM dipendente"),
    BILANCIO_SALDO_TOTALE("SELECT COALESCE((SELECT SUM(c.quantita * c.prezzo) FROM acquista a JOIN concessionaria c ON a.idprodotto = c.idprodotto), 0) + COALESCE((SELECT SUM(costo) FROM prenotazione), 0) + COALESCE((SELECT SUM(costo) FROM manutenzione), 0) - COALESCE((SELECT SUM(stipendio) FROM dipendente), 0) AS SALDO"),

    // =============================== //
    //              SOCIO
    // =============================== //

    REGISTRAZIONE_SOCIO("INSERT INTO socio (socio, nome, cognome, mail, passw, dataN) VALUES('%s', '%s', '%s', '%s', '%s', '%s')"),
    ACQUISTO_KART_UTENTE_TABELLA_SOCIO("UPDATE socio SET targa = '%s' WHERE socio = '%s'"),
    ACQUISTO_KART_UTENTE_TROVA_ID_PRODOTTO("SELECT idProdotto FROM concessionaria WHERE tipol = '%s'"),
    ACQUISTO_KART_UTENTE_TABELLA_ACQUISTA("INSERT INTO acquista (socio, idProdotto, data) VALUES('%s', '%s', '%s')"),
    ACQUISTA_PEZZI_TABELLA_CONCESSIONARIA("UPDATE concessionaria SET quantita = quantita - 1 WHERE idProdotto = '%s'"),
    ACQUISTA_PEZZI_TABELLA_ACQUISTA("INSERT INTO acquista (socio, idProdotto, data) VALUES('%s', '%s', '%s')"),
    MOSTRA_PRENOTAZIONI_SOCIO("SELECT dataG, fasciaO, tipologia FROM caciokart.prenotazione JOIN prenota ON prenotazione.idP = prenota.idP WHERE socio = '%s' ORDER BY prenotazione.idP DESC"),
    MOSTRA_KART_SOCIO("SELECT kart.targa, kart.cilindrata, kart.serbatoio FROM caciokart.kart JOIN socio ON kart.targa = socio.targa WHERE socio = '%s'"),
    MOSTRA_PEZZI_SOCIO("SELECT tipol, data FROM caciokart.acquista JOIN concessionaria ON acquista.idProdotto = concessionaria.idProdotto WHERE socio = '%s'"),
    INSERIMENTO_KART_UTENTE_TABELLA_ACQUISTA("DELETE FROM caciokart.acquista WHERE idProdotto = (SELECT idProdotto FROM concessionaria WHERE tipol = '%s')");

    private final String query;

    Query(String query) {
        this.query = query;
    }

    /**
     * Restituisce la query SQL, sostituendo eventuali parametri %s
     * con quelli forniti in input.
     * Esempio: Query.RIMUOVI_KART_MECCANICO.getQuery("KRT123")
     */
    public String getQuery(Object... args) {
        if (args.length == 0) {
            return query;
        }
        return String.format(query, args);
    }
}
