<?php
// Parametri richiesti:
// $dati, $colonne, $colonnaAzione, $actionForm, $labelBottone, $condizioneDisabilitaBottone, $chiavePrimaria
// Opzionale: $nomeCampoHidden (default: 'targa')
if(!isset($nomeCampoHidden)) $nomeCampoHidden = 'targa';

if(count($dati) > 0) {
    echo '<table class="styled-table"><thead><tr>';
    foreach($colonne as $col) {
        echo "<th>$col</th>";
    }
    echo "<th>$colonnaAzione</th>";
    echo '</tr></thead><tbody>';

    foreach($dati as $riga) {
        echo '<tr>';
        foreach($colonne as $col) {
            echo '<td>' . htmlspecialchars($riga[$col]) . '</td>';
        }
        echo '<td>';
        echo "<form action='$actionForm' method='post' style='margin:0'>";
        // Ora è davvero generico:
        echo "<input type='hidden' name='" . htmlspecialchars($nomeCampoHidden) . "' value='" . htmlspecialchars($riga[$chiavePrimaria]) . "'>";

        $disabled = '';
        if(isset($condizioneDisabilitaBottone) && call_user_func($condizioneDisabilitaBottone, $riga)) {
            $disabled = 'disabled';
        }
        echo "<button type='submit' class='btn-green' $disabled>$labelBottone</button>";
        echo '</form>';
        echo '</td>';
        echo '</tr>';
    }

    echo '</tbody></table>';
} else {
    echo '<p>Nessun dato ricevuto.</p>';
}
?>
<script>
  window.addEventListener('pageshow', function(event) {
    if (event.persisted || (window.performance && window.performance.navigation.type === 2)) {
      window.location.reload();
    }
  });
</script>
