<?php include 'default/footerHome.php'; ?>
<?php include 'default/headerProfilo.php'; ?>
<?php require 'logic/controlloLogin.php'; ?>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Aggiungi Team</title>
  <!-- Importa il font Roboto -->
  <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap" rel="stylesheet">
  <!-- Collegamento al file CSS esterno -->
  <link rel="stylesheet" href="css/registration.css">
</head>
<body>

  <!-- Main Content - Form di Registrazione -->
  <main>
    <section class="form-section">
      <h1>Aggiungi Team</h1>
      <form action="logic/aggiungiTeam.php" method="post" class="registration-form">
         <div class="form-group">
            <label for="Nome">Nome Team</label>
            <input type="text" id="Nome" name="Nome" maxlength="49" required>
         </div>
         <div class="form-group">
            <label for="Colore">Colore</label>
            <input type="text" id="Colore" name="Colore" maxlength="49" readonly required>
         </div>
         <div class="form-group">
            <button type="submit">Aggiungi Team</button>
         </div>
      </form>
    </section>
  </main>
  
</body>
</html>
