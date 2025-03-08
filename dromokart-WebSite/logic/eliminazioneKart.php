<?php
    session_start();

    require 'connection.php';

    $socket = connectionOpen($address, $port);

    $targa = $_POST['targa'];


    //invio codice gara libera
    fwrite($socket, "eliminaKart ");
    //invio dati
    fwrite($socket, $targa . "\n");
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
