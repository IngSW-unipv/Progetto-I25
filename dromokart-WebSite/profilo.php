
<?php
session_start();

// Verifico se l'utente ha effettuato il login
if (!isset($_SESSION['username'])) {
    header("Location: index.php");
    exit();
}

// Recupero lo username dalla sessione
$username = $_SESSION['username'];
?>

<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Profilo Privato - Dromokart</title>
  <!-- Importa il font Roboto -->
  <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap" rel="stylesheet">
  <!-- Collegamento al file CSS esterno -->
  <link rel="stylesheet" href="css/profilo.css">
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
          <li><a href="profilo.php">Profilo</a></li>
          <!-- Il link Logout ora reindirizza alla homepage -->
          <li><a href="index.php">Logout</a></li>
        </ul>
      </nav>
    </div>
  </header>

  <!-- Hero Section -->
  <section class="hero">
    <h1>Benvenuto <?php echo htmlspecialchars($username); ?></h1>
    <p>Accedi alle tue informazioni personali e alle funzionalità riservate</p>
  </section>

  <!-- Main Content: Area Bottoni -->
  <main>
    <div class="profile-buttons">
      <button onclick="location.href='migliori_tempi.php'">Migliori Tempi</button>
      <button onclick="location.href='prenota_gara.php'">Prenota Gara Libera</button>
      <button onclick="location.href='caratteristiche_kart.php'">Caratteristiche del Kart</button>
    </div>
  </main>

  <!-- Footer ancorato in fondo alla pagina -->
  <footer>
    <p>&copy; 2025 KacioKart S.R.L. Tutti i diritti riservati.</p>
  </footer>
</body>
</html>
