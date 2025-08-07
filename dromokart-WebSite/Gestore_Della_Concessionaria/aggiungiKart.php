<?php
include '../default/headerProfilo.php';      // Include head, CSS, body, header
require '../logic/controlloLogin.php';
?>

<main>
  <section class="form-section">
    <h1>Aggiungi Kart</h1>
    <form action="../logic/aggiuntaKart.php" method="post" class="registration-form">
      <div class="form-group">
        <label for="targa">Targa</label>
        <input type="text" id="targa" name="targa" value="KRT" maxlength="6" required>
      </div>
      <div class="form-group">
        <label for="cilindrata">Cilindrata</label>
        <select id="cilindrata" name="cilindrata" required>
          <option value="">Seleziona cilindrata</option>
          <option value="50">50cc</option>
          <option value="125">125cc</option>
          <option value="150">150cc</option>
        </select>
      </div>
      <div class="form-group">
        <label for="prezzo">Prezzo</label>
        <input type="text" id="prezzo" name="prezzo" readonly required>
      </div>
      <div class="form-group">
        <button type="submit" class="btn-green">Aggiungi Kart</button>
      </div>
    </form>
  </section>
</main>

<?php include '../default/footerHome.php'; ?>

<script>
  document.getElementById("cilindrata").addEventListener("change", function() {
    const prezzoInput = document.getElementById("prezzo");
    if (this.value === "50") {
      prezzoInput.value = "2500";
    } else if (this.value === "125") {
      prezzoInput.value = "4500";
    } else if (this.value === "150") {
      prezzoInput.value = "6500";
    } else {
      prezzoInput.value = ""; // Nessuna cilindrata selezionata
    }
  });

  document.getElementById("targa").addEventListener("input", function() {
    let targaInput = this;
    if (!targaInput.value.startsWith("KRT")) {
      targaInput.value = "KRT";
    }
    let numeri = targaInput.value.substring(3).replace(/\D/g, "");
    targaInput.value = "KRT" + numeri.substring(0, 3);
  });

  document.getElementById("targa").addEventListener("keydown", function(event) {
    if (this.selectionStart < 3) {
      event.preventDefault();
    }
    if (this.selectionStart >= 3 && !/[0-9]/.test(event.key) && event.key !== "Backspace") {
      event.preventDefault();
    }
  });
</script>
