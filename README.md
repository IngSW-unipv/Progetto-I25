<div style="display: flex; align-items: center;">
  <img src="LOGO_KART.png" width="180" style="margin-right: 10px;">
  <h1 style="font-size: 36px; font-weight: bold; margin: 0;">CacioKart</h1>
</div>

**CacioKart** è una piattaforma web che permette agli utenti di acquistare kart e pezzi di ricambio, prenotare gare e consultare il proprio miglior tempo. Inoltre, la piattaforma consente ai dipendenti di effettuare tutte le operazioni che consentono di gestire il funzionamento di un intero kartodromo.

---

## Ruoli e Funzionalità

All'interno dell'applicazione, sono presenti **6 ruoli** con funzioni specifiche:

1. **Meccanico**

   * Aggiungere nuovi kart all’autodromo
   * Visualizzare ed eventualmente eliminare i kart esistenti
   * Aggiungere benzina ai kart e gestire la manutenzione

2. **Gestore della concessionaria**

   * Aggiungere i kart
   * Aggiungere i pezzi di ricambio per i kart

3. **Arbitro**

   * Visionare le gare secche (gare singole)
   * Aggiungere penalità, se necessario

4. **Organizzatore**

   * Creare dei team e le gare secche
   * Aggiungere le persone che parteciperanno a tali gare
   * Creare e gestire un campionato

5. **Proprietario**

   * Aggiungere o eliminare dipendenti
   * Visionare il bilancio

6. **Utente**

   * Acquistare kart e pezzi di ricambio
   * Prenotare gare libere e gare secche
   * Consultare il proprio miglior tempo

---

## Tecnologie Implementate

![Frontend](https://img.shields.io/badge/Frontend-HTML-E34F26?style=for-the-badge\&logoColor=white)  ![Java](https://img.shields.io/badge/Backend-Java-007396?style=for-the-badge\&logo=java\&logoColor=white)   ![MySQL](https://img.shields.io/badge/Database-MySQL-4479A1?style=for-the-badge\&logo=mysql\&logoColor=white)  ![IntelliJ IDEA](https://img.shields.io/badge/IDE-IntelliJ_IDEA-000000?style=for-the-badge\&logo=intellij-idea\&logoColor=white)

* **HTML/CSS/PHP/JavaScript**: Sviluppo frontend per l'interfaccia utente.
* **Java**: Backend per autenticazione e gestione delle operazioni logiche.
* **MySQL Workbench**: Gestione del database e archiviazione dati.
* **IntelliJ IDEA Community**: Ambiente di sviluppo utilizzato per il coding Java.

---

## Requisiti di Installazione

Per eseguire il progetto localmente è necessario disporre di:

* **Git**: Scaricabile [qui](https://git-scm.com/downloads/win).
* **Java Development Kit (JDK) 11+**: Disponibile [qui](https://www.oracle.com/java/technologies/downloads/#jdk23-windows).
* **MySQL Workbench**: Scaricabile [qui](https://dev.mysql.com/downloads/installer/).
* **IntelliJ IDEA Community**: Scaricabile [qui](https://www.jetbrains.com/idea/download/).

## Configurazione del Progetto

1. **Clonare il repository**:

```bash
git clone https://github.com/IngSW-unipv/Progetto-I25.git
```

2. **Configurare il Database**:

   * Eseguire lo script `CreaDB.sql` per generare le tabelle.
   * Eseguire `PopolaDB.sql` per inserire dati iniziali.

3. **Importare in IntelliJ**:

   * Aprire IntelliJ e selezionare `File > Open`.
   * Selezionare la directory `Progetto-I25`.

4. **Configurare MySQL Workbench**:

   * Aprire MySQL Workbench ed eseguire:

   ```sql
   ALTER USER 'root'@'localhost' IDENTIFIED BY '';
   ```

5. **Impostare Server Apache (XAMPP)**:

   * Aprire XAMPP e selezionare `Config > Apache (httpd.conf)`.
   * Modificare il percorso di `DocumentRoot` e `Directory` con la posizione del sito.
   * Se non installato, scaricare XAMPP da [qui](https://www.apachefriends.org/download.html).
   * Avviare Apache da XAMPP.

6. **Avvio Applicazione**:

   * Avviare il server Apache tramite XAMPP.
   * Eseguire il `main` del codice da IntelliJ IDEA.
   * Accedere all'applicazione dal browser all'indirizzo `http://localhost/`.

---

## Guida Rapida all'Uso di CacioKart

### Accesso e Registrazione

* **Accesso**: Usa la pagina di login se già registrato.

* **Registrazione**: Nuovi utenti possono registrarsi dalla pagina apposita. La registrazione porta automaticamente all'accesso.

* Credenziali esempio:

```bash
        username: Admin1      password: 12345678              username: Andrea       password: 12345678
ADMIN   username: Admin2      password: 12345678   UTENTE     username: Alessandro   password: 12345678
        username: Admin3      password: 12345678              username: Luca         password: 12345678
	username: Admin4      password: 12345678   UTENTE     username: Davide       password: 12345678
        username: Admin5      password: 12345678              username: Adriano      password: 12345678
```
