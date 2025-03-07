<?php
// aggiuntaPenalita.php

// Include eventuali header o footer, se necessari
include 'default/headerProfilo.php';  // Se hai un header personalizzato
include 'default/footerHome.php';     // Se hai un footer
require 'logic/controlloLogin.php';   // Controllo login (opzionale)

// 1) Ricevi l'idGara passato dal form
$idGara = isset($_POST['idGara']) ? $_POST['idGara'] : '';

// 2) Includi il file con la funzione che richiama il server Java
require_once 'logic/selezioneGara.php';

?>

<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <title>Aggiunta Penalità</title>
  <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="css/registration.css">
</head>
<body>
  <h2>Dettagli della Gara: <?php echo htmlspecialchars($idGara); ?></h2>

  <div class="table-section">
    <?php
    // 4) Analizzo la risposta $res
    // Suddivido in righe usando "explode"
    $rows = explode("\n", trim($res));

    // Verifico se ho almeno una riga non vuota
    if (count($rows) > 0 && !empty(trim($rows[0]))) {
        echo '<table>';
        echo '  <thead>';
        echo '    <tr>';
        echo '      <th>idGara</th>';
        echo '      <th>Socio</th>';
        echo '      <th>targa</th>';
        echo '      <th>bGiro</th>';
        echo '      <th>tempoTot</th>';
        echo '      <th>Penalità</th>';
        echo '      <th>Aggiungi Penalità</th>';
        echo '    </tr>';
        echo '  </thead>';
        echo '  <tbody>';

        foreach ($rows as $row) {
            $row = trim($row);
            if (empty($row)) continue;

            // Ogni riga contiene i campi separati da spazi
            $columns = preg_split('/\s+/', $row);

            // Esempio: ci aspettiamo almeno 7-8 colonne
            if (count($columns) >= 5) {
                echo '<tr>';

                // Stampiamo i primi 7 campi in tabella
                // (idGara, socio, targa, bGiro, tempoTot, penalità)
                // Adatta l'indice in base al formato che arriva dal server Java
                echo '  <td>' . htmlspecialchars($columns[0]) . '</td>'; 
                echo '  <td>' . htmlspecialchars($columns[1]) . '</td>';
                echo '  <td>' . htmlspecialchars($columns[2]) . '</td>';
                echo '  <td>' . htmlspecialchars($columns[3]) . '</td>';
                echo '  <td>' . htmlspecialchars($columns[4]) . '</td>';

                // Se ci fosse un ottavo campo, puoi stamparlo
                // echo '  <td>' . htmlspecialchars($columns[7]) . '</td>';

                // Pulsante "Aggiungi Penalità"
                // Qui decidi come vuoi gestire l'aggiunta di penalità.
                // Ad esempio, potresti inviare un form ad un'altra pagina "logic/aggiungiPenalita.php"
                // che poi fa la logica necessaria (aggiornare il DB, il server Java, ecc.)
                echo '  <td>';
                echo '    <form action="logic/aggiungiPenalita.php" method="post">';
                echo '      <input type="time" name="time" value="0">';
                echo '      <input type="hidden" name="idGara" value="'. htmlspecialchars($columns[0]) .'">';
                echo '      <input type="hidden" name="Socio" value="'. htmlspecialchars($columns[1]) .'">';
                echo ' </td>';
                echo '  <td>';
                // Se serve anche un campo "penalità" da inserire:
                // echo '      <input type="number" name="valorePenalita" placeholder="Penalità" min="0">';
                echo '      <button type="submit">Aggiungi penalità</button>';
                echo '    </form>';
                echo '  </td>';

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
