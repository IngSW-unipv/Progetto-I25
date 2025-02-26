<?php include 'default/footerHome.php'; ?>
<?php include 'default/headerProfilo.php'; ?>
<?php include 'logic/mostrakart.php'; ?>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Elimina Kart</title>
  <!-- Importa il font Roboto -->
  <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap" rel="stylesheet">
  <!-- Collegamento al file CSS esterno -->
  <link rel="stylesheet" href="css/registration.css">
  <style>
    table { 
      border-collapse: collapse; 
      width: 100%; 
    }
    th, td { 
      border: 1px solid #ddd; 
      padding: 8px; 
      text-align: center; /* Testo centrato */
    }
    th { 
      background-color: #4CAF50; 
    }
  </style>

</head>
<body>
    <div>
        <?php
        // Suddivide $res in righe
        $rows = explode("\n", $res);
        
        // Controlla se sono presenti righe non vuote
        if(count($rows) > 0 && !empty(trim($rows[0]))) {
            echo '<table>';
            echo '<thead>';
            echo '<tr>';
            echo '<th>Targa</th>';
            echo '<th>Cilindrata</th>';
            echo '<th>Serbatoio</th>';
            echo '</tr>';
            echo '</thead>';
            echo '<tbody>';
            
            // Per ogni riga, suddivide i campi utilizzando preg_split per gestire eventuali spazi multipli
            foreach($rows as $row) {
                $row = trim($row);
                if(empty($row)) continue;
                $columns = preg_split('/\s+/', $row);
                // Assicurati che ci siano almeno 3 colonne
                if(count($columns) >= 3) {
                    echo '<tr>';
                    echo '<td>' . htmlspecialchars($columns[0]) . '</td>';
                    echo '<td>' . htmlspecialchars($columns[1]) . '</td>';
                    echo '<td>' . htmlspecialchars($columns[2]) . '</td>';
                    echo '</tr>';
                }
            }
            
            echo '</tbody>';
            echo '</table>';
        } else {
            echo '<p>Nessun dato ricevuto.</p>';
        }
        ?>



  <!-- Main Content - Form di Registrazione -->
  <main>

  

    <section class="form-section">
      <h1>Elimina Kart</h1>
      <form action="logic/eliminazioneKart.php" method="post" class="registration-form">
         <div class="form-group">
            <label for="targa">Targa</label>
            <input type="text" id="targa" name="targa" maxlength="" required>
         </div>
         <div class="form-group">
            <button type="submit">Elimina Kart</button>
         </div>
      </form>
    </section>
  </main>

</body>
</html>
