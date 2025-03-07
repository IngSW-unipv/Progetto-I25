<?php include 'default/footerHome.php'; ?>
<?php include 'default/headerProfilo.php'; ?>
<?php require 'logic/controlloLogin.php'; ?>

<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Acquista Pezzi</title>
  <!-- Importa il font Roboto -->
  <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap" rel="stylesheet">
  <!-- Collegamento al file CSS esterno -->
  <link rel="stylesheet" href="css/registration.css">
</head>
<body>
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
            echo '<th>nome</th>';
            echo '<th>cognome</th>';
            echo '<th>targa</th>';
            echo '<th>bGiro</th>';
            echo '<th>tempoTot</th>';
            echo '<th>Penalità</th>';
            echo '<th>aggiungi Penalità</th>';
            echo '</tr>';
            echo '</thead>';
            echo '<tbody>';
            
            foreach($rows as $row) {
                $row = trim($row);
                if(empty($row)) continue;
                $columns = preg_split('/\s+/', $row);

                // Assicurati che ci siano almeno 8 campi
                if(count($columns) >= 8) {
                    echo '<tr>';
                    // Colonne testuali
                    echo '<td>' . htmlspecialchars($columns[0]) . '</td>';
                    echo '<td>' . htmlspecialchars($columns[1]) . '</td>';
                    echo '<td>' . htmlspecialchars($columns[2]) . '</td>';
                    echo '<td>' . htmlspecialchars($columns[3]) . '</td>';
                    echo '<td>' . htmlspecialchars($columns[4]) . '</td>'; 
                    echo '<td>' . htmlspecialchars($columns[5]) . '</td>'; 
                    echo '<td>' . htmlspecialchars($columns[6]) . '</td>';

                    // Colonna con il pulsante "Aggiungi Penalità"
                    echo '<td>';
                    echo '  <form action="logic/selezioneGara.php" method="post">';
                    // Passa l'idGara come input nascosto
                    echo '    <input type="hidden" name="idGara" value="' . htmlspecialchars($columns[0]) . '">';
                    echo '    <button type="submit">Aggiungi penalità</button>';
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
