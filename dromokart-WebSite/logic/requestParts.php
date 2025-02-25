<?php
    session_start();

    require 'connection.php';

    $socket = connectionOpen($address, $port);

    fwrite($socket, "rp ");

    $i = 0;

    while(($res = fgets($socket))  != "end"){
        $str = explode($res, ' ');
        $part[i][0] = str[0];
        $part[i][1] = str[1];
    }
?>