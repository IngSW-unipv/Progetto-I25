<?php
    session_start();

    require 'connection.php';

$socket = connectionOpen($address, $port);

$nome = $_POST['nome'];
$idGara = $_POST['idGara'];


    //invio codice gara libera
    fwrite($socket, "inserimentoTeamGara ");
    //invio dati
    fwrite($socket, $nome . " ");
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
