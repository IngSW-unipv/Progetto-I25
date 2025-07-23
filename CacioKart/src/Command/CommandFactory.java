package Command;


import Enums.TipoComandi;

import static Enums.TipoComandi.*;

public class CommandFactory {
    public static RequestCommand getCommand(TipoComandi  tipo) {
        return switch (tipo) {
            case LOGIN -> new LoginCommand(); //funziona
            case REGISTRAZIONE -> new RegistrazioneCommand();//funziona
            case PRENOTAZIONE-> new PrenotazioneCommand();
            case AGGIUNTA_KART_CONCESSIONARIA -> new KartInsertCommand();
            case AGGIUNGI_KART_MECCANICO-> new AggiungiKartMeccanicoCommand();
            case MOSTRA_KART_AGGIUNTA -> new MostraKartAggiuntaCommand(); //funziona
            case MOSTRA_KART_RIMUOVI -> new MostraKartCommand(); //funziona
            case MOSTRA_KART_MANUTENZIONE -> new MostraKartManutenzioneCommand(); //funziona
            case ELIMINAZIONE_KART-> new EliminazioneKartCommand();
            case REGISTRAZIONE_DIPENDENTE-> new AggiuntaDipCommand(); //funziona
            case RICHIESTA_DIPENDENTE -> new MostraDipCommand(); //funziona
            case ELIMINA_DIPENDENTE-> new EliminaDipCommand();
            case ACQUISTA_KART-> new AcquistaKartCommand();
            case AGGIUNGI_BENZINA-> new AggiuntaBenzinaCommand();
            case CLASSIFICA_GENERALE-> new ClassificaGeneraleCommand(); //funziona
            case CLASSIFICA_UTENTE-> new ClassificaUtenteCommand();
            case CLASSIFICA_ARBITRO-> new ClassificaArbitroCommand();
            case MANUTENZIONE-> new ManutenzioneCommand();
                case MOSTRA_PEZZI -> new MostraPezziCommand(); //FUNZIONA
            case ACQUISTA_PEZZI->new AcquistaPezziCommand();
            case MOSTRA_GARA->new MostraGaraCommand();
            case AGGIUNGI_PENALITA->new AggiungiPenalitaCommand();
                case AGGIUNGI_PEZZI-> new PezzoInsertCommand();
            case MOSTRA_SOCI_CAMPIONATO->new MostraSociCampCommand();
            case CREAZIONE_TEAM->new CreazioneTeamCommand();
            case RICHIESTA_CAMPIONATO->new RichiestaCampCommand();
            case SELEZIONE_GARE_CAMPIONATO->new SelectGareCampCommand();
            case AGGIUNGI_GARE_CAMPIONATO->new AggiungiGareCampCommand();
            case RICHIESTA_GARA_SECCA->new RichiestaGaraSCommand(RICHIESTA_GARA_SECCA);
            case MOSTRA_SOCI->new MostraSociCommand();
            case INSERIMENTO_SOCI_GARA->new InserimentoSociGaraCommand();
            case MOSTRA_BILANCIO->new BilancioCommand(); //funziona
            case MOSTRA_PRENOTAZIONI_UTENTE->new RichiestaGaraSCommand(TipoComandi.MOSTRA_PRENOTAZIONI_UTENTE);
            case MOSTRA_KART_UTENTE->new MostraKartUtenteCommand();
            case MOSTRA_PEZZI_POSSEDUTI_UTENTE->new MostraPezziUtenteCommand();
                default -> null;
        };
    }
}
