<?php
    session_start();

    require 'connection.php';

$socket = connectionOpen($address, $port);

$columns[0] = $_POST['pezzo'];
$username = $_SESSION['username'];


    //invio codice gara libera
    fwrite($socket, "acquistaPezzi ");
    //invio dati
    fwrite($socket, $username . " ");
    fwrite($socket, $columns[0]. "\n");
    //viene ricevuta una cifra che indica se la registrazione è andata a buon fine o meno

    $res = trim(fgets($socket));

fclose($socket);

    //nel caso di successo, viene impostato il rango a 1 e si salva 
    if($res === "0"){
        header('Location: ../registerError.php');
        die();
    } else{
        header('Location: ../profilo.php');
        die();
    }
?>
