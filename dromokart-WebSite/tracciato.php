
<?php include 'default/header.php'; ?>
<?php include 'default/modal.php'; ?>

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

  <footer>
    <p>&copy; 2025 CacioKart S.R.L. Tutti i diritti riservati.</p>
    <p>contatti - telefono: +39 3334455678  email: kaciokart@universitadipavia.it</p>
</footer>
  
</body>
</html>
