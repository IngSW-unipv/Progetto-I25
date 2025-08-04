<?php
include 'default/headerProfilo.php';
require 'logic/controlloLogin.php';

// Ricevo l'idCampionato dal form
$idCampionato = isset($_POST['idCampionato']) ? $_POST['idCampionato'] : '';

// Recupero le gare del campionato dal server
require_once 'logic/selezioneGareCampionato.php'; // Deve valorizzare $res

$rows = explode("\n", trim($res));

// Preparo i dati per la tabella generica
$colonne = ["idGara", "ora"];
$dati = [];
foreach ($rows as $row) {
    $row = trim($row);
    if (empty($row)) continue;
    $columns = preg_split('/\s+/', $row);
    if (count($columns) >= 2) {
        $dati[] = array_combine($colonne, array_slice($columns, 0, count($colonne)));
    }
}

// Parametri per la tabella generica
$colonnaAzione = "Aggiungi gara";
$actionForm = "logic/aggiungiGaraCampionato.php";
$labelBottone = "Aggiungi gara";
$chiavePrimaria = "idGara";
$nomeCampoHidden = "idGara";

// Aggiungo idCampionato come hidden extra in ogni form
$condizioneDisabilitaBottone = null;

// Output
echo "<section class='hero'><h2>Dettagli del Campionato: " . htmlspecialchars($idCampionato) . "</h2></section>";

echo '<div class="table-section">';
foreach ($dati as &$riga) {
    $riga['__extra_hidden'] = [
        'idCampionato' => $idCampionato
    ];
}
include 'richiestaDatiTable.php';
echo '</div>';

include 'default/footerHome.php';
?>
