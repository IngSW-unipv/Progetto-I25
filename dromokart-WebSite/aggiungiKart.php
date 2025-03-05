<?php include 'default/footerHome.php'; ?>
<?php include 'default/headerProfilo.php'; ?>
<?php require 'logic/controlloLogin.php'; ?>
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
         <!-- Nuovo input per il prezzo -->
         <div class="form-group">
            <label for="prezzo">Prezzo</label>
            <input type="text" id="prezzo" name="prezzo" readonly required>
         </div>
         <div class="form-group">
            <button type="submit">Aggiungi Kart</button>
         </div>
      </form>
    </section>
  </main>
  
  <!-- Script per impostare il prezzo in base alla cilindrata -->
  <script>
    document.getElementById("cilindrata").addEventListener("change", function() {
      const prezzoInput = document.getElementById("prezzo");
      if (this.value === "50") {
        prezzoInput.value = "2500";
      } else if (this.value === "125") {
        prezzoInput.value = "4500";
      } else if (this.value === "150") {
        prezzoInput.value = "6500";
      } else {
        prezzoInput.value = ""; // Nessuna cilindrata selezionata
      }
    });
  </script>

</body>
</html>
