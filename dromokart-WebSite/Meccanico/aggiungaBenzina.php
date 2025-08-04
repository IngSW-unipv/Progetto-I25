<?php
include '../default/headerProfilo.php';      
require '../logic/controlloLogin.php';         
require '../logic/requestData.php'; 

// Ricevi le righe dal server
$res = request("mostraKartRimuovi", $socket);
$rows = explode("\n", $res);

// Prepara dati come array associativi per la tabella
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
$actionForm = "../logic/aggiungiBenzina.php";
$labelBottone = "Riempi Serbatoio";
$chiavePrimaria = "Targa"; // Parametro fondamentale per la tabella generica
$nomeCampoHidden = "targa"; // opzionale, ma esplicito

// Disabilita il bottone se il serbatoio è pieno
$condizioneDisabilitaBottone = function($riga) { 
    return $riga["Serbatoio"] == 20; 
};

// Contenitore estetico per la tabella
echo '<div class="table-section">';
include '../richiestaDatiTable.php';
echo '</div>';

include '../default/footerHome.php';
?>
