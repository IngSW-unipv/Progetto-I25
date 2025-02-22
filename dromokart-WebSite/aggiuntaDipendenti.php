<?php /*
  session_start();
  if(!isset($_SESSION['username']) || $_SESSION['rank'] != "5"){
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
  <title>Pagina Proprietario</title>
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
            <li><a id="logoutBtn">Logout</a></li>
         </ul>
         </nav>
      </div>
   </header>
    <section class="form-section">
      <h1>Inserisci un dipendente</h1>
      <!-- lista attributi: cf, nome, cognome, mail, password, data_n, ruolo, ore_lavorate, stipendio -->
      <form action="logic/registerDip.php" method="post" class="registration-form">
      <div class="form-group">
            <label for="codice-fiscale">Codice Fiscale</label>
            <input type="text" id="codice-fiscale" name="codice_fiscale" required>
         </div>
         <div class="form-group">
            <label for="nome">Nome</label>
            <input type="text" id="nome" name="nome" required>
         </div>
         <div class="form-group">
            <label for="cognome">Cognome</label>
            <input type="text" id="cognome" name="cognome" required>
         </div>         
         <div class="form-group">
            <label for="email">Email</label>
            <input type="email" id="email" name="email" required>
         </div>
         <div class="form-group">
            <label for="password">Password</label>
            <input type="password" id="password" name="password" minlength="8" maxlength="16" required>
         </div>
         <div class="form-group">
            <label for="data-nascita">Data di nascita</label>
            <input type="date" id="data-nascita" name="data_nascita" required>
         </div>
         <div class="form-group">
            <label for="rank">Ruolo</label> 
            <select id="rank" name="rank">
               <option value="1">Meccanico</option>
               <option value="2">Gestore concessionaria</option>
               <option value="3">Arbitro</option>
               <option value="4">Organizzatore</option>
            </select>
         </div>
         <div class="form-group">
            <button type="submit">Registra</button>
         </div>
      </form>
    </section>
  </main>

</body>
</html>