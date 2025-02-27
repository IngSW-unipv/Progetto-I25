<?php
  include 'default/headerConce.php';
  include 'default/footerConce.php';
  include 'default/modalHome.php';
?>

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
  

  <!-- Main Content -->

  <main>
    <section class="hero">
      <h1>Benvenuti nella concessionaria di CacioKart</h1>
      <p>Scopri la nostra vasta gamma di kart per tutte le età.</p>
    </section>

    <section class="scrollable-section">
      <div class="scrollable-container">
        <img src="immagini/Home/concess1.jpg" alt="Immagine Concessionario">
      </div>
    </section>

    <section class="description-section">
      <div class="description-text">
        <p>
          Da CacioKart, siamo orgogliosi di offrire una selezione di kart di alta qualità made in Italy. Date un occhio alla gamma di kart 125cc,
          che è stata aggiornata di recente con un innovativo sistema ad iniezione diretta.
        </p>
      </div>
    </section>

    <section class="hero">
      <h1>Acquista il Tuo Kart</h1>
      <p>Scegli il kart perfetto per te, disponibile in tre categorie: 50cc, 1252cc e 150cc.</p>
    </section>

    <!-- sezione vendita kart-->
    <div class="kart-container">
      <div class="kart-item">
        <div class="kart-image">
          <a href="kart50cc.php">
          <img src="immagini/Kart/50cc.png" alt="Kart 50cc">
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
          <a href="kart125cc.php">
          <img src="immagini/Kart/100cc.png" alt="Kart 125cc">
          </a>
        </div>
        <div class="kart-description">
          <a href="kart125cc.php">
          <h3>Kart 125cc</h3>
          </a>
          <p>Ideale per adolescenti e chi ha già esperienza con i kart. Potenza maggiore per chi cerca emozioni più forti. Ottimo per gare amatoriali.</p>
          <p><strong>Prezzo:</strong> 4.500€</p>
        </div>
      </div>
    
      <div class="kart-item">
        <div class="kart-image">
          <a href="kart150cc.php">
          <img src="immagini/Kart/150cc.png" alt="Kart 150cc">
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
</body>
</html>