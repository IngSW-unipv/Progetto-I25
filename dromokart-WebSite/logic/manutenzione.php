<?php
    session_start();

    require 'connection.php';

    $socket = connectionOpen($address, $port);

    $targa = $_POST['targa'];
    $Descrizione = $_POST['Descrizione'];
    $prezzo = $_POST['prezzo'];

    //invio targa kart
    fwrite($socket, "manutenzione ");
    //invio dati
    fwrite($socket, $targa . " ");
    fwrite($socket, $prezzo . " ");
    fwrite($socket, $Descrizione . "\n");

    //viene ricevuta una cifra che indica se la registrazione è andata a buon fine o meno

    $res = trim(fgets($socket));

    fclose($socket);

    if($res === "0"){
        header('Location: ../erroreGenerale.php');
        die();
    } else{
        header('Location: ../Manutenzione.php');
        die();
    }
?>