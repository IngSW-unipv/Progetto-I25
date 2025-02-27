<?php include 'default/footerHome.php'; ?>
<?php include 'default/headerProfilo.php'; ?>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Aggiungi Gara Secca</title>
  <!-- Importa il font Roboto -->
  <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap" rel="stylesheet">
  <!-- Collegamento al file CSS esterno -->
  <link rel="stylesheet" href="css/registration.css">
</head>
<body>

  <!-- Main Content - Form di Registrazione -->
  <main>
    <section class="form-section">
      <h1>Aggiungi Gara Secca</h1>
      <form action="logic/aggiungiGaraSecca.php" method="post" class="registration-form">
         <div class="form-group">
            <label for="Gara">ID GARA</label>
            <input type="text" id="Gara" name="Gara" maxlength="5" required>
         </div>
         <div class="form-group">
            <label for="Circuito">ID CIRCUITO</label>
            <input type="text" id="Circuito" name="Circuito" maxlength="5" required>
         </div>
         <div class="additional-info">
        <div class="form-group">
          <label for="date">Scegli il giorno:</label>
          <input type="date" id="date" name="date" min="<?php echo date('Y-m-d', strtotime('+1 day')); ?>" required>
        </div>
      </div>
         <div class="form-group">
            <button type="submit">Aggiungi Gara Secca</button>
         </div>
      </form>
    </section>
  </main>
  
</body>
</html>
