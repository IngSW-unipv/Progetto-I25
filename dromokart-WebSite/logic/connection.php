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

// Apri la connessione
$socket = connectionOpen($address, $port);

// Leggi il dato inviato dalla connessione
$data = fgets($socket);

// Chiudi la connessione
fclose($socket);

// Rimuovi eventuali spazi bianchi o newline
$data = trim($data);

// Controlla il valore ricevuto e reindirizza di conseguenza
if ($data === "0") {
    header("Location: ../index.php");
    exit();
} elseif ($data === "1") {
    header("Location: ../profilo.php");
    exit();
} else {
    echo "Valore non previsto: " . htmlspecialchars($data);
}
?>






