<?php

    require 'connection.php';

    $socket = connectionOpen($address, $port);

    $socio = $_POST['Socio'];
    $idGara = $_POST['idGara'];
    $time = $_POST['time'];


    //invio codice gara libera
    fwrite($socket, "aggiungiPenalita ");
    //invio dati
    fwrite($socket, $socio . " ");
    fwrite($socket, $idGara . " ");
    fwrite($socket, $time . "\n");
    //viene ricevuta una cifra che indica se la registrazione è andata a buon fine o meno

    $res = trim(fgets($socket));

    fclose($socket);

    if($res === "0"){
        header('Location: ../erroreGenerale.php');
        die();
    } else{
        header('Location: ../arbitro.php');
        die();
    }
?>
