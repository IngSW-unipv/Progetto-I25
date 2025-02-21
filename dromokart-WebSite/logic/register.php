<?php
    session_start();

    require 'connection.php';
    //$username = htmlspecialchars($_POST['username']);
    //$password = $_POST['password'];

    $socket = connectionOpen($address, $port);

    $name = 'nome';
    $surname = 'cognome';
    $date = date("03.10.2001");
    $username = 'username';
    $email = 'email@rc.it';
    $password = 'password';

    $password = hash('sha256', $password);

    fwrite($socket, "r ");

    fwrite($socket, $name + " ");
    fwrite($socket, $surname + " ");
    fwrite($socket, $date + " ");
    fwrite($socket, $username + " ");
    fwrite($socket, $email + " ");
    fwrite($socket, $password + "\n");

    //vengono ricevute due cifre intere separate da uno spazio: la prima è una cifra per un confronto booleano,
    //la seconda serve ad indicare il grado dell'utente e viene salvata nelle variabili di sessione
    while(!feof($socket)){
        $res = fgets($socket, 1);
    }

    //la seconda cifra indica: 0 utente semplice; 1 meccanico; 2 gestore concessionaria; 3 arbitro; 4 organizzatore;
    //5 proprietario 
    if($res === "0"){
        header('../registerError.php');
        die();
    } else{
        $_SESSION['username'] = $username;
        $_SESSION['rank'] = 0;
        header('../profilo.php');
        die();
    }
?>