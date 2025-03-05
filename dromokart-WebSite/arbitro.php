<?php include 'default/footerHome.php'; ?>
<?php include 'default/headerProfilo.php'; ?>
<?php require 'logic/controlloLogin.php'; ?>
<?php require 'logic/richiestaGare.php'; ?>


<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Profilo Privato - Albitro</title>
  <!-- Importa il font Roboto -->
  <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap" rel="stylesheet">
  <!-- Collegamento al file CSS esterno -->
  <link rel="stylesheet" href="css/profilo.css">
  <link rel="stylesheet" href="css/registration.css">
</head>
<body>
 
  <!-- Hero Section -->
  <section class="hero">
    <h2>Benvenuto, <?php echo htmlspecialchars($name); ?>!</h2>
  </section>

  <div class="table-section">
        <?php
        // Suddivide $res in righe
        $rows = explode("\n", $res);
        
        // Controlla se sono presenti righe non vuote
        if(count($rows) > 0 && !empty(trim($rows[0]))) {
            echo '<table>';
            echo '<thead>';
            echo '<tr>';
            echo '<th>idGara</th>';
            echo '<th>Qualifica</th>';
            echo '</tr>';
            echo '</thead>';
            echo '<tbody>';
            
            // Per ogni riga, suddivide i campi utilizzando preg_split per gestire eventuali spazi multipli
            foreach($rows as $row) {
                $row = trim($row);
                if(empty($row)) continue;
                $columns = preg_split('/\s+/', $row);
                // Assicurati che ci siano almeno 3 colonne
                if(count($columns) >= 1) {
                    echo '<tr>';
                    echo '<td>' . htmlspecialchars($columns[0]) . '</td>';
                }
            }
            echo '</tbody>';
            echo '</table>';
        } else {
            echo '<p>Nessun dato ricevuto.</p>';
        }
        ?>


  </main>
</body>
</html>
