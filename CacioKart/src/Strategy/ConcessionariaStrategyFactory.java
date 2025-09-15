package Strategy;

import Objects.Kart;
import Objects.Pezzo;

import java.util.HashMap;
import java.util.Map;

public class ConcessionariaStrategyFactory {
    private static final Map<Class<?>, InserimentoConcessionariaStrategy> strategyMap = new HashMap<>();

    static {

        strategyMap.put(Kart.class, new InserimentoKartStrategy());
        strategyMap.put(Pezzo.class, new InserimentoPezzoStrategy());
    }

    public static InserimentoConcessionariaStrategy getStrategy(Object obj) {
        InserimentoConcessionariaStrategy strategy = strategyMap.get(obj.getClass());
        if (strategy == null) {
            throw new IllegalArgumentException("Tipo oggetto non supportato: " + obj.getClass());
        }
        return strategy;
    }
}

