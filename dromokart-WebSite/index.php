<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>KacioKart - Home</title>
  <!-- Importa il font Roboto -->
  <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap" rel="stylesheet">
  <!-- Collegamento al file CSS esterno -->
  <link rel="stylesheet" href="css/styles.css">
</head>
<body>
  <!-- Header -->
  <header>
    <div class="header-container">
      <div class="logo">
        <a href="index.php">
          <img src="immagini/LOGO_KART.png" alt="Logo Dromokart">
        </a>
      </div>
      <nav>
        <ul>
          <li><a href="index.php">Home</a></li>
          <li><a href="chi-siamo.php">Chi Siamo</a></li>
          <li><a href="tracciato.php">Il Tracciato</a></li>
          <li><a href="classifica.php">Classifica</a></li>
          <li><a href="concessionaria-home.php">Concessionario</a></li>
          <li><a id="loginBtn">LOGIN</a></li>
        </ul>
      </nav>
    </div>
  </header>

  <!-- Main Content -->
  <main>
    <!-- Sezione Hero -->
    <section class="hero">
      <h1>Benvenuti in KacioKart</h1>
      <p>Scopri il mondo del karting con noi.</p>
    </section>

    <!-- Sezione scrollabile con immagine iniziale -->
    <section class="scrollable-section">
      <div class="scrollable-container">
        <img src="immagini/COPERTINA1.jpg" alt="Immagine Iniziale">
        <div class="overlay-text">
          Girare nei kart da noi è un'esperienza unica, adatta a tutte le età.
        </div>
      </div>
    </section>

<!-- Sezione scrollabile con immagine iniziale (modificata per COPERTINA2) -->
<!-- Sezione descrizione con immagine COPERTINA2 e testo sotto -->
<section class="description-section">
  <div class="description-image">
    <img src="immagini/COPERTINA2.jpg" alt="Esperienza di Karting">
  </div>
  <div class="description-text">
    <p>
      Contrariamente a quanto si pensi, il karting outdoor è uno sport adatto a un’ampia fascia di persone: uomini, donne, ragazzi e ragazze. I nostri kart sono suddivisi in tre livelli in base all’età:<br>
      • dai 11 ai 14 anni utilizziamo kart da 50cc,<br>
      • dai 14 ai 17 anni kart da 100cc,<br>
      • e dai 18 anni in su kart da 150cc.
      <br><br>
      Questo sistema permette a chiunque, sia amatori che professionisti, di mettersi alla prova nelle numerose gare che organizziamo. Non è necessario portare alcun abbigliamento tecnico, perché lo forniamo direttamente dalla struttura; tuttavia, consigliamo di indossare pantaloni lunghi e scarpe chiuse.
    </p>
  </div>
</section>


<!-- Sezione Prezzario (senza immagine) -->
<section class="pricing-section">
  <div class="pricing-text">
    <h2><strong>TARIFFE</strong></h2>
    <p>
      Tariffe<br>
      Sessioni di corsa da 10 minuti<br><br>
      Non si accettano prenotazioni<br>
      Una corsa da 10 minuti 15€<br>
      dalla seconda in giornata 13€<br>
      Abbonamento nominale e non condivisibile con altri<br>
      6 corse da 10 minuti 70€<br>
      10 corse da 10 minuti 100€
    </p>
  </div>
</section>

<!-- Sezione Contatti -->
<section class="contacts-section">
  <h2>CONTATTI</h2>
  <p>
    Se sei alla ricerca di un'esperienza di guida emozionante o sei un fan delle corse sui Kart, KacioKart a Pavia è il luogo ideale per te. Siamo un tracciato di kart che offre una vasta gamma di servizi di guida per soddisfare le tue esigenze. Se hai domande o hai bisogno di maggiori informazioni, non esitare a contattarci. Puoi farlo attraverso il nostro numero di telefono o la nostra email.
  </p>
  <div class="contact-details">
    <div class="contact-box phone">
      <p>+39 3334455678</p>
    </div>
    <div class="contact-box email">
      <p>kaciokart@universitadipavia.it</p>
    </div>
  </div>
</section>

    

  <!-- Footer fisso -->
  <footer>
    <p>&copy; 2025 KacioKart S.R.L. Tutti i diritti riservati.</p>
  </footer>

<!-- Modal per il Login -->
<div id="loginModal" class="modal">
  <div class="modal-content">
    <span class="close-modal" id="closeModal">&times;</span>
    <h2>Login</h2>
    <form action="profilo.php" method="post">
      <input type="text" name="username" placeholder="Nome utente" required>
      <input type="password" name="password" placeholder="Password" required>
      <button type="submit">Accedi</button>
    </form>
    <p class="register-link">Non sei iscritto? <a href="registrazione.php">Iscriviti qui</a></p>
  </div>
</div>


  <!-- Script per la gestione del Modal -->
  <script>
    document.addEventListener("DOMContentLoaded", function() {
      var loginBtn = document.getElementById("loginBtn");
      var modal = document.getElementById("loginModal");
      var closeModal = document.getElementById("closeModal");
      
      if (loginBtn) {
        loginBtn.addEventListener("click", function() {
          modal.style.display = "flex";
        });
      }
      if (closeModal) {
        closeModal.addEventListener("click", function() {
          modal.style.display = "none";
        });
      }
      window.addEventListener("click", function(event) {
        if (event.target === modal) {
          modal.style.display = "none";
        }
      });
    });
  </script>
</body>
</html>
