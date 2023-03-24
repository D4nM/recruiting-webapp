const recruit = document.querySelector("#resto");
const ing = document.querySelector("#ing");
const signup = document.querySelector("#sign-up");
const login = document.querySelector("#log-in");
const logout = document.querySelector("#log-out");
const bottoneProfilo = document.querySelector(".bottone-profilo");
const bottoneAreaRiservata = document.querySelector("#bottone-area-riservata");

const container = document.querySelector("#risultati-ricerca");

const loginGiaEffettuato = document.querySelector("#loginGiaEffettuato-container");


//
// Cambio colore delle scritte nell'header
//
const primiColori = ["#7A60A7", "#45628E", "#689AF2", "#1095C1"];
const secondiColori = ["#E9C133", "#FFC34D", "#F2A450", "#C14F24"];

window.onload = function(){
	const indice = Math.floor(Math.random() * primiColori.length);
	recruit.style.color = primiColori[indice];
	ing.style.color = secondiColori[indice];
	if(bottoneProfilo != null){
		bottoneProfilo.style.backgroundColor = secondiColori[indice];
	}
	if(bottoneAreaRiservata != null){
		bottoneAreaRiservata.style.color = primiColori[indice];
	}
	if(signup != null){
		signup.className = "sign-up" + indice;
	}
	if(login != null){
		login.className = "log-in" + indice;
	}
	if(logout != null){
		logout.className = "log-out" + indice;	
	}
	
	if(container != null){
		container.scrollIntoView(true);
	}
	
	if(loginGiaEffettuato != null){
		loginGiaEffettuato.scrollIntoView(true);
	}
	
}
