package Strategy;

//Rimuovere instanceof

public class ConcessionariaStrategyFactory {
    public static InserimentoConcessionariaStrategy getStrategy(Object obj) {
        if (obj instanceof Objects.Kart) {
            return new InserimentoKartStrategy();
        } else if (obj instanceof Objects.Pezzo) {
            return new InserimentoPezzoStrategy();
        }
        throw new IllegalArgumentException("Tipo oggetto non supportato: " + obj.getClass());
    }
}
