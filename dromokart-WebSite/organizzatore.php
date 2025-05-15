<?php include 'default/footerHome.php'; ?>
<?php include 'default/headerProfilo.php'; ?>
<?php require 'logic/controlloLogin.php'; ?>
<?php require 'logic/controlloLogin.php'; ?>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Profilo Privato - Organizzatore</title>
  <!-- Importa il font Roboto -->
  <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap" rel="stylesheet">
  <!-- Collegamento al file CSS esterno -->
  <link rel="stylesheet" href="css/profilo.css">
</head>
<body>
 
  <!-- Hero Section -->
  <section class="hero">
    <h2>Benvenuto/a, <?php echo htmlspecialchars($name); ?>!</h2>
    <p>Crea o modifica le prenotazioni e gestisci i dettagli dei campionati</p>
  </section>

  <!-- Main Content: Area Bottoni -->
  <main>
    <div class="profile-buttons">
      <button onclick="location.href='creazioneTeam.php'">Crea team per il campionato</button>
      <button onclick="location.href='inserimentoCampionato.php'">Associa gare a un campionato</button>
      <button onclick="location.href='prenota_gara.php'">Prenota una gara libera</button>
      <button onclick="location.href='prenotazioneGaraSecca.php'">Prenota una gara secca</button>
      <button onclick="location.href='creazioneGaraSecca.php'">Inserisci i partecipanti di una prenotazione</button>


    </div>
  </main>
</body>
</html>
