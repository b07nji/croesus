function compareFees() {
	
	var url = "/fee"
		
	fetch(url)
		.then(response => {
			return  response.json();
			
		}).then(json => {
			
			function createTable(obj, company, tableId) {
				
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

				table.innerHTML = html + `</tbody>`;
				
				var parent = document.getElementById('feeDiv');
				parent.appendChild(table);
			}
			

			var matsui = json.matsuiFee;
			if (matsui !== null) {
				createTable(matsui, '松井証券', 'matsuiFeeTable');
			}
				
			var monex = json.monexFee;
			if (monex !== null) {
				createTable(monex, 'マネックス証券', 'monexFeeTable');
			}
			
			var rakuten = json.rakutenFee;
			if (rakuten !== null) {
				createTable(rakuten, '楽天証券', 'rakutenFeeTable');
			}
			
			var gmo = json.gmoFee;
			if (gmo !== null) {
				createTable(gmo, 'GMOクリック証券', 'gmoFeeTable');
			}
			
			var sbi = json.sbiFee;
			if (sbi !== null) {
				createTable(sbi, 'SBI証券', 'sbiFeeTable');
			}
			
		});

	
}

/*
`\
<tr>\
<td>${matsui[0].maxExcurtionFee}</td>\
<td>${matsui[0].fee}</td>\
</tr>\
<tr>\
<td>10万円まで</td>\
<td></td>\
</tr>\
<tr>\
<td>30万円まで</td>\
<td></td>\
</tr>\
<tr>\
<td>50万円まで</td>\
<td></td>\
</tr>\
<tr>\
<td>100万円まで</td>\
<td></td>\
</tr>\
<tr>\
<td>200万円まで</td>\
<td></td>\
</tr>\
<tr>\
<td>100万円増えるごとに1000円加筆</td>\
</tr>\
<tr>\
<td>1億円越</td>\
<td></td>\
</tr>\
`;
*/


