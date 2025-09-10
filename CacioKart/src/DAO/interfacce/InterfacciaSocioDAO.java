package DAO.interfacce;

import Logic.Socio;
import Objects.Kart;
import Objects.Pezzo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


public interface InterfacciaSocioDAO{
    Kart getKart();
    //int checkPrenotazione(String username, LocalDate data, LocalTime intervallo, LocalDate dataCorr);
    //int prenotazione(String username, LocalDate data, LocalTime intervallo, LocalDate dataCorr);
    int registrazione(Socio nuovoSocio);
    int acquistaKart(String targa, String cF, LocalDateTime ora);
    List<Pezzo> ottieniPezzi();
    int acquistaPezzi(String IDProdotto, int quantita, String username, LocalTime ora);
}