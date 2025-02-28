<?php
    session_start();

    require 'connection.php';

$socket = connectionOpen($address, $port);

$targa = $_POST['targa'];
$cilindrata = $_POST['cilindrata'];


    //invio codice gara libera
    fwrite($socket, "aggiungiKart ");
    //invio dati
    fwrite($socket, $targa . " ");
    fwrite($socket, $cilindrata . "\n");

    //viene ricevuta una cifra che indica se la registrazione è andata a buon fine o meno

    $res = trim(fgets($socket));

fclose($socket);

    //nel caso di successo, viene impostato il rango a 0 e si salva 
    if($res === "0"){
        header('Location: ../registerError.php');
        die();
    } else{
        header('Location: ../meccanico.php');
        die();
    }
?>
