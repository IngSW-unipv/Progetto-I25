<?php
    //pagina di invio dei dati per le gare del campionato
    session_start();

    require 'connection.php';

    $socket = connectionOpen($address, $port);

    $Gara = $_POST['idGara'];
    $Campionato = $_POST['idCampionato'];
    

    //invio codice gara libera
    fwrite($socket, "aggiungiGareCampionato ");
    //invio dati
    fwrite($socket, $Gara . " ");
    fwrite($socket, $Campionato . "\n");

    //viene ricevuta una cifra che indica se la registrazione è andata a buon fine o meno

    $res = trim(fgets($socket));

    fclose($socket);

    //nel caso di successo, viene impostato il rango a 0 e si salva 
    if($res === "0"){
        header('Location: ../erroreGenerale.php');
        die();
    } else{
        header('Location: transazioneCorretta.php');
        die();
    }
?>
