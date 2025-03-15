<?php
include 'default/footerConce.php';
?>

<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Pagina Bilancio</title>
  <!-- Importa il font Roboto -->
  <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap" rel="stylesheet">
  <!-- Collegamento al file CSS esterno -->
  <link rel="stylesheet" href="css/profilo.css">
</head>
  <!-- Header -->
  <header>
    <div class="header-container">
      <div class="logo">
        <a href="index.php">
          <img src="immagini/LOGO_KART.png" alt="Logo CacioKart">
        </a>
      </div>
      <nav>
        <ul>
          <li><a href="proprietario.php">Home</a></li>
          <li><a href="default/logout.php" id="logoutBtn">Logout</a></li>
        </ul>
      </nav>
    </div>
  </header>

  <body>
    <div class="table-section">
        <?php
        require 'logic/requestData.php';
        $res = request("mostraBilancio", $socket);
        // Suddivide $res in righe
        $rows = explode("\n", $res);
        
        // Controlla se sono presenti righe non vuote
        if(count($rows) > 0 && !empty(trim($rows[0]))) {
            echo '<table>';
            echo '<thead>';
            echo '<tr>';
            echo '<th>Entrate</th>';
            echo '<th>Uscite</th>';
            echo '<th>Guadagni</th>';
            echo '</tr>';
            echo '</thead>';
            echo '<tbody>';
            
            foreach($rows as $row) {
                $row = trim($row);
                if(empty($row)) continue;
                $columns = preg_split('/\s+/', $row);
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
</body>