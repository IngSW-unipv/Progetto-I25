<?php
    session_start();

    require 'connection.php';

    $socket = connectionOpen($address, $port);

    fwrite($socket, "richiestaDipen ");

    $i = 0;

    //la risposta è il codice fiscale di tutti i dipendenti tranne il proprietario
    while(($res = fgets($socket))  != "end"){
        $dip[$i] = $res;
        $i++;
    }
    fclose($socket);
?>