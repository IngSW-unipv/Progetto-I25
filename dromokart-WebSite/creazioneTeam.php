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
  <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="css/styles.css">
  <link rel="stylesheet" href="css/registration.css">

  <style>
    /* Stile per il bottone disabilitato */
    #submitButton {
      background-color: grey;
      color: white;
      cursor: not-allowed;
      border: none;
      padding: 10px 20px;
      font-size: 16px;
    }

    /* Stile per il bottone abilitato */
    #submitButton.enabled {
      background-color: green;
      cursor: pointer;
    }
  </style>

  <script>
    function handleCheckboxClick(checkbox) {
      const checkboxes = document.querySelectorAll('input[type="checkbox"]');
      const submitButton = document.getElementById('submitButton');

      let countChecked = 0;
      checkboxes.forEach(cb => {
        if (cb.checked) {
          countChecked++;
        }
      });

      if (countChecked > 2) {
        checkbox.checked = false;
        alert("Puoi selezionare al massimo due codici Piloti!");
      }

      // Gestione del bottone
      if (countChecked === 2) {
        submitButton.disabled = false;
        submitButton.classList.add("enabled");
      } else {
        submitButton.disabled = true;
        submitButton.classList.remove("enabled");
      }
    }

    window.onload = function () {
      document.getElementById('submitButton').disabled = true;
    };
  </script>
</head>
<body>
  <hero>
    <h1>Creazione Team</h1>
  </hero>

  <?php
    require 'logic/requestData.php';

    $res = request("mostraSociCampionato", $socket);
    $rows = explode("\n", $res);

    if(count($rows) > 0 && !empty(trim($rows[0]))) {        
      echo '<form action="logic/aggiungiTeam.php" method="post" onsubmit="checkButton(event)">';
      echo '<div class="description-section">';
      echo '<section class="table-section">';
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

      $j = 0;
      foreach($rows as $row) {
        $row = trim($row);
        if(empty($row)) continue;
        $columns = preg_split('/\s+/', $row);
        
        if(count($columns) >= 3) {
          echo '<tr>';
          echo '<td>' . htmlspecialchars($columns[0]) . '</td>';
          echo '<td>' . htmlspecialchars($columns[1]) . '</td>';
          echo '<td>' . htmlspecialchars($columns[2]) . '</td>';
          echo '<td><input type="checkbox" name="codice_fiscale' .$j .'" value="' . htmlspecialchars($columns[0]) . '" onclick="handleCheckboxClick(this)" /></td>';
          echo '</tr>';
          $j++;
        }
      }
      echo '</tbody>';
      echo '</table>';
      echo '</section>';

      echo '<div class="form-section">';
      echo '<div class="form-group">';
      echo '<label for="nome_team">NOME TEAM</label><br>';
      echo '<input type="text" id="nome_team" name="Nome" required pattern="[A-Za-z0-9]+" /><br><br>';
      echo '</div>';
    
      echo '<div class="form-group">';
      echo '<label for="colore_team">COLORE TEAM</label><br>';
      echo '<input type="color" id="colore_team" name="Colore" required /><br><br>';
      echo '<input type="hidden" name="nsoci" value="' .count($rows) .'"/><br>';
      
      echo '<button type="submit" id="submitButton" disabled>Aggiungi</button>';
      echo '</div>';
      echo '</div>';
      echo '</div>';

      echo '</form>';
    } else {
      echo '<p>Nessun dato ricevuto.</p>';
    }
  ?>
</body>
</html>
<script>
  window.addEventListener('pageshow', function(event) {
    if (event.persisted || (window.performance && window.performance.navigation.type === 2)) {
      window.location.reload();
    }
  });
</script>
