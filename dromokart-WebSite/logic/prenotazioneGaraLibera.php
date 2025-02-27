<?php
    session_start();

    require 'connection.php';

$socket = connectionOpen($address, $port);

$tempo = $_POST['timeslot'];
$date = $_POST['date'];


    //invio codice gara libera
    fwrite($socket, "prenotazioneLibera ");
    //invio dati
    fwrite($socket, $tempo . " ");
    fwrite($socket, $date . "\n");

    //viene ricevuta una cifra che indica se la registrazione è andata a buon fine o meno

    $res = trim(fgets($socket));

fclose($socket);

    //nel caso di successo, viene impostato il rango a 0 e si salva 
    if($res === "0"){
        header('Location: ../registerError.php');
        die();
    } else{
        $_SESSION['username'] = $username;
        $_SESSION['rank'] = 0;
        header('Location: profilo.php');
        die();
    }
?>
