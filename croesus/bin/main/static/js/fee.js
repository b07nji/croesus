function compareFees() {
	
	var feeDiv = document.getElementById('feeDiv')
	while (feeDiv.firstChild) {
		feeDiv.removeChild(feeDiv.firstChild);
	}
	
	var url = "/fee"
	fetch(url)
		.then(response => {
			return  response.json();
			
		}).then(json => {		
			console.log(json);
			var comparison = json.comparisonTable;
			if (comparison !== null) {
				createTable(json, comparison, '手数料比較', 'comparisonTable');
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
	var comparison = document.getElementById('comparisonTable');
	if (id !== null || comparison !== null) { return; }
	
	var table = document.createElement("table");
	table.id = tableId;
	
	var html = ``;
	if (obj === json.monexFee) {
		html = `<thead><tr><td style="font-size : 15px;" colspan="3">${company}</td></tr></thead><tbody>`;
			
	} else if(obj === json.comparisonTable) {
		html = `<thead><tr><td style="font-size : 15px;" colspan="6">手数料比較</td></tr></thead><tbody>`;
		
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
		} else if(obj === json.comparisonTable) {
			html += `\
				<tr>\
					<td>${obj[i].maxExcurtionFee}</td>\
					<td>${obj[i].matsui}</td>\
					<td>${obj[i].monex}</td>\
					<td>${obj[i].rakuten}</td>\
					<td>${obj[i].gmo}</td>\
					<td>${obj[i].sbi}</td>\
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
			
	} else if(obj === json.comparisonTable) {
		html += `<tr><td colspan="6"><a name=${tableId} href="javascript:void(0);" onclick="closeTable(name);">close</a></td></tr></tbody>`;
	} else {
		html += `<tr><td colspan="2"><a name=${tableId} href="javascript:void(0);" onclick="closeTable(name);">close</a></td></tr></tbody>`;
	}

	table.innerHTML = html;
	
	var parent = document.getElementById('feeDiv');
	parent.appendChild(table);
}


