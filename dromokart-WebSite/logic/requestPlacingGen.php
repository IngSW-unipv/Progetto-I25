<?php
    //logic frontend
    require 'connection.php';

    $socket = connectionOpen($address, $port);

    fwrite($socket, "richiestaClass\n");

    $res = '';
    while (!feof($socket)) {
        $line = fgets($socket);
        // Se la riga, una volta rimossi spazi e newline, è "end", esci dal ciclo
        if (trim($line) === "end") {
            break;
        }
        $res .= $line;
    }
    $res = trim($res);

    fclose($socket);
?>