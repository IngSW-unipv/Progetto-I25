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
        <img src="immagini/Kart/100cc.png" alt="Kart in vendita" width="600" height="auto">
      </div>
      <div class="product-info">
        <h1>Kart 125cc</h1>
        <p>Potente, resistente e progettato per la massima affidabilità. Il Kart è ideale per i piloti giovani o i neofiti che cercano un'esperienza di guida nuova.</p>
        
        <h2>Caratteristiche:</h2>
        <ul>
          <li><strong>Motore:</strong> 125cc a 4 tempi</li>
          <li><strong>Velocità massima:</strong> 90 km/h</li>
          <li><strong>Peso:</strong> 105 kg</li>
          <li><strong>Sospensioni:</strong> Regolabili per una guida ottimale</li>
          <li><strong>Ruote:</strong> 11 pollici</li>
        </ul>

        <h3>Prezzo: €4.500</h3>
        
      </div>
    </section>
  </main>
</body>
</html>
