<?php include 'default/footerHome.php'; ?> 
<?php include 'default/headerHome.php'; ?>
<?php $targa = isset($_POST['targa']) ? $_POST['targa'] : ''; ?>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Descrizione Manutenzione</title>
  <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="css/registration.css">
</head>
<body>
  <main>
    <section class="form-section">
      <h1>Manutenzione</h1>
      <form action="logic/manutenzione.php" method="post" class="registration-form">
         <div class="form-group">
            <label for="targa">Targa</label>
            <input type="text" id="targa" name="targa" maxlength="6" required readonly value="<?php echo htmlspecialchars($targa); ?>">
         </div>
         <div class="form-group">
            <label for="Descrizione">Descrizione</label>
            <input type="text" id="Descrizione" name="Descrizione" maxlength="1000" required>
         </div>
         <div class="form-group">
            <label for="prezzo">Prezzo</label>
            <input type="number" id="prezzo" name="prezzo" step="0.01" required>
         </div>
         <div class="form-group">
            <button type="submit">Manutenzione</button>
         </div>
      </form>
    </section>
  </main>
</body>
</html>