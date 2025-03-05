<?php include 'default/footerHome.php'; ?>
<?php include 'default/headerProfilo.php'; ?>
<?php require 'logic/controlloLogin.php'; ?>
<?php include 'logic/mostrakartAggiunta.php'; ?>

<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Acquista Kart</title>
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
            echo '<th>Targa</th>';
            echo '<th>Cilindrata</th>';
            echo '<th>Serbatoio</th>';
            echo '<th>Acquista</th>';
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
                    echo '<td> <form action="logic/acquistaKart.php" method="post"> <input type="hidden" id="targa" name="targa" value="' .htmlspecialchars($columns[0]) .'"> ';
                    echo '<button type="submit">Acquista Kart</button> </form></td>';
                    echo '</tr>';
                }
            }
            
            echo '</tbody>';
            echo '</table>';
        } else {
            echo '<p>Nessun dato ricevuto.</p>';
        }
        ?>
</body>
</html>
