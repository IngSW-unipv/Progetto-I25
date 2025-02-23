<?php
session_start();
require 'connection.php';

$socket = connectionOpen($address, $port);

$name = $_POST['nome'];
$surname = $_POST['cognome'];
$date = $_POST['data_nascita'];
$username = htmlspecialchars($_POST['codice_fiscale']);
$email = $_POST['email'];
$password = $_POST['password'];

$password = hash('sha256', $password);

fwrite($socket, "r ");
fwrite($socket, $name . " ");
fwrite($socket, $surname . " ");
fwrite($socket, $date . " ");
fwrite($socket, $username . " ");
fwrite($socket, $email . " ");
fwrite($socket, $password . "\n");

$res = trim(fgets($socket));

fclose($socket);

// Rimuovi questo echo per evitare output prima del redirect
// echo $res;

if($res === '0'){
    header('Location: ../registerError.php');
    exit;
} else{
    $_SESSION['username'] = $username;
    $_SESSION['rank'] = 0;
    header('Location: ../profilo.php');
    exit;
}
?>
