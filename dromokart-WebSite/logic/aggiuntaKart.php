<?php
    session_start();

    require 'connection.php';

$socket = connectionOpen($address, $port);

$targa = $_POST['targa'];
$cilindrata = $_POST['cilindrata'];
$prezzo = $_POST['prezzo'];


    //invio codice gara libera
    fwrite($socket, "aggiungiKartConcessionario ");
    //invio dati
    fwrite($socket, $targa . " ");
    fwrite($socket, $cilindrata . " ");
    fwrite($socket, $prezzo . "\n");

    //viene ricevuta una cifra che indica se la registrazione è andata a buon fine o meno

    $res = trim(fgets($socket));

fclose($socket);

    if($res === "0"){
        header('Location: ../erroreGenerale.php');
        die();
    } else{
        header('Location: acquistoAvvenutoCorrettamente.php');
        die();
    }
?>
