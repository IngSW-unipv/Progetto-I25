<?php include 'default/footerHome.php'; ?>
<?php include 'default/headerProfilo.php'; ?>
<?php require 'logic/controlloLogin.php'; ?>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Riempi Serbatoio</title>
  <!-- Importa il font Roboto -->
  <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap" rel="stylesheet">
  <!-- Collegamento al file CSS esterno -->
  <link rel="stylesheet" href="css/registration.css">
</head>
<body>
  <div class="table-section">
    <?php
    require 'logic/requestData.php';
    $res = request("mostraKartRimuovi", $socket);
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
        echo '<th>Aggiungi</th>';
        echo '</tr>';
        echo '</thead>';
        echo '<tbody>';
        
        // Per ogni riga, suddivide i campi utilizzando preg_split per gestire eventuali spazi multipli
        foreach($rows as $row) {
            $row = trim($row);
            if(empty($row)) continue;
            $columns = preg_split('/\s+/', $row);
            // Assicuriamoci che ci siano almeno 3 colonne
            if(count($columns) >= 3) {
                echo '<tr>';
                echo '<td>' . htmlspecialchars($columns[0]) . '</td>';
                echo '<td>' . htmlspecialchars($columns[1]) . '</td>';
                echo '<td>' . htmlspecialchars($columns[2]) . '</td>';
                
                echo '<td>';
                echo '<form action="logic/aggiungiBenzina.php" method="post">';
                echo '<input type="hidden" id="targa" name="targa" value="' . htmlspecialchars($columns[0]) . '">';

                // Se il valore del serbatoio è 20, disabilitiamo il pulsante
                if ($columns[2] == 20) {
                    echo '<button type="submit" disabled style="background-color: #ccc; cursor: not-allowed;">Riempi Serbatoio</button>';
                } else {
                    echo '<button type="submit">Riempi Serbatoio</button>';
                }
                

                echo '</form>';
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
<script>
  window.addEventListener('pageshow', function(event) {
    if (event.persisted || (window.performance && window.performance.navigation.type === 2)) {
      window.location.reload();
    }
  });
</script>
