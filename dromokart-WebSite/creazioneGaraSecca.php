<?php
include 'default/headerProfilo.php';     // Header personalizzato per la sezione
include 'default/footerHome.php';        // Footer del sito
require 'logic/controlloLogin.php';      // Verifica se l'utente è loggato
?>

<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Riempimento Prenotazione</title>
  <!-- Fogli di stile -->
  <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="css/profilo.css">
  <link rel="stylesheet" href="css/registration.css">
</head>
<body>

  <div class="table-section">
    <?php
    require 'logic/requestData.php';
    $res = request("richiestaGaraSecca", $socket);
    // Suddivido $res per righe
    $rows = explode("\n", $res);

    // Verifico se c'è almeno una riga non vuota
    if(count($rows) > 0 && !empty(trim($rows[0]))) {
        echo '<table>';
        echo '  <thead>';
        echo '    <tr>';
        echo '      <th>IdPrenotazione</th>';
        echo '      <th>Seleziona</th>';
        echo '    </tr>';
        echo '  </thead>';
        echo '  <tbody>';

        // Per ogni riga di $res
        foreach($rows as $row) {
            // Elimino spazi bianchi di contorno
            $row = trim($row);
            // Se la riga è vuota, salto
            if(empty($row)) continue;

            // Suddivido la riga (in questo caso potrebbe contenere solo l'IdPrenotazione)
            $columns = preg_split('/\s+/', $row);
            
            // Estraggo IdPrenotazione (se la riga ha almeno 1 colonna)
            if(isset($columns[0])) {
                $IdPrenotazione = htmlspecialchars($columns[0]);
                
                echo '<tr>';
                // Colonna con l'IdPrenotazione
                echo '  <td>' . $IdPrenotazione . '</td>';
                // Colonna con bottone "Seleziona"
                echo '  <td>';
                echo '    <form action="garaSecca.php" method="post">';
                echo '      <!-- Passo l\'IdPrenotazione come input nascosto -->';
                echo '      <input type="hidden" name="IdPrenotazione" value="' . $IdPrenotazione . '">';
                echo '      <button type="submit">Seleziona</button>';
                echo '    </form>';
                echo '  </td>';
                echo '</tr>';
            }
        }

        echo '  </tbody>';
        echo '</table>';
    } else {
        echo '<p>Nessun dato ricevuto.</p>';
    }
    ?>
  </div>
</body>
</html>
