/**Classe di tipo Enum per gestire i comandi in arrivo dal client.
 * Ogni possibile comando ha associato ad esso una stringa, che
 * sarà il comando effettivo che riceverà dalle pagine PHP.
 *
 */
public enum TipoComandi {
    REGISTRAZIONE("registrazioneSocio"),
    LOGIN("login"),
    CLASSIFICA_GENERALE("richiestaClass"),
    PRENOTAZIONE_LIBERA("prenotazioneLibera"),
    PRENOTAZIONE_SECCA("prenotazioneSecca"),
    AGGIUNTA_KART_CONCESSIONARIA("aggiungiKartConcessionario"),
    MOSTRA_KART_AGGIUNTA("mostraKartAggiunta"),
    MOSTRA_KART_RIMUOVI("mostraKartRimuovi"),
    ELIMINAZIONE_KART("eliminaKart"),
    REGISTRAZIONE_DIPENDENTE("registrazioneDipen"),
    ELIMINA_DIPENDENTE("eliminaDipen"),
    RICHIESTA_DIPENDENTE("richiestaDipen"),
    CREAZIONE_TEAM("aggiungiTeam"),
    CREAZIONE_CAMPIONATO("aggiungiCampionato"),
    AGGIUNGI_KART_MECCANICO("aggiungiKartMeccanico"),
    AGGIUNGI_BENZINA("aggiungiBenzina"),
    ACQUISTA_KART("acquistaKart");



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
