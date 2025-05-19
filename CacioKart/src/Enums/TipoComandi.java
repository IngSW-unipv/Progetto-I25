package Enums;

/**
 * Classe di tipo Enum per gestire i comandi in arrivo dal client.
 * Ogni possibile comando ha associato ad esso una stringa, che
 * sarà il comando effettivo che riceverà dalle pagine PHP.
 */
public enum TipoComandi {

    //UTENTE

    REGISTRAZIONE("registrazioneSocio"),
    LOGIN("login"),
    CLASSIFICA_UTENTE("richiestaClassUsr"),
    MOSTRA_PRENOTAZIONI_UTENTE("richiestaPren"), //mando: il messaggio e il cf, mi aspetto: dataG, fasciaO, tipo
    PRENOTAZIONE_LIBERA("prenotazioneLibera"),
    ACQUISTA_KART("acquistaKart"),
    MOSTRA_KART_AGGIUNTA("mostraKartAggiunta"),
    MOSTRA_KART_UTENTE("richiestaKartUsr"), //mando: il messaggio e il cf, mi aspetto: targa, cilindrata, serbatoio
    CLASSIFICA_GENERALE("richiestaClass"),
    ACQUISTA_PEZZI("acquistaPezzi"),
    MOSTRA_PEZZI_POSSEDUTI_UTENTE("richiestaPezziUsr"), //mando: il messaggio e il cf, mi aspetto: Nome del prodotto e data acquisto

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
    MOSTRA_SOCI("mostraSoci"),
    INSERIMENTO_SOCI_GARA("inserimentoSociGara"),
    RICHIESTA_GARA_SECCA("richiestaGaraSecca"),
    MOSTRA_SOCI_CAMPIONATO("mostraSociCampionato"),
    RICHIESTA_CAMPIONATO("richiestaCampionato"),
    SELEZIONE_GARE_CAMPIONATO("selezioneGareCampionato"),
    AGGIUNGI_GARE_CAMPIONATO("aggiungiGareCampionato"),
    CREAZIONE_TEAM("aggiungiTeam"),

    //PROPRIETARIO

    MOSTRA_BILANCIO("mostraBilancio"),
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

    /**
     * Metodo per riconoscere la stringa in entrata e
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
