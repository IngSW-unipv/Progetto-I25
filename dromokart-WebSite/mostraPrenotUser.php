<?php include 'default/footerHome.php'; ?>
<?php include 'default/headerProfilo.php'; ?>
<?php require 'logic/controlloLogin.php'; ?>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Prenotazioni Personali - Dromokart</title>
  <!-- Importa il font Roboto -->
  <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap" rel="stylesheet">
  <!-- Collegamento al file CSS esterno -->
  <link rel="stylesheet" href="css/styles.css">
  <link rel="stylesheet" href="css/registration.css">

</head>
<body>
  <!-- Hero Section -->
  <section class="hero">
    <h1>Prenotazioni effettuate</h1>
  </section>
  
  <!-- Contenuto principale -->
  <main>
    
   
    <?php
      //
      require_once 'logic/requestData.php';
      $msg = 'richiestaPren ' .$_SESSION['username'];
      $res = request($msg, $socket);

      if(strcmp($res, "Nessun dato ricevuto.") != 0){
        require 'logic/tableCreation.php';
        echo '<div class="table-section">';

        $titolo = array("Data", "Fascia oraria", "Tipo gara");
        createTable($titolo, $res);
        echo '</div>';
      }
      else{
        echo '<div class=description-section>';
        echo '<h1>Non ci sono prenotazioni</h1>';
        echo '</div>';
      }

    ?>
    <div class="profile-buttons">
        <button onclick="window.location.href='profilo.php';">Torna indietro</button>
    </div>
  </main>
  
</body>
</html>
