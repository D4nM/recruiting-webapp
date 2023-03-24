const aziendaButton = document.querySelector(".aziendaButton");
const utenteButton = document.querySelector(".utenteButton");
const movingButton = document.querySelector(".movingButton");
const aziendaForm = document.querySelector("#azienda");
const utenteForm = document.querySelector("#privato");

utenteButton.addEventListener("click", ()=>{
	movingButton.classList.add("right");
	utenteForm.classList.add("utenteForm");
	aziendaForm.classList.remove("aziendaForm");
	movingButton.innerHTML = "Utente";
})

aziendaButton.addEventListener("click", ()=>{
	movingButton.classList.remove("right");
	utenteForm.classList.remove("utenteForm");
	aziendaForm.classList.add("aziendaForm");
	movingButton.innerHTML = "Azienda";
})

