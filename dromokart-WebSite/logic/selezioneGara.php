<?php
require_once 'connection.php';

// Leggo idGara dal POST (se la pagina viene richiamata con un form)
$idGara = isset($_POST['idGara']) ? $_POST['idGara'] : '';

// Apri il socket
$socket = connectionOpen($address, $port);
if (!$socket) {
    die("Errore di connessione al server Java");
}

// Invia il comando + l’ID
fwrite($socket, "mostraGara ");
fwrite($socket, $idGara . "\n");

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