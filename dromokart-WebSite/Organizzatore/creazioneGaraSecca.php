<?php
include '../default/headerProfilo.php';     // Header personalizzato per la sezione
require '../logic/controlloLogin.php';      // Verifica se l'utente è loggato
require '../logic/requestData.php';         // Funzione request()

// Recupera i dati dal server
$res = request("richiestaGaraSecca", $socket);
$rows = explode("\n", $res);

// Prepara dati e colonne per la tabella generica
$colonne = ["Id Prenotazione"];
$dati = [];
foreach ($rows as $row) {
    $row = trim($row);
    if (empty($row)) continue;
    $columns = preg_split('/\s+/', $row);
    if (isset($columns[0])) {
        $dati[] = array_combine($colonne, [$columns[0]]);
    }
}

// Parametri tabella generica
$colonnaAzione = "Seleziona";
$actionForm = "garaSecca.php";
$labelBottone = "Seleziona";
$chiavePrimaria = "Id Prenotazione";
$nomeCampoHidden = "IdPrenotazione";
$condizioneDisabilitaBottone = null; // Nessuna condizione di disabilitazione

// Contenitore estetico
echo '<div class="table-section">';
include '../richiestaDatiTable.php';
echo '</div>';

include '../default/footerHome.php';
?>
