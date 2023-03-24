
document.querySelectorAll(".container-anteprima").forEach(function(element){
	element.addEventListener("click", function(){
		element.querySelector("form#form-bottone").submit();
	});
});
