<?php
include 'default/headerProprietario.php';
require 'logic/controlloLogin.php';
require 'logic/requestData.php';

// Ricevi i dati dal server
$res = request("richiestaDipen", $socket);
$rows = explode("\n", $res);

// Prepara dati come array associativo
$colonne = ["Codice Fiscale", "Nome", "Cognome"];
$dati = [];
foreach($rows as $row) {
    $row = trim($row);
    if(empty($row)) continue;
    $columns = preg_split('/\s+/', $row);
    if(count($columns) >= 3) {
        $dati[] = array_combine($colonne, array_slice($columns, 0, 3));
    }
}

// Parametri per la tabella generica
$colonnaAzione = "Rimozione";
$actionForm = "logic/deleteDip.php";
$labelBottone = "Rimuovi dipendente";
$chiavePrimaria = "Codice Fiscale";
$nomeCampoHidden = "codice_fiscale";
$condizioneDisabilitaBottone = null;
?>

<section class="hero">
  <h1>Elimina un dipendente</h1>
</section>

<div class="table-section">
  <?php include 'richiestaDatiTable.php'; ?>
</div>

<?php include 'default/footerConce.php'; ?>
