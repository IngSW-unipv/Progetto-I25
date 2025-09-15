package DAO.interfacce;

import Logic.Prenotazione;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface PrenotazioneDAOInterface {
    int prenota(String username, String tipologia, LocalDate data, LocalTime intervallo, LocalDate dataCorr);
}
