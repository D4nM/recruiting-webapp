
$(document).on("click", "#closeButton-notifica", function(){
	
	// https://developer.mozilla.org/en-US/docs/Web/API/Web_Animations_API/Keyframe_Formats
	// https://developer.mozilla.org/en-US/docs/Web/CSS/easing-function
	const slideRightAnimation = [
		
		{ transform: "translate(0)" }, // keyframe (stato) iniziale
		{ transform: "translate(150%)" } // keyframe (stato) finale
	];
	
	const slideRightTiming = {
		
		duration: 500,
		iterations: 1
	};
	
	document.querySelector("#container-notifica").animate(slideRightAnimation, slideRightTiming);
	
	setTimeout(function(){
		$("#container-notifica").css("opacity", 0);
		$("#container-notifica").remove();
		
	}, 450);
	
	// TODO sistemare il fatto che il riquadro, al termine dell'animazione, compare una frazione di secondo nella posizione originale prima di venire eliminato
	
	/*
	// un test con jQuery
	$("#container-notifica").animate(
		{
			transform: translate(150%)
		},
		duration: 1000,
		complete: function(){
			$(this).remove();
		}
	);
	*/
})