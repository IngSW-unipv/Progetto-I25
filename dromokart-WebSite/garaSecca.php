<?php
// aggiuntaPenalita.php

// Include eventuali header o footer, se necessari
include 'default/headerProfilo.php';  // Se hai un header personalizzato
include 'default/footerHome.php';     // Se hai un footer
require 'logic/controlloLogin.php';   // Controllo login (opzionale)

$IdPrenotazione = isset($_POST['IdPrenotazione']) ? $_POST['IdPrenotazione'] : '';

?>

<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <title>Aggiungi soci</title>
  <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="css/registration.css">
</head>
<body>
  <h2>Dettagli della Prenotazione: <?php echo htmlspecialchars($IdPrenotazione); ?></h2>

  <div class="table-section">
    <?php
    require 'logic/requestData.php';
    $res = request("mostraSoci", $socket);
    // 4) Analizzo la risposta $res
    // Suddivido in righe usando "explode"
    $rows = explode("\n", trim($res));

    // Verifico se ho almeno una riga non vuota
    if (count($rows) > 0 && !empty(trim($rows[0]))) {
        echo '<table>';
        echo '  <thead>';
        echo '    <tr>';
        echo '      <th>CF</th>';
        echo '      <th>Nome</th>';
        echo '      <th>Cognome</th>';
        echo '      <th>Aggiungi Pilota</th>';
        echo '    </tr>';
        echo '  </thead>';
        echo '  <tbody>';

        foreach ($rows as $row) {
            $row = trim($row);
            if (empty($row)) continue;

            // Ogni riga contiene i campi separati da spazi
            $columns = preg_split('/\s+/', $row);

            if (count($columns) >= 3) {
                echo '<tr>';

                echo '  <td>' . htmlspecialchars($columns[0]) . '</td>'; 
                echo '  <td>' . htmlspecialchars($columns[1]) . '</td>';
                echo '  <td>' . htmlspecialchars($columns[2]) . '</td>';
                echo '  <td>';
                echo '    <form action="logic/aggiungiSociGaraSecca.php" method="post">';
                echo '      <input type="hidden" name="CF" value="'. htmlspecialchars($columns[0]) .'">';
                echo '      <input type="hidden" name="IdPrenotazione" value="'. htmlspecialchars($IdPrenotazione) .'">';
                

                echo '      <button type="submit">Aggiungi Pilota</button>';
                echo ' </td>';

                echo '    </form>';

                echo '</tr>';
            }
        }

        echo '  </tbody>';
        echo '</table>';
    } else {
        echo '<p>Nessun dato valido ricevuto dal server per questa gara.</p>';
    }
    ?>
  </div>
</body>
</html>
