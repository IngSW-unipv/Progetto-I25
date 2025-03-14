<?php
    session_start();

    require 'connection.php';

$socket = connectionOpen($address, $port);

$CF = $_POST['CF'];
$IdPrenotazione = $_POST['IdPrenotazione'];


    //invio codice gara libera
    fwrite($socket, "inserimentoSociGara ");
    //invio dati
    fwrite($socket, $CF . " ");
    fwrite($socket, $IdPrenotazione . "\n");

    //viene ricevuta una cifra che indica se la registrazione è andata a buon fine o meno

    $res = trim(fgets($socket));

fclose($socket);

    if($res === "0"){
        header('Location: ../erroreGenerale.php');
        die();
    } else{
        header('Location: ../organizzatore.php');
        die();
    }
?>
