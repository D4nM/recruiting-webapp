const bottoneSubmitRicerca = document.querySelector("#bottone-ricerca");
const divCreaAnnuncio = document.querySelector("#apri-azienda");
const divChiudiAnnuncio = document.querySelector("#chiudi-azienda");
const bottoneCreaAnnuncio = document.querySelector("#bottone-apri");
const bottoneChiudiAnnuncio = document.querySelector("#bottone-chiudi");
const createAnnuncio = document.querySelector("#create-annuncio");
const divNessunaRicerca = document.querySelector("#nessuna-ricerca-div");
const divBarreRicerca = document.querySelector("#div-ricerca-azienda");
const bottoneRicerca = document.querySelector("#bottone-ricerca");
const benvenutoScritta = document.querySelector("#benvenuto");
const risultatiRicerca = document.querySelector("#risultati-ricerca");
const divIntero = document.querySelector("#sfondo-inizio-azienda");
const divRisultatiRicerca = document.querySelector(".container-anteprima");
const divRisultatiConTitolo = document.querySelector(".colonna-sinistra");
const hiddenBoxClass = "hidden-box";
const titoloHover = "hoverTitolo";
const bottoneTesto = document.querySelector(".bottone-testo");
const bottoneIndietro = document.querySelector(".bottone-indietro");
const bobberto = document.querySelector(".bobberto");
const textArea = document.querySelector("#textarea-annuncio");
const bottoneCrea = document.querySelector("#bottone-crea-annuncio");


bottoneCreaAnnuncio.addEventListener("click", ()=>{
	divCreaAnnuncio.classList.add(hiddenBoxClass);
	divIntero.classList.add(hiddenBoxClass);
	createAnnuncio.classList.remove(hiddenBoxClass);
	if(divNessunaRicerca != null){
		divNessunaRicerca.classList.add(hiddenBoxClass);
	}
	
	if(risultatiRicerca != null){
		risultatiRicerca.classList.add(hiddenBoxClass);
	}
	
})

bottoneChiudiAnnuncio.addEventListener("click", ()=>{
	divCreaAnnuncio.classList.remove(hiddenBoxClass);
	divIntero.classList.remove(hiddenBoxClass);
	createAnnuncio.classList.add(hiddenBoxClass);
	if(divNessunaRicerca != null){
		divNessunaRicerca.classList.remove(hiddenBoxClass);
	}
	
	if(risultatiRicerca != null){
		risultatiRicerca.classList.remove(hiddenBoxClass);
	}
	
	document.querySelector("form#form-creazione-annuncio").reset();
})

if(divRisultatiRicerca != null){
	divRisultatiRicerca.addEventListener("click", ()=>{
		$(divRisultatiRicerca).children("form").submit();
	})
}

bottoneTesto.addEventListener("click", ()=>{
	bottoneTesto.classList.add(hiddenBoxClass);
	bottoneIndietro.classList.remove(hiddenBoxClass);
	bobberto.classList.add(hiddenBoxClass);
	textArea.classList.remove(hiddenBoxClass);
	bottoneCrea.classList.remove(hiddenBoxClass);
})

bottoneIndietro.addEventListener("click", ()=>{
	bottoneTesto.classList.remove(hiddenBoxClass);
	bottoneIndietro.classList.add(hiddenBoxClass);
	bobberto.classList.remove(hiddenBoxClass);
	textArea.classList.add(hiddenBoxClass);
	bottoneCrea.classList.add(hiddenBoxClass);
})


/*$(divRisultatiRicerca).hover(mouseIn, mouseOut);


function mouseIn(){
	$(divRisultatiConTitolo).children("h1").addClass(titoloHover);
}

function mouseOut(){
	$(divRisultatiConTitolo).children("h1").removeClass(titoloHover);
}*/
