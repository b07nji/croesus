var url = "/fee"

fetch(url)
	.then(response => {
		return  response.json();
		
	}).then(json => {
		var matsui = json.matsuiFee;
		console.log(matsui);
		
		
		
		var sbi = json.sbiFee;
		console.log(sbi);
		
		var gmo = json.gmoFee;
		console.log(gmo);
		
		var rakuten = json.rakutenFee;
		console.log(rakuten);
		
		var monex = json.monexFee;
		console.log(monex);
		
	});