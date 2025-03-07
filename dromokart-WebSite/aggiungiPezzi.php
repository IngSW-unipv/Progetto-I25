<?php include 'default/footerHome.php'; ?>
<?php include 'default/headerProfilo.php'; ?>
<?php require 'logic/controlloLogin.php'; ?>
<!DOCTYPE html>
<html lang="it">
<head>
<!--
<style>
   
    .product button {
      padding: 10px 20px;
      font-size: 16px;
      cursor: pointer;
      background-color: green;
      color: white;
      border: none;
      border-radius: 5px;
    }
    .product button:hover {
      background-color: darkgreen;
    }

    input[type="number"] {
        width: 80px;
        padding: 10px;
        font-size: 18px;
        border: 2px solid #ddd;
        border-radius: 10px;
        outline: none;
        text-align: center;
        transition: all 0.3s ease;
    }

    input[type="number"]:focus {
        border-color: #4CAF50;
        box-shadow: 0 0 10px rgba(76, 175, 80, 0.4);
    }

    input[type="number"]::-webkit-outer-spin-button,
    input[type="number"]::-webkit-inner-spin-button {
        -webkit-appearance: inner-spin-button;
        margin: 0;
    }

  </style>-->
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Aggiungi Pezzi</title>
  <!-- Importa il font Roboto -->
  <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap" rel="stylesheet">
  <!-- Collegamento al file CSS esterno -->
  <link rel="stylesheet" href="css/styles.css">
  <link rel="stylesheet" href="css/registration.css">
</head>
<body>
  <div class="main-container">
  <div class="products-container">
    <?php
      require 'logic/requestData.php';
      $res = request('mostraPezzi', $socket);
      //i parametri sono: id, nome del pezzo, quantita`, prezzo 

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
            echo /*'<a href="/products/' .$columns[1] .'.php">*/'<img src="immagini/Pezzi/' .$namePart .'.png" alt="' .$namePart .'">'; //</a>';
            echo /*'<a href="/products/' .$columns[1] .'.php">*/ '<h1>' .$namePart .'</h1>';//</a>';            
            echo '<form method="post" action="logic/insertParts.php">';
            echo '<input type="number" name="quantity" id="quantity" value=0 min=0 required>';
            echo '<input type="hidden" name="part" id="part" value="' .$columns[0] .'" required>';            
            echo '<button type="submit">Inserisci</button>';
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
<!--
<div class="container">
    <-- Primo prodotto --
    <div class="product-card">
      <img src="/immagini/motore.png" alt="Motore">
      <p>Motore 150cc</p>
      <div class="quantity-controls">
        <button onclick="updateQuantity(this, -1)">-</button>
        <span class="quantity">0</span>
        <button onclick="updateQuantity(this, 1)">+</button>
      </div>
    </div>
    
    -->
 
<!--
  <script>
    // Funzione per aggiornare la quantità
    function updateQuantity(button, delta) {
      var quantitySpan = button.parentElement.querySelector('.quantity');
      var currentQuantity = parseInt(quantitySpan.textContent, 10);
      var newQuantity = currentQuantity + delta;
      if (newQuantity < 0) {
        newQuantity = 0; // Impedisce quantità negative
      }
      quantitySpan.textContent = newQuantity;
    }
  </script>
    -->

</body>
</html>