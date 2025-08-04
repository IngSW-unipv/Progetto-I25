<?php
include 'default/headerProfilo.php';
require 'logic/controlloLogin.php';
require 'logic/requestData.php';

// Ricevi dati dal server
$res = request("richiestaClassAr", $socket);
$rows = explode("\n", $res);

// Prepara dati come array associativi per la tabella generica
$colonne = ["IdGara"];
$dati = [];
foreach ($rows as $row) {
    $row = trim($row);
    if (empty($row)) continue;

    $columns = preg_split('/\s+/', $row);
    if (isset($columns[0])) {
        $dati[] = ["IdGara" => $columns[0]];
    }
}

// Parametri per la tabella generica
$colonnaAzione = "Seleziona";
$actionForm = "aggiuntaPenalita.php";
$labelBottone = "Seleziona";
$chiavePrimaria = "IdGara";
$nomeCampoHidden = "idGara";
$condizioneDisabilitaBottone = null;

// Hero Section (titolo / benvenuto)
?>
<section class="hero">
  <h2>Benvenuto/a, <?php echo htmlspecialchars($name); ?>!</h2>
  <p>Scegli una gara per inserire le penalità</p>
</section>

<div class="table-section">
  <?php include 'richiestaDatiTable.php'; ?>
</div>

<?php include 'default/footerHome.php'; ?>