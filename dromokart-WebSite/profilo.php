<?php include 'default/footerHome.php'; ?>
<?php include 'default/headerProfilo.php'; ?>
<?php require 'logic/controlloLogin.php'; ?>

<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Profilo Privato - Dromokart</title>
  <!-- Importa il font Roboto -->
  <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap" rel="stylesheet">
  <!-- Collegamento al file CSS esterno -->
  <link rel="stylesheet" href="css/profilo.css">
</head>
<body>
 
  <!-- Hero Section -->
  <section class="hero">
    <h2>Benvenuto/a, <?php echo htmlspecialchars($name); ?>!</h2>
    <p>Accedi alle tue informazioni personali e alle funzionalità riservate</p>
  </section>

  <!-- Main Content: Area Bottoni -->
  <main>
    <div class="profile-buttons">
      <button onclick="location.href='migliori_tempi.php'">Guarda i tuoi tempi migliori</button>
      <button onclick="location.href='prenota_gara.php'">Prenota una gara libera</button>
      <button onclick="location.href='acquistaKart.php'">Acquista un kart</button>
      <button onclick="location.href='acquistaPezzi.php'">Acquista pezzi di ricambio</button>
      <button onclick="location.href='mostraPrenotUser.php'">Prenotazioni effettuate</button>
      <button onclick="location.href='mostraKartUser.php'">Il tuo kart</button>
    </div>
  </main>
</body>
</html>
