<?php
session_start();
require 'connection.php';

$socket = connectionOpen($address, $port);

$username = 'username';
$password = 'password';

fwrite($socket, "login\n");
fwrite($socket, $username . "\n");
fwrite($socket, $password . "\n");

// Leggi l'intera riga di risposta dal socket
$res = fgets($socket);
fclose($socket);

// Rimuove eventuali spazi vuoti e newline
$res = trim($res);

// Dividi la stringa in base allo spazio
$str = explode(' ', $res);

// Verifica che la risposta sia nel formato atteso (almeno due elementi)
if (count($str) < 2) {
    echo "Risposta non valida: " . htmlspecialchars($res);
    exit();
}

// Controlla il primo valore e redirigi di conseguenza
if ($str[0] === "0") {
    header("Location: ../index.php");
    exit();
} else {
    $_SESSION['username'] = $username;
    $_SESSION['rank'] = $str[1];
    header("Location: ../profilo.php");
    exit();
}
?>
