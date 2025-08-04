<?php
include '../default/headerProfilo.php';
require '../logic/controlloLogin.php';
require '../logic/requestData.php';

// Ricevi le righe dal server
$res = request("mostraKartManutenzione", $socket);
$rows = explode("\n", $res);

// Prepara dati come array associativi per la tabella
$colonne = ["Targa", "Data dall'ultima manutenzione"];
$dati = [];
foreach($rows as $row) {
    $row = trim($row);
    if(empty($row)) continue;
    $columns = preg_split('/\s+/', $row);
    if(count($columns) >= 2) {
        // Per visualizzare gli underscore come spazi
        $columns[1] = str_replace('_', ' ', $columns[1]);
        $dati[] = array_combine($colonne, array_slice($columns, 0, count($colonne)));
    }
}

// Parametri della tabella generica aggiornata
$colonnaAzione = "Effettua manutenzione";
$actionForm = "descrizioneManutenzione.php";
$labelBottone = "Effettua manutenzione";
$chiavePrimaria = "Targa";        // <-- fondamentale!
$nomeCampoHidden = "targa";       // opzionale, default è "targa"

// Logica per disabilitare il bottone
$condizioneDisabilitaBottone = function($riga) { 
    return trim($riga["Data dall'ultima manutenzione"]) == "0"; 
};

// Contenitore estetico per la tabella
echo '<div class="table-section">';

// Chiama la tabella generica con tutti i parametri già impostati
include '../richiestaDatiTable.php';

echo '</div>';

include '../default/footerHome.php';
?>
