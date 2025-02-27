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
          //require 'logic/requestParts.php';
          //queste di sotto sono delle prove
          $part[0][0] = "Motore 125cc";
          $part[0][1] = 3;
          $part[0][2] = 2000;
          $part[1][0] = "Carburatore 28";
          $part[1][1] = 7;
          $part[1][2] = 170;

          $i = 2;          

          for($j = 0; $j < $i; $j++){
            echo '<div class="product">';
            echo '<a href="/products/' .$part[$j][0] .'.php"><img src="immagini/Pezzi' .$part[$j][0] .'.png" alt="' .$part[$j][0] .'"></a>';
            echo '<a href="/products/' .$part[$j][0] .'.php"><h1>' .$part[$j][0] .'</h1></a>';
            echo '<p><h3> prezzo: </h3>'.$part[$j][2] .'€</p>';
            if($part[$j][1] > 0)
            echo '<p>Quantità: ';
            if($part[$j][1] > 0){
              echo $part[$j][1] .'</p>';
            } else{
              echo 'esaurito</p>';
            }
            
            echo'</div>';
          }
        ?>
        </div>

        </div>
    </main>
</body>
</html>