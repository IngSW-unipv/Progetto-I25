package DAO.interfacce;

import Logic.Socio;
import Objects.Kart;
import Objects.Pezzo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


public interface SocioDAOInterface {
    Kart getKart();
    int registrazione(Socio nuovoSocio);
}