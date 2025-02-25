<?php include 'default/footerHome.php'; ?>
<?php include 'default/headerProfilo.php'; ?>
<!DOCTYPE html>
<html lang="it">
<head>

<style>
   /* Stili per la griglia dei prodotti */
   .container {
      display: grid;
      grid-template-columns: repeat(3, 1fr);
      gap: 20px;
      max-width: 1200px;
      margin: 0 auto;
      padding: 20px;
    }
    .product-card {
      border: 1px solid #ccc;
      padding: 10px;
      text-align: center;
      border-radius: 5px;
      box-shadow: 0 2px 4px rgba(0,0,0,0.1);
    }
    .product-card img {
      max-width: 100%;
      height: auto;
      margin-bottom: 10px;
    }
    .quantity-controls {
      display: flex;
      align-items: center;
      justify-content: center;
      margin-top: 10px;
    }
    .quantity-controls button {
      padding: 5px 10px;
      margin: 0 5px;
      font-size: 16px;
      cursor: pointer;
    }
    .quantity {
      font-size: 16px;
      min-width: 30px;
      text-align: center;
    }
    /* Stili per il bottone Aggiorna */
    .update-button {
      text-align: center;
      margin: 20px 0;
    }
    .update-button button {
      padding: 10px 20px;
      font-size: 16px;
      cursor: pointer;
      background-color: green;
      color: white;
      border: none;
      border-radius: 5px;
    }
    .update-button button:hover {
      background-color: darkgreen;
    }

  </style>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Aggiungi Pezzi</title>
  <!-- Importa il font Roboto -->
  <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap" rel="stylesheet">
  <!-- Collegamento al file CSS esterno -->
  <link rel="stylesheet" href="css/registration.css">
</head>
<body>

<div class="container">
    <!-- Primo prodotto -->
    <div class="product-card">
      <img src="/immagini/motore.png" alt="Motore">
      <p>Motore 150cc</p>
      <div class="quantity-controls">
        <button onclick="updateQuantity(this, -1)">-</button>
        <span class="quantity">0</span>
        <button onclick="updateQuantity(this, 1)">+</button>
      </div>
    </div>
    
    <!-- Secondo prodotto -->
    <div class="product-card">
      <img src="immagine2.jpg" alt="Prodotto 2">
      <p>Descrizione prodotto 2</p>
      <div class="quantity-controls">
        <button onclick="updateQuantity(this, -1)">-</button>
        <span class="quantity">0</span>
        <button onclick="updateQuantity(this, 1)">+</button>
      </div>
    </div>
    
    <!-- Terzo prodotto -->
    <div class="product-card">
      <img src="immagine3.jpg" alt="Prodotto 3">
      <p>Descrizione prodotto 3</p>
      <div class="quantity-controls">
        <button onclick="updateQuantity(this, -1)">-</button>
        <span class="quantity">0</span>
        <button onclick="updateQuantity(this, 1)">+</button>
      </div>
    </div>
    
    <!-- Quarto prodotto -->
    <div class="product-card">
      <img src="immagine4.jpg" alt="Prodotto 4">
      <p>Descrizione prodotto 4</p>
      <div class="quantity-controls">
        <button onclick="updateQuantity(this, -1)">-</button>
        <span class="quantity">0</span>
        <button onclick="updateQuantity(this, 1)">+</button>
      </div>
    </div>
    
    <!-- Quinto prodotto -->
    <div class="product-card">
      <img src="immagine5.jpg" alt="Prodotto 5">
      <p>Descrizione prodotto 5</p>
      <div class="quantity-controls">
        <button onclick="updateQuantity(this, -1)">-</button>
        <span class="quantity">0</span>
        <button onclick="updateQuantity(this, 1)">+</button>
      </div>
    </div>
    
    <!-- Sesto prodotto -->
    <div class="product-card">
      <img src="immagine6.jpg" alt="Prodotto 6">
      <p>Descrizione prodotto 6</p>
      <div class="quantity-controls">
        <button onclick="updateQuantity(this, -1)">-</button>
        <span class="quantity">0</span>
        <button onclick="updateQuantity(this, 1)">+</button>
      </div>
    </div>
    
    <!-- Settimo prodotto -->
    <div class="product-card">
      <img src="immagine7.jpg" alt="Prodotto 7">
      <p>Descrizione prodotto 7</p>
      <div class="quantity-controls">
        <button onclick="updateQuantity(this, -1)">-</button>
        <span class="quantity">0</span>
        <button onclick="updateQuantity(this, 1)">+</button>
      </div>
    </div>
    
    <!-- Ottavo prodotto -->
    <div class="product-card">
      <img src="immagine8.jpg" alt="Prodotto 8">
      <p>Descrizione prodotto 8</p>
      <div class="quantity-controls">
        <button onclick="updateQuantity(this, -1)">-</button>
        <span class="quantity">0</span>
        <button onclick="updateQuantity(this, 1)">+</button>
      </div>
    </div>
    
    <!-- Nono prodotto -->
    <div class="product-card">
      <img src="immagine9.jpg" alt="Prodotto 9">
      <p>Descrizione prodotto 9</p>
      <div class="quantity-controls">
        <button onclick="updateQuantity(this, -1)">-</button>
        <span class="quantity">0</span>
        <button onclick="updateQuantity(this, 1)">+</button>
      </div>
    </div>
  </div>
  
  <!-- Bottone Aggiorna che richiama aggiuntaPezzi.php -->
  <div class="update-button">
    <button onclick="window.location.href='aggiuntaPezzi.php'">Aggiorna</button>
  </div>

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


</body>
</html>