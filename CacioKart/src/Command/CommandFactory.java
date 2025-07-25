package Command;


import Enums.TipoComandi;

import static Enums.TipoComandi.*;

public class CommandFactory {
    public static RequestCommand getCommand(TipoComandi  tipo) {
        return switch (tipo) {
            case LOGIN -> new LoginCommand(); //funziona
            case REGISTRAZIONE -> new RegistrazioneCommand();//funziona
            case PRENOTAZIONE-> new PrenotazioneCommand();
            case AGGIUNTA_KART_CONCESSIONARIA -> new KartInsertCommand(); //funziona ANDREA
            case AGGIUNGI_KART_MECCANICO-> new AggiungiKartMeccanicoCommand(); //funziona ANDREA
            case MOSTRA_KART_AGGIUNTA -> new MostraKartAggiuntaCommand(); //funziona ANDREA
            case MOSTRA_KART_RIMUOVI -> new MostraKartCommand(); //funziona ANDREA
            case MOSTRA_KART_MANUTENZIONE -> new MostraKartManutenzioneCommand(); //funziona ANDREA
            case ELIMINAZIONE_KART-> new EliminazioneKartCommand(); //funziona ANDREA
            case REGISTRAZIONE_DIPENDENTE-> new AggiungiDipendenteCommand(); //funziona
            case RICHIESTA_DIPENDENTE -> new MostraDipendentiCommand(); //funziona
            case ELIMINA_DIPENDENTE-> new RimuoviDipendenteCommand(); //funziona
            case ACQUISTA_KART-> new AcquistaKartCommand();
            case AGGIUNGI_BENZINA-> new AggiuntaBenzinaCommand(); //funziona ANDREA
            case CLASSIFICA_GENERALE-> new ClassificaGeneraleCommand(); //funziona
            case CLASSIFICA_UTENTE-> new ClassificaUtenteCommand();
            case CLASSIFICA_ARBITRO-> new ClassificaArbitroCommand(); //Funziona ANDREA
            case MANUTENZIONE-> new ManutenzioneCommand(); //funziona ANDREA
            case MOSTRA_PEZZI -> new MostraPezziCommand(); //Funziona
            case ACQUISTA_PEZZI->new AcquistaPezziCommand();
            case MOSTRA_GARA->new MostraGareSvolteCommand();
            case AGGIUNGI_PENALITA->new AggiungiPenalitaCommand(); //Funziona ANDREA
            case AGGIUNGI_PEZZI-> new PezzoInsertCommand(); //funziona ANDREA
            case MOSTRA_SOCI_CAMPIONATO->new MostraSociCampCommand(); //funziona ANDREA
            case CREAZIONE_TEAM->new AggiungiTeamCommand(); //funziona ANDREA
            case AGGIUNGI_GARA_PARTECIPA_CAMPIONATO->new AggiungiGaraCampionatoCommand(); //NON FUNZIONA ANDREA
            case RICHIESTA_CAMPIONATO->new MostraCampionatiCommand(); //funziona ANDREA
            case SELEZIONE_GARE_CAMPIONATO->new SelectGareCampCommand(); //Funziona ANDREA
            case RICHIESTA_GARA_SECCA->new RichiestaGaraSCommand(RICHIESTA_GARA_SECCA);
            case MOSTRA_SOCI->new MostraSociPrenotazioniCommand();
            case INSERIMENTO_SOCI_GARA->new InserimentoSociGaraCommand();
            case MOSTRA_BILANCIO->new BilancioCommand(); //funziona
            case MOSTRA_PRENOTAZIONI_UTENTE->new RichiestaGaraSCommand(TipoComandi.MOSTRA_PRENOTAZIONI_UTENTE);
            case MOSTRA_PRENOTAZIONI_ORGANIZZATORE -> new MostraPrenotazioniCommand();
            case MOSTRA_KART_UTENTE->new MostraKartUtenteCommand();
            case MOSTRA_PEZZI_POSSEDUTI_UTENTE->new MostraPezziUtenteCommand();
                default -> null;
        };
    }
}
