<?php
$address = '127.0.0.1';
$port = 50000;

function connectionOpen($address, $port) {
    // Specifica il protocollo tcp://
    $socket = fsockopen("tcp://$address", $port, $errno, $errstr, 30);
    if (!$socket) {
        // Se la connessione fallisce, reindirizza ad una pagina di errore
        header('Location: ../ConnectionError.html');
        exit();
    }
    return $socket;
}

$socket = connectionOpen($address, $port);

// Per test, puoi inviare qualche dato
fwrite($socket, "Ciao dal PHP!\n");

// Leggi una risposta dal server se prevista
$response = fgets($socket);
echo "Risposta dal server: " . $response;

// Chiudi il socket
fclose($socket);
?>
