<?php
    session_start();

    require 'connection.php';

$socket = connectionOpen($address, $port);

$date = $_POST['date'];
$tempo = $_POST['timeslot'];
$username = $_SESSION['username'];

    //invio codice gara libera
    fwrite($socket, "prenotazioneLibera ");
    //invio dati
    fwrite($socket, $date . " ");
    fwrite($socket, $tempo . " ");
    fwrite($socket, $username . "\n");

    //viene ricevuta una cifra che indica se la registrazione è andata a buon fine o meno

    $res = trim(fgets($socket));

fclose($socket);

    //nel caso di successo, viene impostato il rango a 0 e si salva 
    if($res === "0"){
        header('Location: ../erroreGenerale.php');
        die();
    } else{
        header('Location: profilo.php');
        die();
    }
?>
