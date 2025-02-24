<?php session_Start();?>

<header>
    <div class="header-container">
      <div class="logo">
        <a href="index.php">
          <img src="../immagini/LOGO_KART.png" alt="Logo Dromokart">
        </a>
      </div>
      <nav>
        <ul>
          <li><a href="index.php">Home</a></li>
          <li><a href="chi-siamo.php">Chi Siamo</a></li>
          <li><a href="tracciato.php">Il Tracciato</a></li>
          <li><a href="classifica.php">Classifica</a></li>
          <li><a href="concessionaria-home.php">Concessionario</a></li>
          <?php
          if(isset($_SESSION['username'])){
            echo '<li><a href="/default/logout.php">Logout</a></li>';
          } else{
            echo '<li><a id="loginBtn">LOGIN</a></li>';
          }
          ?>   
        </ul>
      </nav>
    </div>
  </header>