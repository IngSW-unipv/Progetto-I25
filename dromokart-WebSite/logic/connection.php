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
?>
