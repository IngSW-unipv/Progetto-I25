<?php

    require 'connection.php';

    $socket = connectionOpen($address, $port);

    $idCampionato = $_POST['idCampionato'];
    $idGara = $_POST['idGara'];


    //invio codice gara libera
    fwrite($socket, "aggiungiGareCampionato ");
    //invio dati
    fwrite($socket, $idCampionato . " ");
    fwrite($socket, $idGara . "\n");
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
