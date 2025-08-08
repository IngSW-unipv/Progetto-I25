<?php
include '../default/headerProfilo.php';
require '../logic/controlloLogin.php';
require '../logic/requestData.php';

// Ricevi i dati dal server
$res = request("mostraPezzi", $socket);
$rows = explode("\n", $res);

// Prepara dati come array associativi
$colonne = ["Prodotto", "Prezzo", "Quantità"];
$dati = [];
foreach ($rows as $row) {
    $row = trim($row);
    if (empty($row)) continue;
    $columns = preg_split('/\s+/', $row);
    if (count($columns) >= 4) {
        $dati[] = [
            "Prodotto" => str_replace('_', ' ', $columns[1]),
            "Prezzo"   => $columns[3],
            "Quantità" => $columns[2],
            "ID"       => $columns[0] // ID usato come chiave primaria
        ];
    }
}

// Parametri per la tabella generica
$colonnaAzione = "Acquista";
$actionForm = "../logic/acquirePart.php";
$labelBottone = "Acquista Pezzo";
$chiavePrimaria = "ID"; // Campo usato come chiave primaria
$nomeCampoHidden = "pezzo";
$condizioneDisabilitaBottone = function($riga) {
    return $riga["Quantità"] == "0"; // Disabilita se esaurito
};

// Contenitore estetico per la tabella
echo '<div class="table-section">';
include '../richiestaDatiTable.php';
echo '</div>';

include '../default/footerHome.php';
?>
