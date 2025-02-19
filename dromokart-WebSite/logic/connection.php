<?php
    $address = '127.0.0.1';
    $port = 40000;

    function connectionOpen(){
        $socket = fsockopen($address, $port);
        if($socket == NULL)
            header('Location: ../ConnectionError.html');
        return $socket;
    };
    //blocking?
?>