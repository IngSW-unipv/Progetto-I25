public enum TipoComandi {
    REGISTRAZIONE("registrazioneSocio"),
    LOGIN("login"),
    CLASSIFICA_GENERALE("classificaGenerale"),
    PRENOTAZIONE("prenotazione"),
    AGGIUNTA_KART("aggiungiKart"),
    ELIMINAZIONE_KART("eliminazioneKart"),
    REGISTRAZIONE_DIPENDENTE("registrazioneDipen"),
    ELIMINA_DIPENDENTE("eliminaDipen");


    private final String descrizione;

    // Costruttore privato
    TipoComandi(String descrizione) {
        this.descrizione = descrizione;
    }

    // Metodo per ottenere la descrizione
    public String getDescrizione() {
        return descrizione;
    }

    public static TipoComandi requestedCommand(String comando) {
        for (TipoComandi cmd : TipoComandi.values()) {
            if (cmd.getDescrizione().equalsIgnoreCase(comando)) {
                return cmd;
            }
        }
        return null;
    }

}
