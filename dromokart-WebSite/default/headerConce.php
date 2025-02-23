<?php session_Start();?>


<!-- Header -->
<header>
    <div class="header-container">
      <div class="logo">
        <a href="concessionaria-home.php">
          <img src="immagini/LOGO_KART.png" alt="Logo CacioKart">
        </a>
      </div>
      <nav>
        <ul>
          <li><a href="index.php">Home</a></li>
          <li><a href="chi-siamo.php">Chi Siamo</a></li>
          <li><a href="concessionaria-home.php">I Nostri Modelli</a></li>
          <li><a href="parti.php">Parti di ricambio</a></li>
          <?php
          if(isset($_SESSION['username'])){
            echo '<li><a href="logout.php">Logout</a></li>';
          } else{
            echo '<li><a id="loginBtn">LOGIN</a></li>';
          }
          ?>         
        </ul>
      </nav>
    </div>
  </header>