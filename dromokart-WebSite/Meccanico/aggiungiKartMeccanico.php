<?php
include '../default/headerProfilo.php';      
require '../logic/controlloLogin.php';         
require '../logic/requestData.php';            

// Ricevi le righe dal server
$res = request("mostraKartAggiunta", $socket);
$rows = explode("\n", $res);

// Prepara dati come array associativi per la tabella generica
$colonne = ["Targa", "Cilindrata", "Serbatoio"];
$dati = [];
foreach($rows as $row) {
    $row = trim($row);
    if(empty($row)) continue;
    $columns = preg_split('/\s+/', $row);
    if(count($columns) >= 3) {
        $dati[] = array_combine($colonne, array_slice($columns, 0, count($colonne)));
    }
}

// Parametri della tabella generica
$colonnaAzione = "Aggiungi";
$actionForm = "../logic/aggiungiKartMeccanico.php"; 
$labelBottone = "Aggiungi Kart";
$chiavePrimaria = "Targa";
$nomeCampoHidden = "targa"; // opzionale, ma esplicito
$condizioneDisabilitaBottone = null;

// Contenitore estetico per la tabella
echo '<div class="table-section">';
include '../richiestaDatiTable.php'; 

include '../default/footerHome.php'; 
