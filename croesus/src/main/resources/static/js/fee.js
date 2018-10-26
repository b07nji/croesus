var url = "/fee"

fetch(url)
	.then(response => {
		console.log(response.json());
	});