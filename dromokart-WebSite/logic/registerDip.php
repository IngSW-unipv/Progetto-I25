<?php
    session_start();

    require 'connection.php';

$socket = connectionOpen($address, $port);

$name = $_POST['nome'];
$surname = $_POST['cognome'];
$date = $_POST['data_nascita'];
$username = htmlspecialchars($_POST['codice_fiscale']);
$email = $_POST['email'];
$password = $_POST['password'];
$rank = $_POST['rank'];
$oreL = $_POST['oreL'];
$stipendio = $_POST['stipendio'];

$password = hash('sha256', $password);

    //invio codice registrazione
    fwrite($socket, "r ");
    //invio dati
    fwrite($socket, $name . " ");
    fwrite($socket, $surname . " ");
    fwrite($socket, $date . " ");
    fwrite($socket, $username . " ");
    fwrite($socket, $email . " ");
    fwrite($socket, $password . " ");
    fwrite($socket, $rank . " ");
    fwrite($socket, $oreL . " ");
    fwrite($socket, $stipendio . "\n");

    //viene ricevuta una cifra che indica se la registrazione è andata a buon fine o meno

    $res = trim(fgets($socket));
    fclose($socket);

    //nel caso di successo, viene impostato il rango a 0 e si salva 
    if($res === "0"){
        header('Location: ../registerError.php');
        die();
    } else{
        header('Location: ../proprietario.php');
        die();
    }
?>
