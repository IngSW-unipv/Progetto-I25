package DAO.interfacce;

import Objects.Pezzo;

/**
 * Interfaccia per la gestione dei pezzi.
 */
public interface PezzoDAOInterface {

    /**
     * Inserisce una quantità di pezzi nel database.
     * @param p pezzo da inserire
     * @param quantita quantità da inserire
     */
    void insertPezzo(Pezzo p, int quantita);
}
