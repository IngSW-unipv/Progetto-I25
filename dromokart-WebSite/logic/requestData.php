<?php
    //logic frontend
    require 'connection.php';

    $socket = connectionOpen($address, $port);
    function request($msg, $socket){
        fwrite($socket, $msg ."\n");

        $res = '';
        while (!feof($socket)) {
            $line = fgets($socket);
            // Se la riga, una volta rimossi spazi e newline, è "end", esci dal ciclo
            if (trim($line) === "end") {
                break;
            }
            $res .= $line;
        }
        fclose($socket);

        $res = trim($res);

        return $res;
    }
?>