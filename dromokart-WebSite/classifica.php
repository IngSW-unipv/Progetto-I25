<?php include 'default/headerHome.php'; ?>
<?php include 'default/footerHome.php'; ?>
<?php include 'default/modalHome.php'; ?>



<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Dromokart - Classifica</title>
  <!-- Importa un font moderno -->
  <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap" rel="stylesheet">
  <!-- Collegamento al file CSS esterno -->
  <link rel="stylesheet" href="css/styles.css">
  <link rel="stylesheet" href="css/registration.css">
</head>
<body>

  <!-- Hero Section -->
  <section class="hero">
    <h1>Classifica</h1>
    <p>Consulta la classifica dei piloti</p>
  </section>

  <!-- Contenuto principale -->
  <main>
    <div class="table-section">

   
    <?php
      //id gara, socio, targa, miglior giro, tempo totale (mm,ss,dd)
      require 'logic/requestPlacingsGen.php';
      require 'logic/tableCreation.php';

      $titolo = array("ID Gara", "NomePilota", "CognomePilota", "Targa", "Miglior Giro", "Tempo totale");
      createTable($titolo, $res); 
      
    ?>
    </div>
  </main>

</body>
</html>
