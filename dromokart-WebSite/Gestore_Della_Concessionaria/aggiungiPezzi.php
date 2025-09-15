<?php
include '../default/footerHome.php';
include '../default/headerProfilo.php';
require '../logic/controlloLogin.php';
?>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Aggiungi Pezzi</title>
  <!-- Importa il font Roboto -->
  <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap" rel="stylesheet">
  <!-- Collegamento al file CSS esterno -->
  <link rel="stylesheet" href="../css/styles.css">
  <link rel="stylesheet" href="../css/registration.css">
</head>
<body>
  <div class="main-container">
  <div class="products-container">
    <?php
      require '../logic/requestData.php';
      $res = request('mostraPezzi', $socket);
      // i parametri sono: id, nome del pezzo, quantita`, prezzo

      $parts =  explode("\n", $res);
      $nval = 4;

      if(count($parts) > 0 && !empty(trim($parts[0]))) {
        foreach($parts as $part) {
          $part = trim($part);
          if(empty($part)) continue;
          $columns = preg_split('/\s+/', $part);
          // Controlla che ci sia il numero di colonne necessario
          if(count($columns) >= $nval) {
            $namePart = str_replace('_',' ',$columns[1]);
            echo '<div class="product">';
            echo '<img src="../immagini/Pezzi/' .$namePart .'.png" alt="' .$namePart .'">';
            echo '<h1>' .$namePart .'</h1>';         
            echo '<form method="post" action="../logic/insertParts.php">';
            echo '<input type="number" name="quantity" id="quantity" value=0 min=0 required>';
            echo '<input type="hidden" name="part" id="part" value="' .$columns[0] .'" required>';            
            echo '<button type="submit">Aggiungi</button>';
            echo '</form>';
            echo '<p>Quantità: ';
            
            if($columns[2] > 0){
              echo $columns[2] .'</p>';
            } else{
              echo 'esaurito</p>';
            }
            echo'</div>';
          }
        }
      } else {
        echo '<p>Nessun dato ricevuto.</p>';
      }
    ?>
    </div>
  </div>
</body>
</html>
<script>
  window.addEventListener('pageshow', function(event) {
    if (event.persisted || (window.performance && window.performance.navigation.type === 2)) {
      window.location.reload();
    }
  });
</script>
