<?php
  session_start();
  /*if(!isset($_SESSION['username']) || $_SESSION['rank'] != "5"){
    header('Location: index.php');
    die();
  }*/

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
   <!-- Header -->
   <header>
      <div class="header-container">
         <div class="logo">
         <a href="index.php">
            <img src="immagini/LOGO_KART.png" alt="Logo CacioKart">
         </a>
         </div>
         <nav>
         <ul>
            <li><a href="proprietario.php">Home</a></li>
            <li><a href="logout.php" id="logoutBtn">Logout</a></li>
         </ul>
         </nav>
      </div>
   </header>
    <section class="form-section">
      <h1>Elimina un dipendente</h1>
      <!-- lista attributi: cf, nome, cognome, mail, password, data_n, ruolo, ore_lavorate, stipendio -->
      <form action="logic/deleteDip.php" method="post" class="registration-form">
         <div class="form-group">
            <label for="codice-fiscale">Codice Fiscale</label>
            <input type="text" id="codice-fiscale" name="codice_fiscale" pattern ="^[A-Z0-9]+$" maxlength="16" minlength="16" required>
         </div>         
            <button type="submit">Elimina Dipendente</button>
         </div>
      </form>
    </section>
  </main>

</body>
</html>