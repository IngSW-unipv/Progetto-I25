<?php
session_start();
// Imposta il testo e il link del bottone in base al rank
switch ($_SESSION['rank']) {
    case "0":
        $btnText = 'Profilo';
        $link = 'profilo.php';
        break;
    case "1":
        $btnText = 'Meccanico';
        $link = 'meccanico.php';
        break;
    case "2":
        $btnText = 'Gestore Concessionaria';
        $link = 'gestoreConcessionaria.php';
        break;
    case "3":
        $btnText = 'Arbitro';
        $link = 'arbitro.php';
        break;
    case "4":
        $btnText = 'Organizzatore';
        $link = 'organizzatore.php';
        break;
    case "5":
        $btnText = 'Proprietario';
        $link = 'proprietario.php';
        break;
    default:
        $btnText = 'Profilo';
        $link = 'profilo.php';
}
?>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title><?php echo $btnText; ?></title>
    <link rel="stylesheet" href="../css/registration.css"> <!-- <--- AGGIUNGI QUESTA RIGA! -->
</head>
<body>
  <!-- Header -->
  <header>
    <div class="header-container">
      <div class="logo">
        <a href="index.php">
          <img src="../immagini/LOGO_KART.png" alt="Logo Dromokart">
        </a>
      </div>
      <nav>
        <ul>
          <li><a href="<?php echo $link; ?>"><?php echo $btnText; ?></a></li>
          <li><a href="default/logout.php">Logout</a></li>
        </ul>
      </nav>
    </div>
  </header>
<!-- NON chiudere qui il body e html! -->
