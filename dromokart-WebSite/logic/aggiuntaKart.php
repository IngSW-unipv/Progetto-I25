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

    //nel caso di successo, viene impostato il rango a 0 e si salva 
    if($res === "0"){
        header('Location: ../registerError.php');
        die();
    } else{
        header('Location: ../gestoreConcessionaria.php');
        die();
    }
?>
