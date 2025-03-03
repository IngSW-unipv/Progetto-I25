<?php include 'default/footerHome.php'; ?>
<?php include 'default/headerProfilo.php'; ?>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Classifica Personale - Dromokart</title>
  <!-- Importa il font Roboto -->
  <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap" rel="stylesheet">
  <!-- Collegamento al file CSS esterno -->
  <link rel="stylesheet" href="css/migliori_tempi.css">

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
      require 'logic/requestPlacingsUsr.php';

      // Suddivide $res in righe
      $rows = explode("\n", $res);
      
      // Controlla se sono presenti righe non vuote
      if(count($rows) > 0 && !empty(trim($rows[0]))) {
          echo '<table>';
          echo '<thead>';
          echo '<tr>';
          echo '<th>ID Gara</th>';
          echo '<th>Targa</th>';
          echo '<th>Miglior Giro</th>';
          echo '<th>tempo totale</th>';
          echo '</tr>';
          echo '</thead>';
          echo '<tbody>';
          
          // Per ogni riga, suddivide i campi utilizzando preg_split per gestire eventuali spazi multipli
          foreach($rows as $row) {
            $row = trim($row);
            if(empty($row)) continue;
            $columns = preg_split('/\s+/', $row);
            // Assicurati che ci siano almeno 3 colonne
            if(count($columns) >= 5) {
                echo '<tr>';
                echo '<td>' . htmlspecialchars($columns[0]) . '</td>';
                echo '<td>' . htmlspecialchars($columns[1]) . '</td>';
                echo '<td>' . htmlspecialchars($columns[2]) . '</td>';
                echo '<td>' . htmlspecialchars($columns[3]) . '</td>';
                echo '</tr>';
            }
          }
          
          echo '</tbody>';
          echo '</table>';
      } else {
          echo '<p>Nessun dato ricevuto.</p>';
      }
    ?>
    </div>
  </main>
  
</body>
</html>
