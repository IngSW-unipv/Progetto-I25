<?php
    session_start();

    require 'connection.php';

    $socket = connectionOpen($address, $port);

    $date = $_POST['date'];
    $tempo = $_POST['timeslot'];
    $cf = $_SESSION['username'];

    //invio codice gara Secca
    fwrite($socket, "prenotazioneSecca ");
    //invio dati
    fwrite($socket, $date . " ");
    fwrite($socket, $tempo . "\n");

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
