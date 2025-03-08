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
    <main>
        <section class="hero">
            <h1>Benvenuti nella sezione di acquisto Parti</h1>
            <p>Scopri la nostra gamma di componenti di ricambio per i tuoi kart.</p>
        </section>
        <div class="main-container">
          <div class="products-container">
        <?php
          require 'logic/requestParts.php';
          
          //i parametri sono: id, nome del pezzo, quantita`, prezzo       
          $parts =  explode("\n", $res);
          $ncol = 4;
          // Controlla se sono presenti righe non vuote
          if(count($parts) > 0 && !empty(trim($parts[0]))) {              
              // Per ogni riga, suddivide i campi utilizzando preg_split per gestire eventuali spazi multipli
              foreach($parts as $part) {
                  $part = trim($part);
                  if(empty($part)) continue;
                  $columns = preg_split('/\s+/', $part);
                  // Controlla che ci sia il numero di colonne necessario
                  if(count($columns) >= $ncol) {
                    echo '<div class="product">';
                    $namePart = str_replace('_',' ',$columns[1]);
                    echo /*'<a href="/products/' .$columns[1] .'.php">*/'<img src="immagini/Pezzi/' .$namePart .'.png" alt="' .$namePart .'">'; //</a>';
                    echo /*'<a href="/products/' .$columns[1] .'.php">*/ '<h1>' .$namePart .'</h1>';//</a>';
                    echo '<p><h3> prezzo: </h3>'.$columns[3] .'€</p>';
                    if($columns[2] > 0)
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
    </main>
</body>
</html>