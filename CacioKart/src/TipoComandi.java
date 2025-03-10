/**Classe di tipo Enum per gestire i comandi in arrivo dal client.
 * Ogni possibile comando ha associato ad esso una stringa, che
 * sarà il comando effettivo che riceverà dalle pagine PHP.
 *
 */
public enum TipoComandi {

    //UTENTE
    REGISTRAZIONE("registrazioneSocio"),
    LOGIN("login"),
    CLASSIFICA_UTENTE("richiestaClassUsr"),
    PRENOTAZIONE_LIBERA("prenotazioneLibera"),
    ACQUISTA_KART("acquistaKart"),
    MOSTRA_KART_AGGIUNTA("mostraKartAggiunta"),
    CLASSIFICA_GENERALE("richiestaClass"),
    ACQUISTA_PEZZI("acquistaPezzi"),

    //MECCANICO

    MOSTRA_KART_RIMUOVI("mostraKartRimuovi"),
    MOSTRA_KART_MANUTENZIONE("mostraKartManutenzione"),
    ELIMINAZIONE_KART("eliminaKart"),
    AGGIUNGI_KART_MECCANICO("aggiungiKartMeccanico"),
    AGGIUNGI_BENZINA("aggiungiBenzina"),
    MANUTENZIONE("manutenzione"),

    //CONCESSIONARIA

    AGGIUNTA_KART_CONCESSIONARIA("aggiungiKartConcessionario"),
    AGGIUNGI_PEZZI("aggiungiPezzi"),
    MOSTRA_PEZZI("mostraPezzi"),

    //ARBITRO

    CLASSIFICA_ARBITRO("richiestaClassAr"),
    MOSTRA_GARA("mostraGara"),
    AGGIUNGI_PENALITA("aggiungiPenalita"),

    //ORGANIZZATORE

    PRENOTAZIONE_SECCA("prenotazioneSecca"),
    MOSTRA_TEAM("mostraTeam"),
    INSERIMENTO_TEAM_GARA("inserimentoTeamGara"),
    RICHIESTA_GARA_SECCA("richiestaGaraSecca"),
    MOSTRA_SOCI_CAMPIONATO("mostraSociCampionato"),
    RICHIESTA_CAMPIONATO("richiestaCampionato"),
    SELEZIONE_GARE_CAMPIONATO("selezioneGareCampionato"),
    AGGIUNGI_GARE_CAMPIONATO("aggiungiGareCampionato"),
    CREAZIONE_TEAM("aggiungiTeam"),
    CREAZIONE_CAMPIONATO("aggiungiCampionato"),

    //PROPRIETARIO

    REGISTRAZIONE_DIPENDENTE("registrazioneDipen"),
    ELIMINA_DIPENDENTE("eliminaDipen"),
    RICHIESTA_DIPENDENTE("richiestaDipen");


    private final String descrizione;

    // Costruttore privato
    TipoComandi(String descrizione) {
        this.descrizione = descrizione;
    }

    // Metodo per ottenere la descrizione
    public String getDescrizione() {
        return descrizione;
    }

    /**Metodo per riconoscere la stringa in entrata e
     * convertirla in un ENUM per facilità di gestione.
     *
     * @param comando
     * @return
     */
    public static TipoComandi requestedCommand(String comando) {
        for (TipoComandi cmd : TipoComandi.values()) {
            if (cmd.getDescrizione().equalsIgnoreCase(comando)) {
                return cmd;
            }
        }
        return null;
    }

}
