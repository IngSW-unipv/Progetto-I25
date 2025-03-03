<?php
    //controllo sul rango attraverso la variabile di sessione
    session_start();
    if($_SESSION['rank'] != 5){
        header('Location: ../index.php');
        die();
    }
?>
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
          <li><a href="default/logout.php" id="logoutBtn">Logout</a></li>
        </ul>
      </nav>
    </div>
  </header>