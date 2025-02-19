<?php
    session_start();

    require 'connection.php';
    $username = htmlspecialchars($_POST['username']);
    $password = $_POST['password'];

    $socket = connectionOpen();

    fwrite($socket, $username);
    fwrite($socket, $password);

    //vengono ricevute due cifre intere separate da uno spazio: la prima è una cifra per un confronto booleano,
    //la seconda serve ad indicare il grado dell'utente e viene salvata nelle variabili di sessione
    while(!feof($socket)){
        $res = fgets($socket, 3);
    }

    fclose($socket);

    $str = explode(' ',$res);

    if(!($str[0])){
        header('../index.php');
        die();
    } else{
        $_SESSION['user'] = $username;
        $_SESSION['rank'] = $str[1];
        header('../profilo.php');
        die();
    }
?>