<?php
  include 'default/footerConce.php';
?>

<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Pagina Proprietario</title>
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
          <img src="immagini/LOGO_KART.png" alt="Logo CacioKart">
        </a>
      </div>
      <nav>
        <ul>
          <li><a href="proprietario.php">Home</a></li>
          <li><a href="default/logout.php" id="logoutBtn">Logout</a></li>
        </ul>
      </nav>
    </div>
  </header>

  
  <!-- Sezione Hero -->
  <section class="hero">
    <h1>Benvenuto, Antonino!</h1>
    <p>Clicca uno dei pulsanti qui sotto per aggiungere un dipendente, rimuoverlo o visionare il bilancio</p>
  </section>

  <div class="profile-buttons">
    <button onclick="location.href='bilancio.php'">Visualizza il bilancio</button>
    <button onclick="location.href='aggiuntaDipendenti.php'">Aggiungi un dipendente</button>
    <button onclick="location.href='rimozioniDipendenti.php'">Rimuovi un dipendente</button>
  </div>

</body>
</html>