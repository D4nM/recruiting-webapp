const observer = new IntersectionObserver((elementi)=>{
	elementi.forEach((elemento)=>{
		if (elemento.isIntersecting){
			elemento.target.classList.add("shown-container");
		}
	});
});

const anteprime = document.querySelectorAll(".hidden-container");
anteprime.forEach((ant)=> observer.observe(ant));