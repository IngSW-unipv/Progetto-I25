<?php
    require 'connection.php';

    $socket = connectionOpen($address, $port);

    fwrite($socket, "mostraPezzi\n");

    $i = 0;

    //i parametri sono: nome del pezzo, quantita`, prezzo
    while(($res = fgets($socket))  != "end"){
        $str = explode($res, ' ');
        $part[$i][0] = $str[0];
        $part[$i][1] = $str[1];
        $part[$i][2] = $str[2];
        $part[$i][3] = $str[3];
        $i++;
    }

    fclose($socket);
?>