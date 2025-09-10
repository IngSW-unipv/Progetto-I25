package Command;


import Enums.TipoComandi;

import static Enums.TipoComandi.*;

public class CommandFactory {
    public static RequestCommand getCommand(TipoComandi  tipo) {
        return switch (tipo) {
            case LOGIN -> new LoginCommand();
            case REGISTRAZIONE -> new RegistrazioneCommand();
            case PRENOTAZIONE-> new PrenotazioneCommand();
            case AGGIUNTA_KART_CONCESSIONARIA -> new KartInsertCommand();
            case AGGIUNGI_KART_MECCANICO-> new AggiungiKartMeccanicoCommand();
            case MOSTRA_KART_AGGIUNTA -> new MostraKartAggiuntaCommand();
            case MOSTRA_KART_RIMUOVI -> new MostraKartCommand();
            case MOSTRA_KART_MANUTENZIONE -> new MostraKartManutenzioneCommand();
            case ELIMINAZIONE_KART-> new EliminazioneKartCommand();
            case REGISTRAZIONE_DIPENDENTE-> new AggiungiDipendenteCommand();
            case RICHIESTA_DIPENDENTE -> new MostraDipendentiCommand();
            case ELIMINA_DIPENDENTE-> new RimuoviDipendenteCommand();
            case ACQUISTA_KART-> new AcquistaKartCommand();  //testare e fatemi sapere.............
            case AGGIUNGI_BENZINA-> new AggiuntaBenzinaCommand();
            case CLASSIFICA_GENERALE-> new ClassificaGeneraleCommand();
            case CLASSIFICA_UTENTE-> new ClassificaUtenteCommand();
            case CLASSIFICA_ARBITRO-> new ClassificaArbitroCommand();
            case MANUTENZIONE-> new ManutenzioneCommand();
            case MOSTRA_PEZZI -> new MostraPezziCommand();
            case ACQUISTA_PEZZI->new AcquistaPezziCommand();
            case MOSTRA_GARA->new MostraGareSvolteCommand();
            case AGGIUNGI_PENALITA->new AggiungiPenalitaCommand();
            case AGGIUNGI_PEZZI-> new PezzoInsertCommand();
            case MOSTRA_SOCI_CAMPIONATO->new MostraSociCampCommand();
            case CREAZIONE_TEAM->new AggiungiTeamCommand();
            case AGGIUNGI_GARA_PARTECIPA_CAMPIONATO->new AggiungiGaraCampionatoCommand();
            case RICHIESTA_CAMPIONATO->new MostraCampionatiCommand();
            case SELEZIONE_GARE_CAMPIONATO->new SelectGareCampCommand();
            case RICHIESTA_GARA_SECCA->new RichiestaGaraSCommand(RICHIESTA_GARA_SECCA);
            case MOSTRA_SOCI->new MostraSociPrenotazioniCommand();
            case INSERIMENTO_SOCI_GARA->new InserimentoSociGaraCommand();
            case MOSTRA_BILANCIO->new BilancioCommand();
            case MOSTRA_PRENOTAZIONI_UTENTE->new RichiestaGaraSCommand(TipoComandi.MOSTRA_PRENOTAZIONI_UTENTE);
            case MOSTRA_PRENOTAZIONI_ORGANIZZATORE -> new MostraPrenotazioniCommand();
            case MOSTRA_KART_UTENTE->new MostraKartUtenteCommand();
            case MOSTRA_PEZZI_POSSEDUTI_UTENTE->new MostraPezziUtenteCommand();
                default -> null;
        };
    }
}
