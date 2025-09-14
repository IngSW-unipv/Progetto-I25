package Command;

import Enums.TipoComandi;
import java.util.Map;
import java.util.EnumMap;
import java.util.function.Supplier;
import static Enums.TipoComandi.*;

public class CommandFactory {

    private static final Map<TipoComandi, Supplier<RequestCommand>> commandMap = new EnumMap<>(TipoComandi.class);

    static {
        commandMap.put(LOGIN, LoginCommand::new);
        commandMap.put(REGISTRAZIONE, RegistrazioneCommand::new);
        commandMap.put(PRENOTAZIONE, PrenotazioneCommand::new);
        commandMap.put(AGGIUNTA_KART_CONCESSIONARIA, KartInsertCommand::new);
        commandMap.put(AGGIUNGI_KART_MECCANICO, AggiungiKartMeccanicoCommand::new);
        commandMap.put(MOSTRA_KART_AGGIUNTA, MostraKartAggiuntaCommand::new);
        commandMap.put(MOSTRA_KART_RIMUOVI, MostraKartCommand::new);
        commandMap.put(MOSTRA_KART_MANUTENZIONE, MostraKartManutenzioneCommand::new);
        commandMap.put(ELIMINAZIONE_KART, EliminazioneKartCommand::new);
        commandMap.put(REGISTRAZIONE_DIPENDENTE, AggiungiDipendenteCommand::new);
        commandMap.put(RICHIESTA_DIPENDENTE, MostraDipendentiCommand::new);
        commandMap.put(ELIMINA_DIPENDENTE, RimuoviDipendenteCommand::new);
        commandMap.put(ACQUISTA_KART, AcquistaKartCommand::new);
        commandMap.put(AGGIUNGI_BENZINA, AggiuntaBenzinaCommand::new);
        commandMap.put(CLASSIFICA_GENERALE, ClassificaGeneraleCommand::new);
        commandMap.put(CLASSIFICA_UTENTE, ClassificaUtenteCommand::new);
        commandMap.put(CLASSIFICA_ARBITRO, ClassificaArbitroCommand::new);
        commandMap.put(MANUTENZIONE, ManutenzioneCommand::new);
        commandMap.put(MOSTRA_PEZZI, MostraPezziCommand::new);
        commandMap.put(ACQUISTA_PEZZI, AcquistaPezziCommand::new);
        commandMap.put(MOSTRA_GARA, MostraGareSvolteCommand::new);
        commandMap.put(AGGIUNGI_PENALITA, AggiungiPenalitaCommand::new);
        commandMap.put(AGGIUNGI_PEZZI, PezzoInsertCommand::new);
        commandMap.put(MOSTRA_SOCI_CAMPIONATO, MostraSociCampCommand::new);
        commandMap.put(CREAZIONE_TEAM, AggiungiTeamCommand::new);
        commandMap.put(AGGIUNGI_GARA_PARTECIPA_CAMPIONATO, AggiungiGaraCampionatoCommand::new);
        commandMap.put(RICHIESTA_CAMPIONATO, MostraCampionatiCommand::new);
        commandMap.put(SELEZIONE_GARE_CAMPIONATO, SelectGareCampCommand::new);
        commandMap.put(RICHIESTA_GARA_SECCA, () -> new RichiestaGaraSCommand(RICHIESTA_GARA_SECCA));
        commandMap.put(MOSTRA_SOCI, MostraSociPrenotazioniCommand::new);
        commandMap.put(INSERIMENTO_SOCI_GARA, InserimentoSociGaraCommand::new);
        commandMap.put(MOSTRA_BILANCIO, BilancioCommand::new);
        commandMap.put(MOSTRA_PRENOTAZIONI_UTENTE, () -> new RichiestaGaraSCommand(MOSTRA_PRENOTAZIONI_UTENTE));
        commandMap.put(MOSTRA_PRENOTAZIONI_ORGANIZZATORE, MostraPrenotazioniCommand::new);
        commandMap.put(MOSTRA_KART_UTENTE, MostraKartUtenteCommand::new);
        commandMap.put(MOSTRA_PEZZI_POSSEDUTI_UTENTE, MostraPezziUtenteCommand::new);
    }

    public static RequestCommand getCommand(TipoComandi tipo) {
        Supplier<RequestCommand> supplier = commandMap.get(tipo);

        if (supplier != null) {
            return supplier.get(); // Esegue il costruttore (es. LoginCommand::new)
        }

        // Se il comando non è registrato nella mappa, ritorna null.
        return null;
    }
}