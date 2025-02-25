<?php
session_start();
require 'connection.php';

$socket = connectionOpen($address, $port);

//invio codice login
fwrite($socket, "mostraKart ");

// Leggi l'intera riga di risposta dal socket e
// rimuove eventuali spazi vuoti e newline
$res = trim(fgets($socket));

fclose($socket);

?>
