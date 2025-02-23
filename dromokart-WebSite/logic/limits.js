// File per limitare la dimensione delle informazioni in ingresso nel form di registrazione

document.addEventListener("DOMContentLoaded", function () {
    const limits = {
        "nome": 30,
        "cognome": 30,
        "email": 100,
        "password": 100,
        "codice-fiscale": 16,
        "data-nascita": 10
    };

    Object.keys(limits).forEach(id => {
        const input = document.getElementById(id);
        if (input) {
            input.addEventListener("input", function () {
                if (this.value.length > limits[id]) {
                    this.value = this.value.slice(0, limits[id]);
                }

                if(id === "nome" || id === "cognome"){
                    this.value = this.value.replace(/[0-9]/g,'');
                }

            });
        }
    });
});
