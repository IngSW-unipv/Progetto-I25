<?php
include 'default/headerProfilo.php';
require 'logic/controlloLogin.php';
require_once 'logic/selezioneGara.php'; // recupera $res dal server usando $idGara

// Ricevi l'idGara passato dal form
$idGara = isset($_POST['idGara']) ? $_POST['idGara'] : '';

// Analizzo la risposta ricevuta ($res)
$rows = explode("\n", trim($res));

// Prepara i dati come array associativo per la tabella generica
$colonne = ["idGara", "Socio", "Targa", "bGiro", "tempoTot"];
$dati = [];

foreach ($rows as $row) {
    $row = trim($row);
    if (empty($row)) continue;
    $columns = preg_split('/\s+/', $row);

    if (count($columns) >= 5) {
        $dati[] = array_combine($colonne, array_slice($columns, 0, 5));
    }
}

// Parametri della tabella generica
$colonnaAzione = "Penalità";
$actionForm = "logic/aggiungiPenalita.php";
$labelBottone = "Aggiungi penalità";
$chiavePrimaria = "idGara";
$nomeCampoHidden = "idGara"; // opzionale ma esplicito

// Nessuna condizione per disabilitare il bottone
$condizioneDisabilitaBottone = null;

// Titolo della pagina
echo "<h2>Dettagli della Gara: " . htmlspecialchars($idGara) . "</h2>";
?>

<div class="table-section">
<?php
// In questo caso la tabella richiede un campo aggiuntivo (input di tempo)
// Implementiamo una versione specifica del ciclo per includere l'input extra:

if (count($dati) > 0) {
    echo '<table class="styled-table"><thead><tr>';
    foreach ($colonne as $col) {
        echo "<th>$col</th>";
    }
    echo "<th>Penalità</th><th>$labelBottone</th>";
    echo '</tr></thead><tbody>';

    foreach ($dati as $riga) {
        echo '<tr>';
        foreach ($colonne as $col) {
            echo '<td>' . htmlspecialchars($riga[$col]) . '</td>';
        }

        echo '<td>'; 
        echo '<form action="'.htmlspecialchars($actionForm).'" method="post" style="margin:0;">';
        echo '<input type="time" name="time" value="00:00:00" step="1" required>';
        
        // Hidden inputs
        echo '<input type="hidden" name="'.htmlspecialchars($nomeCampoHidden).'" value="'.htmlspecialchars($riga[$chiavePrimaria]).'">';
        echo '<input type="hidden" name="Socio" value="'.htmlspecialchars($riga["Socio"]).'">';
        echo '</td>';
        
        echo '<td>';
        echo '<button type="submit" class="btn-green">'.$labelBottone.'</button>';
        echo '</form>';
        echo '</td>';
        
        echo '</tr>';
    }

    echo '</tbody></table>';
} else {
    echo '<p>Nessun dato ricevuto per questa gara.</p>';
}
?>
</div>

<?php include 'default/footerHome.php'; ?>