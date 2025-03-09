<?php
  include 'default/headerProfilo.php';
  include 'default/footerConce.php';

?>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Creazione Team</title>
  <!-- Importa il font Roboto -->
  <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap" rel="stylesheet">
  <!-- Collegamento al file CSS esterno -->
  <link rel="stylesheet" href="css/registration.css">

  <script>
    // Script per consentire di selezionare al massimo 2 checkbox
    function handleCheckboxClick(checkbox) {
      // Recupera tutte le checkbox
      const checkboxes = document.querySelectorAll('input[name="codice_fiscale[]"]');
      // Conta quante sono già selezionate
      let countChecked = 0;
      checkboxes.forEach(cb => {
        if (cb.checked) {
          countChecked++;
        }
      });
      // Se ho appena selezionato e il totale supera 2, deseleziono l’ultimo check
      if (countChecked > 2) {
        checkbox.checked = false;
        alert("Puoi selezionare al massimo due codici fiscali!");
      }
    }
  </script>
</head>
<body>
  <hero>
    <h1>Creazione Team</h1>
  </hero>

  <section class="table-section">
    <?php
      require 'logic/requestData.php';

      // Richiesta dei dipendenti
      $res = request("richiestaDipen", $socket);

      // Suddivide $res in righe
      $rows = explode("\n", $res);

      // Controlla se sono presenti righe non vuote
      if(count($rows) > 0 && !empty(trim($rows[0]))) {
         echo '<form action="logic/addTeam.php" method="post">'; 
         echo '<table>';
         echo '<thead>';
         echo '<tr>';
         echo '<th>Codice Fiscale</th>';
         echo '<th>Nome</th>';
         echo '<th>Cognome</th>';
         echo '<th>Seleziona</th>';
         echo '</tr>';
         echo '</thead>';
         echo '<tbody>';

         // Per ogni riga, suddivide i campi utilizzando preg_split
         foreach($rows as $row) {
            $row = trim($row);
            if(empty($row)) continue;
            $columns = preg_split('/\s+/', $row);
            if(count($columns) >= 3) {
               echo '<tr>';
               echo '<td>' . htmlspecialchars($columns[0]) . '</td>'; // CF
               echo '<td>' . htmlspecialchars($columns[1]) . '</td>'; // Nome
               echo '<td>' . htmlspecialchars($columns[2]) . '</td>'; // Cognome
               // Checkbox: name="codice_fiscale[]" e value=CF
               echo '<td><input type="checkbox" name="codice_fiscale[]" value="' . htmlspecialchars($columns[0]) . '" onclick="handleCheckboxClick(this)" /></td>';
               echo '</tr>';
            }
         }
         echo '</tbody>';
         echo '</table>';

         // Campi di input: Nome Team e Colore Team
         echo '<div style="margin-top:20px;">';
         echo '<label for="nome_team">NOME TEAM</label><br>';
         echo '<input type="text" id="nome_team" name="nome_team" required /><br><br>';

         echo '<label for="colore_team">COLORE TEAM</label><br>';
         echo '<input type="text" id="colore_team" name="colore_team" required /><br><br>';
         echo '</div>';

         // Bottone invio
         echo '<button type="submit">Aggiungi</button>';

         echo '</form>';
      } else {
         echo '<p>Nessun dato ricevuto.</p>';
      }
    ?>
  </section>
</body>
</html>
