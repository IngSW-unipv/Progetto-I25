<?php
session_start();
require 'logic/connection.php';

$socket = connectionOpen($address, $port);
fwrite($socket, "mostraKart\n");

$res = '';
while (!feof($socket)) {
    $line = fgets($socket);
    // Se la riga, una volta rimossi spazi e newline, è "and", esci dal ciclo
    if (trim($line) === "end") {
        break;
    }
    $res .= $line;
}
$res = trim($res);

fclose($socket);
?>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Dati ricevuti da Java</title>
</head>
<body>
    <h1>Dati ricevuti da Java</h1>
    <div>
        <?php
        // Utilizza nl2br per visualizzare correttamente le newline in HTML
        echo nl2br(htmlspecialchars($res));
        ?>
    </div>
</body>
</html>
