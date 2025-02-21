<?php
    session_start();

    require 'connection.php';
    //$username = htmlspecialchars($_POST['username']);
    //$password = $_POST['password'];

    $socket = connectionOpen($address, $port);

    $username = 'username';
    $password = 'password';

    fwrite($socket, "login \n");

    fwrite($socket, $username . "\n");
    fwrite($socket, $password . "\n");

    //vengono ricevute due cifre intere separate da uno spazio: la prima è una cifra per un confronto booleano,
    //la seconda serve ad indicare il grado dell'utente e viene salvata nelle variabili di sessione
    while(!feof($socket)){
        $logged = fgets($socket, 1);
        $rnk = fgets($socket, 1);
    }

    fclose($socket);

    //la seconda cifra indica: 0 utente semplice; 1 meccanico; 2 gestore concessionaria; 3 arbitro; 4 organizzatore;
    //5 proprietario 
    if($logged == 0){
        header('../index.php');
        die();
    } else{
        $_SESSION['user'] = $username;
        $_SESSION['rank'] = $rnk;
        header('../profilo.php');
        die();
    }
?>