<?php
    session_start();

    require 'connection.php';

$socket = connectionOpen($address, $port);

$Nome = $_POST['Nome'];
$Colore = $_POST['Colore'];


    //invio codice gara libera
    fwrite($socket, "aggiungiTeam ");
    //invio dati
    fwrite($socket, $Nome . " ");
    fwrite($socket, $Colore . "\n");

    //viene ricevuta una cifra che indica se la registrazione è andata a buon fine o meno

    $res = trim(fgets($socket));

fclose($socket);

    if($res === "0"){
        header('Location: ../erroreGenerale.php');
        die();
    } else{
        header('Location: transazioneCorretta.php');
        die();
    }
?>
