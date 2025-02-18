<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Dromokart - Il Tracciato</title>
  <!-- Importa il font Roboto -->
  <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap" rel="stylesheet">
  <!-- Collegamento al file CSS esterno -->
  <link rel="stylesheet" href="css/tracciato.css">
</head>
<body>
  <!-- Header comune -->
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
          <li><a id="loginBtn" href="#">LOGIN</a></li>
        </ul>
      </nav>
    </div>
  </header>
  
  <!-- Hero Section -->
  <section class="hero">
    <h1>Il Tracciato</h1>
    <p>Scopri le caratteristiche della nostra pista</p>
  </section>
  
  <!-- Contenuto principale -->
  <main>
    <div class="content">
      <!-- Sezione immagine e specifiche -->
      <div class="track-image">
        <img src="immagini/pista.png" alt="Pista di Kart">
      </div>
      <div class="specs">
        <h2>Specifiche</h2>
        <ul>
          <li><span>Lunghezza pista:</span> 1256 mt</li>
          <li><span>Larghezza carreggiata:</span> 10 mt (8 - 10 mt)</li>
          <li><span>Rettilineo più lungo:</span> 170 mt</li>
          <li><span>Rettilineo minore:</span> 145 mt</li>
        </ul>
      </div>
    </div>
    
    <!-- Sezione: Descrizione a sinistra, immagine a destra -->
    <section class="additional-details">
      <div class="additional-text">
        <p>
          <strong>Capienza piloti</strong><br>
          in gara 34 – prove libere fino a 51<br><br>
          <strong>Bande magnetiche</strong><br>
          N. 3 bande magnetiche ALFANO<br><br>
          <strong>Curve</strong><br>
          Curve a raggio costante e variabile. Cordoli interni ed esterni a norme CIK<br><br>
          <strong>Asfalto</strong><br>
          Manto di asfalto realizzato con bitume STIRELF<br><br>
          <strong>Area Paddock</strong><br>
          Area Paddock interamente asfaltata e recintata
        </p>
      </div>
      <div class="additional-image">
        <img src="immagini/pista2.png" alt="Dettagli Pista">
      </div>
    </section>
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
  
  <!-- Script per la gestione del Modal -->
  <script>
    document.addEventListener("DOMContentLoaded", function() {
      var loginBtn = document.getElementById("loginBtn");
      var modal = document.getElementById("loginModal");
      var closeModal = document.getElementById("closeModal");
      
      if (loginBtn) {
        loginBtn.addEventListener("click", function(e) {
          e.preventDefault();
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
