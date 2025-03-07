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
        echo '<th>IdGara</th>';
        echo '<th>Seleziona</th>';
        echo '</tr>';
        echo '</thead>';
        echo '<tbody>';
        
        // Per ogni riga, suddivide i campi
        foreach($rows as $row) {
            $row = trim($row);
            if(empty($row)) continue;

            // Suddividi le colonne in base agli spazi
            $columns = preg_split('/\s+/', $row);

            // Assicurati che esista almeno un valore
            if(count($columns) >= 1) {
                $idGara = htmlspecialchars($columns[0]);
                
                echo '<tr>';
                // Colonna IdGara
                echo '<td>' . $idGara . '</td>';
                // Colonna con bottone "Seleziona"
                echo '<td>';
                echo '  <form action="logic/selezioneGara.php" method="post">';
                echo '    <input type="hidden" name="idGara" value="' . $idGara . '">';
                echo '    <button type="submit">Seleziona</button>';
                echo '  </form>';
                echo '</td>';
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
</body>
</html>
