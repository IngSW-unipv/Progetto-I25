<?php include '../default/headerProfilo.php'; ?>
<?php require '../logic/controlloLogin.php'; ?>

<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Pagina Proprietario</title>
  <!-- Importa il font Roboto -->
  <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="../css/profilo.css">
</head>
<body>

<section class="hero">
  <h2>Benvenuto/a, <?php echo htmlspecialchars($name); ?>!</h2>
  <p>Gestisci i dipendenti oppure visualizza il bilancio del kartodromo</p>
</section>

<div class="profile-buttons">
  <button onclick="location.href='bilancio.php'">Visualizza il bilancio</button>
  <button onclick="location.href='aggiuntaDipendenti.php'">Aggiungi un dipendente</button>
  <button onclick="location.href='rimozioniDipendenti.php'">Rimuovi un dipendente</button>
</div>

<?php include '../default/footerConce.php'; ?>

</body>
</html>
