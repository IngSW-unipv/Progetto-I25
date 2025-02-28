/**Classe di tipo Enum per gestire i comandi in arrivo dal client.
 * Ogni possibile comando ha associato ad esso una stringa, che
 * sarà il comando effettivo che riceverà dalle pagine PHP.
 *
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

    /**Metodo per riconoscere la stringa in entrata e
     * convertirla in un ENUM per facilità di gestione.
     *
     * @param ruoloRichiesto
     * @return
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
