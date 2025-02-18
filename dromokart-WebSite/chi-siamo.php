<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Dromokart - Chi Siamo</title>
  <!-- Importa il font Roboto -->
  <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap" rel="stylesheet">
  <!-- Collegamento al file CSS esterno -->
  <link rel="stylesheet" href="css/styles.css">
</head>
<body>
  <!-- Header comune -->
  <header>
    <div class="header-container">
      <!-- Logo -->
      <div class="logo">
        <a href="index.php">
          <img src="immagini/LOGO_KART.png" alt="Logo Dromokart">
        </a>
      </div>
      <!-- Menu di navigazione -->
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

  <!-- Hero Section -->
  <section class="hero">
    <h1>Chi Siamo</h1>
    <p>Conosci il nostro team</p>
  </section>

  <!-- Contenuto principale -->
  <main>
    <div class="content">
      <!-- Immagine "Noi" -->
      <img src="immagini/noi.png" alt="Noi">
      <!-- Testo già presente -->
      <p>
        Siamo 5 giovani ragazzi impegnati in un ambizioso progetto di ingegneria del software per la gestione di un dromokart.
        Il nostro obiettivo è sviluppare soluzioni innovative per migliorare l'esperienza delle competizioni e ottimizzare la gestione
        della pista, mettendo in gioco passione, creatività e competenze tecniche.
      </p>
    </div>
  </main>

  <!-- Footer comune -->
  <footer>
    <p>&copy; 2025 KacioKart S.R.L. Tutti i diritti riservati.</p>
  </footer>

<!-- Modal per il Login -->
<div id="loginModal" class="modal">
  <div class="modal-content">
    <span class="close-modal" id="closeModal">&times;</span>
    <h2>Login</h2>
    <form action="login.php" method="post">
      <input type="text" name="username" placeholder="Nome utente" required>
      <input type="password" name="password" placeholder="Password" required>
      <button type="submit">Accedi</button>
    </form>
    <p class="register-link">Non sei iscritto? <a href="registrazione.php">Iscriviti qui</a></p>
  </div>
</div>

  <!-- Script per il Modal -->
  <script>
    document.addEventListener("DOMContentLoaded", function() {
      var loginBtn = document.getElementById("loginBtn");
      var modal = document.getElementById("loginModal");
      var closeModal = document.getElementById("closeModal");
      loginBtn.addEventListener("click", function() {
        modal.style.display = "flex";
      });
      closeModal.addEventListener("click", function() {
        modal.style.display = "none";
      });
      window.addEventListener("click", function(event) {
        if (event.target === modal) {
          modal.style.display = "none";
        }
      });
    });
  </script>
</body>
</html>
