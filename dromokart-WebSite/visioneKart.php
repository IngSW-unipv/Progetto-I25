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
  
  <form id="formAuto">
    <label for="targa">Targa:</label>
    <input type="text" id="targa" name="targa" required><br><br>

    <label for="cilindrata">Cilindrata:</label>
    <select id="cilindrata" name="cilindrata">
      <option value="50">50cc</option>
      <option value="125">125cc</option>
      <option value="150">150cc</option>
    </select><br><br>

    <label for="serbatoio">Serbatoio (litri):</label>
    <input type="number" id="serbatoio" name="serbatoio" readonly><br><br>

    <input type="submit" value="Invia">
  </form>

  <script>
    // Funzione che aggiorna il valore del serbatoio in base alla cilindrata selezionata
    function aggiornaSerbatoio() {
      var cilindrata = document.getElementById('cilindrata').value;
      var serbatoioInput = document.getElementById('serbatoio');
      
      switch (cilindrata) {
        case "50":
          serbatoioInput.value = 10;
          break;
        case "125":
          serbatoioInput.value = 15;
          break;
        case "150":
          serbatoioInput.value = 20;
          break;
        default:
          serbatoioInput.value = "";
      }
    }

    // Aggiorna il serbatoio quando cambia la selezione della cilindrata
    document.getElementById('cilindrata').addEventListener('change', aggiornaSerbatoio);

    // Imposta il valore iniziale del serbatoio al caricamento della pagina
    window.onload = aggiornaSerbatoio;
  </script>
  
</body>
</html>
