<?php
session_start();
require 'connection.php';

$socket = connectionOpen($address, $port);

$username = $_POST['username'];
$password = $_POST['password'];

$password = hash('sha256', $password);

//invio codice login
fwrite($socket, "l ");

//invio dati
fwrite($socket, $username . " ");
fwrite($socket, $password . "\n");

// Leggi l'intera riga di risposta dal socket e
// rimuove eventuali spazi vuoti e newline
$res = trim(fgets($socket));

fclose($socket);

// Dividi la stringa in base allo spazio
$str = explode(' ', $res);

// Verifica che la risposta sia nel formato atteso (almeno due elementi)
if (count($str) < 2) {
    echo "Risposta non valida: " . htmlspecialchars($res);
    exit();
}

// Controlla il primo valore e redirigi di conseguenza
//la seconda cifra indica: 0 utente semplice; 1 meccanico; 2 gestore concessionaria; 3 arbitro; 4 organizzatore;
//5 proprietario 
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
