<?php
    session_start();

    require 'connection.php';

$socket = connectionOpen($address, $port);

$codice_fiscale = $_POST['codice_fiscale'];


    //invio codice gara libera
    fwrite($socket, "eliminaDipen ");
    //invio dati
    fwrite($socket, $codice_fiscale . " ");
    //viene ricevuta una cifra che indica se la registrazione è andata a buon fine o meno

    $res = trim(fgets($socket));

fclose($socket);

    //nel caso di successo, viene impostato il rango a 1 e si salva 
    if($res === "0"){
        header('Location: ../registerError.php');
        die();
    } else{
        header('Location: proprietario.php');
        die();
    }
?>
