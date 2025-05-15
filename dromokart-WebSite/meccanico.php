<?php include 'default/footerHome.php'; ?>
<?php include 'default/headerProfilo.php'; ?>
<?php require 'logic/controlloLogin.php'; ?>

<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Profilo Privato - Meccanico</title>
  <!-- Importa il font Roboto -->
  <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap" rel="stylesheet">
  <!-- Collegamento al file CSS esterno -->
  <link rel="stylesheet" href="css/profilo.css">
</head>
<body>
 
  <!-- Hero Section -->
  <section class="hero">
    <h2>Benvenuto/a, <?php echo htmlspecialchars($name); ?>!</h2>
    <p>Modifica la disponibilità di vetture al noleggio e effettuane la manutenzione</p>
  </section>

  <!-- Main Content: Area Bottoni -->
  <main>
    <div class="profile-buttons">
      <button onclick="location.href='aggiungiKartMeccanico.php'">Aggiungi kart al noleggio</button>
      <button onclick="location.href='eliminaKartMeccanico.php'">Rimuovi kart dal noleggio</button>
      <button onclick="location.href='aggiungaBenzina.php'">Fai il pieno</button>
      <button onclick="location.href='Manutenzione.php'">Manutenzione</button>

    </div>
  </main>
</body>
</html>
