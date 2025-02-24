<?php include 'default/footerHome.php'; ?>
<?php include 'default/headerHome.php'; ?>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Iscriviti - Dromokart</title>
  <!-- Importa il font Roboto -->
  <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap" rel="stylesheet">
  <!-- Collegamento al file CSS esterno -->
  <link rel="stylesheet" href="css/registration.css">
  <script src="../logic/limits.js" defer></script>
</head>
<body>

  <!-- Main Content - Form di Registrazione -->
  <main>
    <section class="form-section">
      <h1>Iscriviti</h1>
      <form action="logic/register.php" method="post" class="registration-form">
         <div class="form-group">
            <label for="nome">Nome</label>
            <input type="text" id="nome" name="nome" maxlength="30" required>
         </div>
         <div class="form-group">
            <label for="cognome">Cognome</label>
            <input type="text" id="cognome" name="cognome" maxlength="30" required>
         </div>
         <div class="form-group">
            <label for="data-nascita">Data di nascita</label>
            <input type="date" id="data-nascita" name="data_nascita" required max="<?php echo date('Y-m-d'); ?>" min="1950-01-01">
         </div>
         <div class="form-group">
            <label for="codice-fiscale">Codice Fiscale</label>
            <input type="text" id="codice-fiscale" name="codice_fiscale" maxlength="16" required >
         </div>
         <div class="form-group">
            <label for="email">Email</label>
            <input type="email" id="email" name="email" maxlength="70" required>
         </div>
         <div class="form-group">
            <label for="password">Password</label>
            <input type="password" id="password" name="password" minlength="8" maxlength="16" required>
         </div>
         <div class="form-group">
            <button type="submit">Registrati</button>
         </div>
      </form>
    </section>
  </main>

</body>
</html>
