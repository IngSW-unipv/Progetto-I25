<?php include 'default/footerHome.php'; ?>
<?php include 'default/headerProfilo.php'; ?>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Aggiungi Kart</title>
  <!-- Importa il font Roboto -->
  <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap" rel="stylesheet">
  <!-- Collegamento al file CSS esterno -->
  <link rel="stylesheet" href="css/registration.css">
</head>
<body>

  <!-- Main Content - Form di Registrazione -->
  <main>
    <section class="form-section">
      <h1>Aggiungi Kart</h1>
      <form action="logic/aggiuntaKart.php" method="post" class="registration-form">
         <div class="form-group">
            <label for="targa">Targa</label>
            <input type="text" id="targa" name="targa" maxlength="6" required>
         </div>
         <div class="form-group">
            <label for="cilindrata">Cilindrata</label>
            <select id="cilindrata" name="cilindrata" required>
              <option value="">Seleziona cilindrata</option>
              <option value="50">50cc</option>
              <option value="125">125cc</option>
              <option value="150">150cc</option>
            </select>
         </div>
         <div class="form-group">
            <button type="submit">Aggiungi Kart</button>
         </div>
      </form>
    </section>
  </main>
  
</body>
</html>
