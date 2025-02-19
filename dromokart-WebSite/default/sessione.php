<?php
session_start();

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $username = $_POST['username'];
    $password = $_POST['password'];

    // Esegui qui la verifica delle credenziali, ad esempio controllando su un database.
    // Questo è un esempio semplice con credenziali hardcoded:
    if ($username === 'admin' && $password === 'admin') {
        // Credenziali corrette: salva l'username nella sessione
        $_SESSION['username'] = $username;
        // Reindirizza alla pagina protetta
        header("Location: profilo.php");
        exit;
    } else {
        echo "Credenziali non valide";
    }
}
?>