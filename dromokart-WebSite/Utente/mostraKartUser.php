<?php include '../default/footerHome.php'; ?>
<?php include '../default/headerProfilo.php'; ?>
<?php require '../logic/controlloLogin.php'; ?>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Kart posseduto - Dromokart</title>
  <!-- Importa il font Roboto -->
  <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap" rel="stylesheet">
  <!-- Collegamento al file CSS esterno -->
  <link rel="stylesheet" href="../css/styles.css">

</head>
<body>
  <!-- Hero Section -->
  <section class="hero">
    <h1>Kart Posseduto</h1>
  </section>
  
  <!-- Contenuto principale -->
  <main>
   
    <?php
      //
      require_once '../logic/requestData.php';
      $msg = 'richiestaKartUsr ' .$_SESSION['username'];
      $res = request($msg, $socket);

      if(strcmp($res, "Nessun kart associato al socio.") != 0){
        require '../logic/tableCreation.php';
        echo '<div class="table-section">';

        $titolo = array("Targa", "Cilindrata", "Serbatoio");
        createTable($titolo, $res);
        echo '</div>';
      }
      else{
        echo '<div class=description-section>';
        echo '<h3>Non possiedi un kart</h3>';
        echo '</div>';
      }
      
    ?>
    <div class="profile-buttons">
      <button onclick="window.location.href='profilo.php';">Torna indietro</button>
    </div>
  </main>
  
</body>
</html>
