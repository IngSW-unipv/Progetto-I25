<?php
    session_start();

    require 'connection.php';

$socket = connectionOpen($address, $port);

$IdPrenotazione = $_POST['IdPrenotazione'];
$CF = $_POST['CF'];

    //invio codice gara libera
    fwrite($socket, "inserimentoSociGara ");
    //invio dati
    fwrite($socket, $IdPrenotazione . " ");
    fwrite($socket, $CF . "\n");


    //viene ricevuta una cifra che indica se la registrazione è andata a buon fine o meno

    $res = trim(fgets($socket));

fclose($socket);

    if($res === "0"){
        header('Location: ../erroreGenerale.php');
        die();
    } else{
        header('Location: ../Organizzatore/organizzatore.php');
        die();
    }
?>
