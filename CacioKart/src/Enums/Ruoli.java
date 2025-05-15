package Enums;

/**
 * Classe di tipo Enum per gestire i comandi in arrivo dal client.
 * Ogni possibile comando ha associato a esso una stringa, che
 * sarà il comando effettivo che riceverà dalle pagine PHP.
 */
public enum Ruoli {
    MECCANICO("meccanico"),
    GESTORE("gestore"),
    ARBITRO("arbitro"),
    ORGANIZZATORE("organizzatore"),
    PROPRIETARIO("proprietario");

    private final String descrizione;

    // Costruttore privato
    Ruoli(String descrizione) {
        this.descrizione = descrizione;
    }

    // Metodo per ottenere la descrizione
    public String getDescrizione() {
        return descrizione;
    }

    /** Metodo per riconoscere il ruolo in entrata sotto forma di stringa e
     * convertirla in un ENUM per facilità di gestione.
     *
     * @param ruoloRichiesto Il ruolo richiesto sotto forma di String
     * @return Il ruolo sotto forma di ENUM se c'è una corrispondenza. Altrimenti NULL
     */
    public static Ruoli requestedRole(String ruoloRichiesto) {
        for (Ruoli ruolo : Ruoli.values()) {
            if (ruolo.getDescrizione().equalsIgnoreCase(ruoloRichiesto)) {
                return ruolo;
            }
        }
        return null;
    }

}
