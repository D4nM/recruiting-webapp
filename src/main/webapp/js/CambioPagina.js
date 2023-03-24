const primapagina= document.querySelector("#primapagina");
const bottoneNext= document.querySelector("#bottone-next");
const bottoneBack= document.querySelector("#bottone-back");
const secondaPagina= document.querySelector("#seconda-pagina");
const formCreaAnnuncio = document.querySelector("#form-creazione-annuncio");
const hidden= "hidden-box";


bottoneNext.addEventListener("click", function (){
	primapagina.classList.add(hidden);
	bottoneNext.classList.add(hidden);
	secondaPagina.classList.remove(hidden);
	bottoneBack.classList.remove(hidden);
	
	formCreaAnnuncio.style.height = "100vh";
});

bottoneBack.addEventListener("click", function (){
	primapagina.classList.remove(hidden);
	bottoneNext.classList.remove(hidden);
	secondaPagina.classList.add(hidden);
	bottoneBack.classList.add(hidden);
	
	formCreaAnnuncio.style.height = "auto";
});