<?php
// Non serve richiamare session_start() se già avviato in headerConce.php
include 'default/headerConce.php';
include 'default/footerConce.php';
include 'default/modalHome.php';
?>
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
  <!-- Main Content -->
  <main>
    <section class="product-details">
      <div class="product-image">
        <img src="immagini/Kart/50cc.png" alt="Kart in vendita" width="600" height="auto">
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
        
        <?php if (!empty($_SESSION['username'])): ?>
          <!-- Visualizza il bottone solo se 'username' non è vuoto -->
          <a href="kart50cc.php">
            <button class="custom-button">Acquista</button>
          </a>
        <?php endif; ?>
        
      </div>
    </section>
  </main>
</body>
</html>
