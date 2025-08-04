<?php
    /*pagina di verifica del rango dell'utente per la creazione dell'header per
    ritornare all'area riservata*/
    if(isset($_SESSION['rank'])){
        switch($_SESSION['rank']){
        case 0:
            echo '<li><a href="profilo.php">Area riservata</a></li>';
            break;
        case 1:
            echo '<li><a href="Meccanico/meccanico.php">Area riservata</a></li>';
            break;
        case 2:
            echo '<li><a href="gestoreConcessionaria.php">Area riservata</a></li>';
            break;
        case 3:
            echo '<li><a href="arbitro.php">Area riservata</a></li>';
            break;
        case 4:
            echo '<li><a href="organizzatore.php">Area riservata</a></li>';
            break;
        case 5:
            echo '<li><a href="proprietario.php">Area riservata</a></li>';
            break;
        default:
            break;
        }
    }
?>