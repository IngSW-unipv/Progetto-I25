<?php
// Header e controllo login PRIMA di qualsiasi output
include '../default/headerProfilo.php';
require '../logic/controlloLogin.php';
?>

<section class="hero">
  <h1>Creazione Team</h1>
</section>

<!-- Stili/miniscript locali (se ti servono qui) -->
<style>
  /* Stile per il bottone disabilitato/abilitato */
  #submitButton {
    background-color: grey;
    color: white;
    cursor: not-allowed;
    border: none;
    padding: 10px 20px;
    font-size: 16px;
  }
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
    checkboxes.forEach(cb => { if (cb.checked) countChecked++; });

    if (countChecked > 2) {
      checkbox.checked = false;
      alert("Puoi selezionare al massimo due codici Piloti!");
      countChecked--;
    }

    if (countChecked === 2) {
      submitButton.disabled = false;
      submitButton.classList.add("enabled");
    } else {
      submitButton.disabled = true;
      submitButton.classList.remove("enabled");
    }
  }

  window.addEventListener('pageshow', function (event) {
    if (event.persisted || (window.performance && window.performance.navigation.type === 2)) {
      window.location.reload();
    }
  });
</script>

<?php
// ATTENZIONE: percorso corretto (sei in /Organizzatore/)
require '../logic/requestData.php';

$res  = request("mostraSociCampionato", $socket);
$rows = explode("\n", $res);

if (count($rows) > 0 && !empty(trim($rows[0]))) {
  echo '<form action="../logic/aggiungiTeam.php" method="post">';
  echo '<div class="description-section">';
  echo '<section class="table-section">';
  echo '<table class="styled-table">'; // classe giusta per lo stile tabella
  echo   '<thead>
            <tr>
              <th>Codice Fiscale</th>
              <th>Nome</th>
              <th>Cognome</th>
              <th>Seleziona</th>
            </tr>
          </thead>
          <tbody>';

  $j = 0;
  foreach ($rows as $row) {
    $row = trim($row);
    if (empty($row)) continue;
    $columns = preg_split('/\s+/', $row);

    if (count($columns) >= 3) {
      echo '<tr>';
      echo   '<td>' . htmlspecialchars($columns[0]) . '</td>';
      echo   '<td>' . htmlspecialchars($columns[1]) . '</td>';
      echo   '<td>' . htmlspecialchars($columns[2]) . '</td>';
      echo   '<td><input type="checkbox" name="codice_fiscale' . $j . '" value="' . htmlspecialchars($columns[0]) . '" onclick="handleCheckboxClick(this)"></td>';
      echo '</tr>';
      $j++;
    }
  }

  echo     '</tbody>';
  echo   '</table>';
  echo '</section>';

  echo '<div class="form-section">';
  echo   '<div class="form-group">
            <label for="nome_team">NOME TEAM</label><br>
            <input type="text" id="nome_team" name="Nome" required pattern="[A-Za-z0-9]+">
          </div>';

  echo   '<div class="form-group">
            <label for="colore_team">COLORE TEAM</label><br>
            <input type="color" id="colore_team" name="Colore" required>
            <input type="hidden" name="nsoci" value="' . $j . '">
          </div>';

  echo   '<div class="form-group" style="text-align:center;">
            <button type="submit" id="submitButton" disabled>Aggiungi</button>
          </div>';

  echo '</div>'; // .form-section
  echo '</div>'; // .description-section
  echo '</form>';
} else {
  echo '<p>Nessun dato ricevuto.</p>';
}
?>

<?php include '../default/footerConce.php'; ?>
