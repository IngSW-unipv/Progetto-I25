<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>CacioKart - Concessionaria</title>
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
    <section class="hero">
      <h1>Benvenuti nella concessionaria di CacioKart</h1>
      <p>Scopri la nostra vasta gamma di kart per tutte le età.</p>
    </section>

    <section class="scrollable-section">
      <div class="scrollable-container">
        <img src="immagini/concess1.jpg" alt="Immagine Concessionario">
      </div>
    </section>

    <section class="description-section">
      <div class="description-text">
        <p>
          Da CacioKart, siamo orgogliosi di offrire una selezione di kart di alta qualità made in Italy. Date un occhio alla gamma di kart 100cc,
          che è stata aggiornata di recente con un innovativo sistema ad iniezione diretta.
        </p>
      </div>
    </section>

    <section class="hero">
      <h1>Acquista il Tuo Kart</h1>
      <p>Scegli il kart perfetto per te, disponibile in tre categorie: 50cc, 100cc e 150cc.</p>
    </section>

    <!-- sezione vendita kart-->
    <div class="kart-container">
      <div class="kart-item">
        <div class="kart-image">
          <a href="kart50cc.php">
          <img src="immagini/50cc.jpg" alt="Kart 50cc">
          </a>
        </div> 
        <div class="kart-description">
          <a href="kart50cc.php">
          <h3>Kart 50cc</h3>
          </a>
          <p>Perfetto per i più giovani e principianti. Ideale per chi inizia a scoprire il mondo del karting. Leggero e facile da manovrare.</p>
          <p><strong>Prezzo:</strong> 2.500€</p>
        </div>        
      </div>
    
      <div class="kart-item">
        <div class="kart-image">
          <a href="kart100cc.php">
          <img src="immagini/100cc.jpg" alt="Kart 100cc">
          </a>
        </div>
        <div class="kart-description">
          <a href="kart100cc.php">
          <h3>Kart 100cc</h3>
          </a>
          <p>Ideale per adolescenti e chi ha già esperienza con i kart. Potenza maggiore per chi cerca emozioni più forti. Ottimo per gare amatoriali.</p>
          <p><strong>Prezzo:</strong> 4.500€</p>
        </div>
      </div>
    
      <div class="kart-item">
        <div class="kart-image">
          <a href="kart150cc.php">
          <img src="immagini/150cc.jpg" alt="Kart 150cc">
          </a>
        </div>
        <div class="kart-description">
          <a href="kart150cc.php">
          <h3>Kart 150cc</h3>
          </a>
          <p>Per i più esperti e professionisti. Potenza e velocità per chi vuole sfidare se stesso su circuiti più complessi. Adatto per gare ad alta competitività.</p>
          <p><strong>Prezzo:</strong> 6.500€</p>
        </div>
      </div>
    </div>
    
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