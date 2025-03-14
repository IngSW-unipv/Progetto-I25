<?php

// Include eventuali header o footer, se necessari
include 'default/headerProfilo.php';  // Se hai un header personalizzato
include 'default/footerHome.php';     // Se hai un footer
require 'logic/controlloLogin.php';   // Controllo login (opzionale)

// 1) Ricevi l'idGara passato dal form
$IdPrenotazione = isset($_POST['IdPrenotazione']) ? $_POST['IdPrenotazione'] : '';

// 2) Includi il file con la funzione che richiama il server Java
require_once 'logic/mostraSoci.php';

?>

<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Pagina Aggiunta Soci Gara</title>
  <!-- Importa il font Roboto -->
  <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap" rel="stylesheet">
  <!-- Collegamento al file CSS esterno -->
  <link rel="stylesheet" href="css/registration.css">
</head>
<body>
   <hero>
    <h1>Aggiunta Team Gara</h1>
   </hero>
    <section class="table-section">
      <!-- lista attributi: nome,colore -->
         <?php
            require 'logic/requestData.php';
            $res = request("richiestaTeam", $socket);
            // Suddivide $res in righe
            $rows = explode("\n", $res);
            
            // Controlla se sono presenti righe non vuote
            if(count($rows) > 0 && !empty(trim($rows[0]))) {
               echo '<table>';
               echo '<thead>';
               echo '<tr>';
               echo '<th>Nome</th>';
               echo '<th>Colore</th>';
               echo '<th>Aggiunta Team</th>';
               echo '</tr>';
               echo '</thead>';
               echo '<tbody>';
               
               // Per ogni riga, suddivide i campi utilizzando preg_split per gestire eventuali spazi multipli
               foreach($rows as $row) {
                  $row = trim($row);
                  if(empty($row)) continue;
                  $columns = preg_split('/\s+/', $row);
                  // Assicurati che ci siano almeno 2 colonne
                  if(count($columns) >= 2) {
                     echo '<tr>';
                     echo '<td>' . htmlspecialchars($columns[0]) . '</td>';
                     echo '<td>' . htmlspecialchars($columns[1]) . '</td>';
                     echo '<td> <form action="logic/aggiungiSociGaraSecca.php" method="post"> <input type="hidden" id="nome" name="nome" value="' .htmlspecialchars($columns[0]) .'"> ';
                     echo '<button type="submit">Rimuovi dipendente</button> </form></td>';
                     echo '</tr>';
                  }
               }
               
               echo '</tbody>';
               echo '</table>';
            } else {
               echo '<p>Nessun dato ricevuto.</p>';
            }
         ?>
         </section>
    </section>

</body>
</html>