<?php
include 'default/headerProfilo.php';     // Header personalizzato per la sezione
include 'default/footerHome.php';        // Footer del sito
require 'logic/controlloLogin.php';      // Verifica se l'utente è loggato
?>

<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Profilo Arbitro</title>
  <!-- Fogli di stile -->
  <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="css/profilo.css">
  <link rel="stylesheet" href="css/registration.css">
</head>
<body>
  <!-- Hero Section (titolo / benvenuto) -->
  <section class="hero">
    <h2>Benvenuto/a, <?php echo htmlspecialchars($name); ?>!</h2>
      <p>Scegli una gara per inserire le penalità</p>
  </section>

  <div class="table-section">
    <?php
    require 'logic/requestData.php';
    $res = request("richiestaClassAr", $socket);
    // Suddivido $res per righe
    $rows = explode("\n", $res);

    // Verifico se c'è almeno una riga non vuota
    if(count($rows) > 0 && !empty(trim($rows[0]))) {
        echo '<table>';
        echo '  <thead>';
        echo '    <tr>';
        echo '      <th>IdGara</th>';
        echo '      <th>Seleziona</th>';
        echo '    </tr>';
        echo '  </thead>';
        echo '  <tbody>';

        // Per ogni riga di $res
        foreach($rows as $row) {
            // Elimino spazi bianchi di contorno
            $row = trim($row);
            // Se la riga è vuota, salto
            if(empty($row)) continue;

            // Suddivido la riga (in questo caso potrebbe contenere solo l'idGara)
            $columns = preg_split('/\s+/', $row);
            
            // Estraggo idGara (se la riga ha almeno 1 colonna)
            if(isset($columns[0])) {
                $idGara = htmlspecialchars($columns[0]);
                
                echo '<tr>';
                // Colonna con l'idGara
                echo '  <td>' . $idGara . '</td>';
                // Colonna con bottone "Seleziona"
                echo '  <td>';
                echo '    <form action="aggiuntaPenalita.php" method="post">';
                echo '      <!-- Passo l\'idGara come input nascosto -->';
                echo '      <input type="hidden" name="idGara" value="' . $idGara . '">';
                echo '      <button type="submit">Seleziona</button>';
                echo '    </form>';
                echo '  </td>';
                echo '</tr>';
            }
        }

        echo '  </tbody>';
        echo '</table>';
    } else {
        echo '<p>Nessun dato ricevuto.</p>';
    }
    ?>
  </div>
</body>
</html>
