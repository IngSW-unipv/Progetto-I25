<?php

// Controlla se l'utente è loggato
if (!isset($_SESSION['username'])) {
    // Se non è loggato, reindirizza al login
    header("Location: index.php");
    exit;
}

$name = $_SESSION['name'];
?>
