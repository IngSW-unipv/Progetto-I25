<?php
  include 'default/headerProprietario.php';
  include 'default/footerConce.php';
?>
<?php require 'logic/controlloLogin.php'; ?>


<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Pagina Rimozione dipendenti</title>
  <!-- Importa il font Roboto -->
  <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap" rel="stylesheet">
  <!-- Collegamento al file CSS esterno -->
  <link rel="stylesheet" href="css/registration.css">
</head>
<body>
   <hero>
    <h1>Elimina un dipendente</h1>
   </hero>
    <section class="table-section">
      <!-- lista attributi: cf, nome, cognome, mail, password, data_n, ruolo, ore_lavorate, stipendio -->
         <?php
            require 'logic/requestData.php';
            $res = request("richiestaDipen", $socket);
            // Suddivide $res in righe
            $rows = explode("\n", $res);
            
            // Controlla se sono presenti righe non vuote
            if(count($rows) > 0 && !empty(trim($rows[0]))) {
               echo '<table>';
               echo '<thead>';
               echo '<tr>';
               echo '<th>Codice Fiscale</th>';
               echo '<th>Nome</th>';
               echo '<th>Cognome</th>';
               echo '<th>Rimozione</th>';
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
                     echo '<td> <form action="logic/deleteDip.php" method="post"> <input type="hidden" id="codice_fiscale" name="codice_fiscale" value="' .htmlspecialchars($columns[0]) .'"> ';
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
<script>
  window.addEventListener('pageshow', function(event) {
    if (event.persisted || (window.performance && window.performance.navigation.type === 2)) {
      window.location.reload();
    }
  });
</script>