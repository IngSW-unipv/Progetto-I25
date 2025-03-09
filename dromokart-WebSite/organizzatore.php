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
    <h2>Benvenuto, <?php echo htmlspecialchars($name); ?>!</h2>
    <p>Accedi alle tue informazioni personali e alle funzionalità riservate</p>
  </section>

  <!-- Main Content: Area Bottoni -->
  <main>
    <div class="profile-buttons">
      <button onclick="location.href='test.php'">Crea Team</button>
      <button onclick="location.href='prenotazioneGaraSecca.php'">Prenotazione Gara Secca</button>
      <button onclick="location.href='creazioneGaraSecca.php'">Inserimento Gara Secca</button>
      <button onclick="location.href='prenota_gara.php'">Prenota Gara Libera</button>
      <button onclick="location.href='inserimentoCampionato.php'">Inserimento Campionato</button>
    </div>
  </main>
</body>
</html>
