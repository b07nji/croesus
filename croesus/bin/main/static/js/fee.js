function compareFees() {
	
	var url = "/fee"
	fetch(url)
		.then(response => {
			return  response.json();
			
		}).then(json => {		

			var matsui = json.matsuiFee;
			if (matsui !== null) {
				createTable(json, matsui, '松井証券', 'matsuiFeeTable');
			}
							
			var monex = json.monexFee;
			if (monex !== null) {
				createTable(json, monex, 'マネックス証券', 'monexFeeTable');
			}
			
			var rakuten = json.rakutenFee;
			if (rakuten !== null) {
				createTable(json, rakuten, '楽天証券', 'rakutenFeeTable');
			}
			
			var gmo = json.gmoFee;
			if (gmo !== null) {
				createTable(json, gmo, 'GMOクリック証券', 'gmoFeeTable');
			}
			
			var sbi = json.sbiFee;
			if (sbi !== null) {
				createTable(json, sbi, 'SBI証券', 'sbiFeeTable');
			}			
		});
}

function showTable(id) {
	var url = "/fee"
	fetch(url)
		.then(response => {
			return  response.json();
			
		}).then(json => {	
			
			switch(id) {
		
			case 'matsui':
				var matsui = json.matsuiFee;
				createTable(json, matsui, '松井証券', 'matsuiFeeTable');
				break;
				
			case 'monex':
				var monex = json.monexFee;
				createTable(json, monex, 'マネックス証券', 'monexFeeTable');
				break;
			
			case 'rakuten':
				var rakuten = json.rakutenFee;
				createTable(json, rakuten, '楽天証券', 'rakutenFeeTable');
				break;
			
			case 'gmo':
				var gmo = json.gmoFee;
				createTable(json, gmo, 'GMOクリック証券', 'gmoFeeTable');
				break;
			
			case 'sbi':
				var sbi = json.sbiFee;
				createTable(json, sbi, 'SBI証券', 'sbiFeeTable');
				break;
			}
			
			
		});
}

function closeTable(name) {
	
	var parent = document.getElementById('feeDiv');
	var table = document.getElementById(name);
	parent.removeChild(table);
}

function createTable(json, obj, company, tableId) {
	
	var id = document.getElementById(tableId);
	if (id !== null) { return; }
	
	var table = document.createElement("table");
	table.id = tableId;
	
	var html = ``;
	if (obj === json.monexFee) {
		html = `<thead><tr><td style="font-size : 15px;" colspan="3">${company}</td></tr></thead><tbody>`;
			
	} else {
		html = `<thead><tr class="company"><td style="font-size : 15px;" colspan="2">${company}</td></tr></thead><tbody>`;
	}
	
	for (var i = 0; i < obj.length; i++) {
		
		if (obj === json.monexFee) {
			html += `\
				<tr>\
					<td>${obj[i].maxExcurtionFee}</td>\
					<td>${obj[i].feeForPc}</td>\
					<td>${obj[i].feeForPhone}</td>\
				</tr>
					`;
		} else {
			html += `\
				<tr>\
					<td>${obj[i].maxExcurtionFee}</td>\
					<td>${obj[i].fee}</td>\
				</tr>
					`;
		}
	}
	//last row
	if (obj === json.monexFee) {
		html += `<tr><td colspan="3"><a name=${tableId} href="javascript:void(0);" onclick="closeTable(name);">close</a></td></tr></tbody>`;
			
	} else {
		html += `<tr><td colspan="2"><a name=${tableId} href="javascript:void(0);" onclick="closeTable(name);">close</a></td></tr></tbody>`;
	}

	table.innerHTML = html;
	
	var parent = document.getElementById('feeDiv');
	parent.appendChild(table);
}


