<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Vendita Kart - CacioKart</title>
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
        <a href="concessionaria-home.php">
          <img src="immagini/LOGO_KART.png" alt="Logo CacioKart">
        </a>
      </div>
      <nav>
        <ul>
          <li><a href="index.php">Home</a></li>
          <li><a href="chi-siamo.php">Chi Siamo</a></li>
          <li><a href="modelli.php">I Nostri Modelli</a></li>
          <li><a href="parti.php">Parti di ricambio</a></li>
          <li><a id="loginBtn">LOGIN</a></li>
        </ul>
      </nav>
    </div>
  </header>

  <!-- Main Content -->
  <main>
    <section class="product-details">
      <div class="product-image">
        <img src="immagini/50cc.png" alt="Kart in vendita" width="600" height=auto>
      </div>
      <div class="product-info">
        <h1>Kart 50cc</h1>
        <p>Potente, resistente e progettato per la massima affidabilità. Il Kart è ideale per i piloti giovani o i neofiti che cercano un'esperienza di guida nuova.</p>
        
        <h2>Caratteristiche:</h2>
        <ul>
          <li><strong>Motore:</strong> 50cc a 4 tempi</li>
          <li><strong>Velocità massima:</strong> 60 km/h</li>
          <li><strong>Peso:</strong> 95 kg</li>
          <li><strong>Sospensioni:</strong> Regolabili per una guida ottimale</li>
          <li><strong>Ruote:</strong> 10 pollici</li>
        </ul>

        <h3>Prezzo: €2.500</h3>
        <a href="kart50cc.php">
          <button class="custom-button">Acquista</button>
        </a>
      </div>
    </section>
  </main>

  <!-- Footer fisso -->
  <footer>
    <p>&copy; 2025 CacioKart S.R.L. Tutti i diritti riservati.</p>
    <p>contatti - telefono: +39 3331987477  email: cacioconc@universitadipavia.it</p>
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