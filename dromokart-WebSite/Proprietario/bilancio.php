<?php
include '../default/headerProfilo.php';
require '../logic/requestData.php';

// Richiedi i dati del bilancio dal server
$res = request("mostraBilancio", $socket);
$rows = explode("\n", $res);

// Prepara i dati per la tabella generica
$colonne = ["Entrate", "Uscite", "Guadagni"];
$dati = [];

foreach ($rows as $row) {
    $row = trim($row);
    if (empty($row)) continue;

    $columns = preg_split('/\s+/', $row);
    if (count($columns) >= 3) {
        $dati[] = array_combine($colonne, array_slice($columns, 0, 3));
    }
}

// In questo caso non c'è alcuna azione sulla tabella (niente bottoni)
?>

<section class="hero">
    <h1>Benvenuti nella pagina del bilancio</h1>
</section>

<div class="table-section">
    <?php
    if(count($dati) > 0) {
        echo '<table class="styled-table"><thead><tr>';
        foreach($colonne as $col) {
            echo "<th>$col</th>";
        }
        echo '</tr></thead><tbody>';

        foreach($dati as $riga) {
            echo '<tr>';
            foreach($colonne as $col) {
                echo '<td>' . htmlspecialchars($riga[$col]) . '</td>';
            }
            echo '</tr>';
        }

        echo '</tbody></table>';
    } else {
        echo '<p>Nessun dato ricevuto.</p>';
    }
    ?>
</div>

<?php include '../default/footerConce.php'; ?>