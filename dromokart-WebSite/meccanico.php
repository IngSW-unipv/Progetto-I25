<?php include 'default/footerHome.php'; ?>
<?php include 'default/headerProfilo.php'; ?>

<?php

// Controlla se l'utente è loggato
if (!isset($_SESSION['username'])) {
    // Se non è loggato, reindirizza al login
    header("Location: index.php");
    exit;
}

$username = $_SESSION['username'];
?>

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
    <h2>Benvenuto, <?php echo htmlspecialchars($username); ?>!</h2>
    <p>Accedi alle tue informazioni personali e alle funzionalità riservate</p>
  </section>

  <!-- Main Content: Area Bottoni -->
  <main>
    <div class="profile-buttons">
      <button onclick="location.href='visioneKart.php'">Visione dei kart</button>
      <button onclick="location.href='aggiungiKartMeccanico.php'">Aggiungere kart</button>
      <button onclick="location.href='eliminaKartMeccanico.php'">Togliere kart</button>
      <button onclick="location.href='aggiungaBenzina.php'">aggiungere benzina</button>
      <button onclick="location.href='Manutenzione.php'">Manutenzione</button>

    </div>
  </main>
</body>
</html>
