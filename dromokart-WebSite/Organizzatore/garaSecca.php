<?php
include '../default/headerProfilo.php';  // Header personalizzato
require '../logic/controlloLogin.php';   // Controllo login
require '../logic/requestData.php';      // Funzione request()

$IdPrenotazione = isset($_POST['IdPrenotazione']) ? $_POST['IdPrenotazione'] : '';

// Recupera i dati dal server
$res = request("mostraSoci", $socket);
$rows = explode("\n", trim($res));

// Prepara i dati per la tabella generica
$colonne = ["CF", "Nome", "Cognome"];
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
$colonnaAzione = "Aggiungi Pilota";
$actionForm = "../logic/aggiungiSociGaraSecca.php";
$labelBottone = "Aggiungi Pilota";
$chiavePrimaria = "CF";
$nomeCampoHidden = "CF"; // Campo principale

// Campo hidden extra per passare anche l'IdPrenotazione
$hiddenExtra = ['IdPrenotazione' => $IdPrenotazione];

$condizioneDisabilitaBottone = null; // Nessuna condizione di disabilitazione

// Contenitore estetico per la tabella
echo "<h2>Dettagli della Prenotazione: " . htmlspecialchars($IdPrenotazione) . "</h2>";
echo '<div class="table-section">';
include '../richiestaDatiTable.php';
echo '</div>';

include '../default/footerHome.php';
