<?php
  include 'default/headerProprietario.php';
  include 'default/footerConce.php';
?>
<?php require 'logic/controlloLogin.php'; ?>

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