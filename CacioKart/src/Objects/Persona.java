package Objects;

import java.time.LocalDate;

public class Persona {
    private String nome, cognome, cf, mail, password, ruolo;
    private LocalDate dataNascita;


    public Persona(String nome, String cognome, LocalDate dataNascita, String cF, String mail, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.cf = cF;
        this.mail = mail;
        this.password = password;
    }

    /**Costruttore alternativo da utilizzare se si vuole solo chiamare i metodi
     */
    public Persona() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}