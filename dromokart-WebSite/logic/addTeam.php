<?php
session_start();
require 'connection.php';

// Apri connessione al socket
$socket = connectionOpen($address, $port);

// Recupera i dati inviati dal form
$nomeTeam = $_POST['nome_team'];
$coloreTeam = $_POST['colore_team'];

// Attenzione: $_POST['codice_fiscale'] è un array 
$codiciFiscali = isset($_POST['codice_fiscale']) ? $_POST['codice_fiscale'] : [];

// Controllo base: assicurati che ci siano al massimo 2 CF selezionati
if (count($codiciFiscali) > 2) {
    // Se per qualche ragione ne fossero arrivati 3, errore generico o reindirizzi
    header('Location: ../erroreGenerale.php');
    die();
}

// Prepariamo i due CF, gestendo il caso in cui l’utente ne selezioni solo 1
$cf1 = isset($codiciFiscali[0]) ? $codiciFiscali[0] : "";
$cf2 = isset($codiciFiscali[1]) ? $codiciFiscali[1] : "";

// Esempio: supponiamo che la chiamata a Java si aspetti un comando “creaTeam”
// e poi i valori in un’unica riga, separati da spazi.  
// La sintassi dipende da come hai impostato il tuo server Java.
fwrite($socket, "creaTeam ");
fwrite($socket, $nomeTeam . " " . $coloreTeam . " " . $cf1 . " " . $cf2 . "\n");

// Leggiamo la risposta dal server Java
$res = trim(fgets($socket));
fclose($socket);

// In base a come risponde il server, decidi che fare
if($res === "0"){
    // Errore da parte del server
    header('Location: ../erroreGenerale.php');
    die();
} else {
    // Tutto ok
    header('Location: ../transazioneCorretta.php');
    die();
}
?>
