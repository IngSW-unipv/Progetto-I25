<?php
  include '../default/headerProfilo.php';
  include '../default/footerHome.php';
?>

<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Transazione avvenuta con successo</title>
  <!-- Importa il font Roboto -->
  <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap" rel="stylesheet">
  <!-- Collegamento al file CSS esterno -->
  <link rel="stylesheet" href="../css/styles.css">

  <!-- Script per il redirect dopo 5 secondi -->
  <script>
    setTimeout(function() {
      window.history.back(); // Torna alla pagina precedente
    }, 5000); // 5000 millisecondi = 5 secondi
  </script>
</head>
<body>

  <!-- Main Content -->
  <main>
    <!-- Sezione Hero -->
    <section class="hero">
      <h1>Congratulazioni! Hai effettuato correttamente l'acquisto.</h1>
      <p>Verrai reindirizzato automaticamente...</p>
    </section>
  </main>

</body>
</html>
