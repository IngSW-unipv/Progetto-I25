<?php
    require 'connection.php';

    $socket = connectionOpen($address, $port);

    fwrite($socket, "mostraPezzi\n");

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

    return $res;

    fclose($socket);
?>