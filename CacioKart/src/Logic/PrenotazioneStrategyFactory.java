package Logic;

public class PrenotazioneStrategyFactory {

    public static PrenotazioneStrategy getStrategy(String tipologia) {
        return switch (tipologia.toLowerCase()) {
            case "libera" -> new PrenotazioneLiberaStrategy();
            case "secca" -> new PrenotazioneSeccaStrategy();
            default -> null;
        };
    }
}