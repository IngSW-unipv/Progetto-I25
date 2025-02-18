<?php include 'default/footerHome.php'; ?>
<?php include 'default/headerProfilo.php'; ?>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Prenota Gara Libera - Dromokart</title>
  <!-- Importa il font Roboto -->
  <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap" rel="stylesheet">
  <!-- Collegamento al file CSS esterno -->
  <link rel="stylesheet" href="css/prenota_gara.css">
</head>
<body>
  
  <!-- Main Content -->
  <main>
    <h1>Prenota Gara Libera</h1>
    <form action="prenota_gara.php" method="post" class="reservation-form">
      <!-- Sezione Orari -->
      <div class="timeslot-section">
        <h2>Seleziona un orario</h2>
        <div class="timeslots">
          <input type="radio" id="ts1" name="timeslot" value="10:30-11:00" required>
          <label for="ts1">10:30-11:00</label>
          
          <input type="radio" id="ts2" name="timeslot" value="11:00-11:30">
          <label for="ts2">11:00-11:30</label>
          
          <input type="radio" id="ts3" name="timeslot" value="11:30-12:00">
          <label for="ts3">11:30-12:00</label>
          
          <input type="radio" id="ts4" name="timeslot" value="12:00-12:30">
          <label for="ts4">12:00-12:30</label>
          
          <input type="radio" id="ts5" name="timeslot" value="12:30-13:00">
          <label for="ts5">12:30-13:00</label>
          
          <input type="radio" id="ts6" name="timeslot" value="13:00-13:30">
          <label for="ts6">13:00-13:30</label>
          
          <input type="radio" id="ts7" name="timeslot" value="13:30-14:00">
          <label for="ts7">13:30-14:00</label>
        </div>
      </div>
      
      <!-- Sezione Data e Pilota -->
      <div class="additional-info">
        <div class="form-group">
          <label for="date">Scegli il giorno:</label>
          <input type="date" id="date" name="date" required>
        </div>
        <div class="form-group">
          <label for="pilot">Nome del pilota:</label>
          <input type="text" id="pilot" name="pilot" placeholder="Inserisci il tuo nome" required>
        </div>
      </div>
      
      <!-- Bottone Submit -->
      <div class="form-group submit-group">
        <button type="submit">Prenota Gara Libera</button>
      </div>
    </form>
  </main>
  
</body>
</html>
