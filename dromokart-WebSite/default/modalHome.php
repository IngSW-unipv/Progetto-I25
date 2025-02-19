<!-- Modal per il Login -->
<div id="loginModal" class="modal">
  <div class="modal-content">
    <span class="close-modal" id="closeModal">&times;</span>
    <h2>Login</h2>
    <form action="" method="post">
      <input type="text" name="username" placeholder="Nome utente" required>
      <input type="password" name="password" placeholder="Password" required>
      <button type="submit">Accedi</button>
    </form>
    <p class="register-link">Non sei iscritto? <a href="registrazione.php">Iscriviti qui</a></p>
  </div>
</div>


  <!-- Script per la gestione del Modal -->
  <script>
    document.addEventListener("DOMContentLoaded", function() {
      var loginBtn = document.getElementById("loginBtn");
      var modal = document.getElementById("loginModal");
      var closeModal = document.getElementById("closeModal");
      
      if (loginBtn) {
        loginBtn.addEventListener("click", function() {
          modal.style.display = "flex";
        });
      }
      if (closeModal) {
        closeModal.addEventListener("click", function() {
          modal.style.display = "none";
        });
      }
      window.addEventListener("click", function(event) {
        if (event.target === modal) {
          modal.style.display = "none";
        }
      });
    });
  </script>