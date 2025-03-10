<?php
    session_start();

    require 'connection.php';

    $socket = connectionOpen($address, $port);

    $Nome = $_POST['Nome'];
    //il formato di colore e`: #ffffff
    $Colore = $_POST['Colore'];
    $nsoci = $_POST['nsoci'];

    $usr = '';

    for($k =0; $k < $nsoci; $k++){
        $str = "codice_fiscale" .$k;
        if(isset($_POST[$str])){
            $usr .= " " .$_POST[$str];
        }
    }

    //echo 'nome: ' .$Nome .'  Colore:' .$Colore .'  membri:' .$usr; 

    //invio codice gara libera
    fwrite($socket, "aggiungiTeam ");
    //invio dati
    fwrite($socket, $Nome . " ");
    fwrite($socket, $Colore);
    fwrite($socket, $usr . "\n");

    //viene ricevuta una cifra che indica se la registrazione è andata a buon fine o meno

    $res = trim(fgets($socket));

    fclose($socket);

    if($res === "0"){
        header('Location: ../erroreGenerale.php');
        die();
    } else{
        header('Location: transazioneCorretta.php');
        die();
    }
?>
