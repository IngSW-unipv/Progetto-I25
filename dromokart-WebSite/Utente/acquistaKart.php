<?php
include '../default/headerProfilo.php';
require '../logic/controlloLogin.php';
require '../logic/requestData.php';

// Ricevi i dati dal server
$res = request("mostraKartAggiunta", $socket);
$rows = explode("\n", $res);

// Prepara dati come array associativi
$colonne = ["Targa", "Cilindrata", "Serbatoio"];
$dati = [];
foreach ($rows as $row) {
    $row = trim($row);
    if (empty($row)) continue;
    $columns = preg_split('/\s+/', $row);
    if (count($columns) >= 3) {
        $dati[] = array_combine($colonne, array_slice($columns, 0, 3));
    }
}

// Parametri per la tabella generica
$colonnaAzione = "Acquista";
$actionForm = "../logic/acquistaKart.php";
$labelBottone = "Acquista Kart";
$chiavePrimaria = "Targa"; // Campo usato come chiave primaria
$nomeCampoHidden = "targa";
$condizioneDisabilitaBottone = null; // Nessuna condizione di disabilitazione

// Contenitore estetico per la tabella
echo '<div class="table-section">';
include '../richiestaDatiTable.php';
echo '</div>';

include '../default/footerHome.php';
?>
