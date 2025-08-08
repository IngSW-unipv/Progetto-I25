<?php
include '../default/headerProfilo.php'; // Apre HTML, HEAD con CSS
require '../logic/controlloLogin.php';
require '../logic/requestData.php';

// Recupero dati dal server
$res = request("richiestaCampionato", $socket);
$rows = explode("\n", $res);

// Preparo i dati per la tabella generica
$colonne = ["IdCampionato"];
$dati = [];
foreach ($rows as $row) {
    $row = trim($row);
    if (empty($row)) continue;
    $columns = preg_split('/\s+/', $row);
    if (isset($columns[0])) {
        $dati[] = array_combine($colonne, array_slice($columns, 0, count($colonne)));
    }
}

// Parametri per la tabella generica
$colonnaAzione = "Seleziona";
$actionForm = "gareAssociateCampionato.php";
$labelBottone = "Seleziona";
$chiavePrimaria = "IdCampionato";
$nomeCampoHidden = "idCampionato";
$condizioneDisabilitaBottone = null; // Nessuna condizione di disabilitazione

// Contenitore estetico + tabella generica
echo '<div class="table-section">';
include '../richiestaDatiTable.php';
echo '</div>';

include '../default/footerHome.php'; // Chiude HTML
?>
