<?php
   include 'default/headerProprietario.php';
   include 'default/footerConce.php';
?>


<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Pagina aggiunta dipendenti</title>
  <!-- Importa il font Roboto -->
  <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap" rel="stylesheet">
  <!-- Collegamento al file CSS esterno -->
  <link rel="stylesheet" href="css/registration.css">
</head>
<body>
  <main>
    <section class="form-section">
      <h1>Inserisci un dipendente</h1>
      <!-- lista attributi: cf, nome, cognome, mail, password, data_n, ruolo, ore_lavorate, stipendio -->
      <form action="logic/registerDip.php" method="post" class="registration-form">
         <div class="form-group">
            <label for="nome">Nome</label>
            <input type="text" id="nome" name="nome" maxlength="30" required pattern="^[A-Za-z]+$" title="inserire solo lettere">
         </div>
         <div class="form-group">
            <label for="cognome">Cognome</label>
            <input type="text" id="cognome" name="cognome" maxlength="30" required pattern="^[A-Za-z]+$" title="inserire solo lettere">
         </div>
         <div class="form-group">
            <label for="data-nascita">Data di nascita</label>
            <input type="date" id="data-nascita" name="data_nascita" required max="<?php echo date('Y-m-d'); ?>" min="1950-01-01">
         </div>
         <div class="form-group">
            <label for="codice-fiscale">Codice Fiscale</label>
            <input type="text" id="codice-fiscale" name="codice_fiscale" pattern ="^[A-Z0-9]+$" maxlength="16" minlength="16" required>
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
            <label for="rank">Ruolo</label> 
            <select id="rank" name="rank" required>
               <option value="meccanico">Meccanico</option>
               <option value="gestore">Gestore concessionaria</option>
               <option value="arbitro">Arbitro</option>
               <option value="organizzatore">Organizzatore</option>
            </select>
         </div>
            <div class="form-group">
            <label for="oreL">Ore lavorative</label>
            <input type="time" id="oreL" name="oreL">
         </div>
         <div class="form-group">
            <label for="stipendio">Stipendio</label>
            <input type="text" id="stipendio" name="stipendio" required maxlength="6">
         </div>
         <div class="form-group">
            <button type="submit">Registra</button>
         </div>
      </form>
    </section>
  </main>

</body>
</html>