public class KartDAO {
    private static KartDAO instance;
    private KartDAO() {}
    public static KartDAO getInstance() {
        if (instance == null) instance = new KartDAO();
        return instance;
    }

    public List<Kart> getAllKart() {
        // Implementa la select dalla tabella kart
    }

    public boolean insertKart(Kart k) {
        // Implementa la insert nella tabella kart
    }

    public boolean deleteKart(Kart k) {
        // Implementa la delete dalla tabella kart
    }

    public boolean refillKart(Kart k) {
        // Implementa l'update del serbatoio al massimo
    }
}
