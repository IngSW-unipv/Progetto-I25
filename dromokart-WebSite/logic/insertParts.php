<?php
    require 'connection.php';
    $socket = connectionOpen($address, $port);

    $part = $_POST['part'];
    $q = $_POST['quantity'];

    if($q > 0){
        //invio codice gara libera
        fwrite($socket, "aggiungiPezzi ");
        //invio dati
        fwrite($socket, $part .' ' .$q ."\n");
        //viene ricevuta una cifra che indica se la registrazione è andata a buon fine o meno

        $res = trim(fgets($socket));

        fclose($socket);

        //nel caso di successo, viene impostato il rango a 1 e si salva 
        if($res === "0"){
            header('Location: ../erroreGenerale.php');
            die();
        } else{
            header('Location: ../aggiungiPezzi.php');
            die();
        }
    }
    else{
        header('Location: ../aggiungiPezzi.php');
        die();
    }
?>