<?php
require_once 'connection.php';

// Leggo idGara dal POST (se la pagina viene richiamata con un form)
$idCampionato = isset($_POST['idCampionato']) ? $_POST['idCampionato'] : '';

// Apri il socket
$socket = connectionOpen($address, $port);
if (!$socket) {
    die("Errore di connessione al server Java");
}

// Invia il comando + l’ID
fwrite($socket, "selezioneGareCampionato ");
fwrite($socket, $idCampionato . "\n");

$res = '';
while (!feof($socket)) {
    $line = fgets($socket);
    // Se la riga, una volta rimossi spazi e newline, è "end", esci dal ciclo
    if (trim($line) === "end") {
        break;
    }
    $res .= $line;
}
$res = trim($res);

fclose($socket);
?>