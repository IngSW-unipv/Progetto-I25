<?php include '../default/footerHome.php'; ?>
<?php include '../default/headerProfilo.php'; ?>
<?php require '../logic/controlloLogin.php'; ?>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Classifica Personale - Dromokart</title>
  <!-- Importa il font Roboto -->
  <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap" rel="stylesheet">
  <!-- Collegamento al file CSS esterno -->
  <link rel="stylesheet" href="../css/styles.css">

</head>
<body>
  <!-- Hero Section -->
  <section class="hero">
    <h1>Classifica</h1>
    <p>Consulta la tua classifica</p>
  </section>
  
  <!-- Contenuto principale -->
  <main>
    <div class="table-section">
   
    <?php
      //id gara, targa, miglior giro, tempo totale (mm,ss,dd)
      require '../logic/requestPlacingsUsr.php';
      require '../logic/tableCreation.php';

      $titolo = array("ID Gara", "Targa", "Miglior Giro", "Tempo totale");
      createTable($titolo, $res); 
    ?>
    </div>
  </main>
  
</body>
</html>
