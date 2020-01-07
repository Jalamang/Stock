db = db.getSisterDB("stockdb")
db.stocks.drop()

for (let i = 0; i < 50; i++){
	let symbol = 'irm-' + i
	let name = "iron mountain-" + i
	let price = 40 + i
	let category = 'c1'
	let _id = 1000 + i
	let data = {_id,symbol,name,price,category}
	db.stocks.insert(data)
}

for (let i = 0; i < 150; i++){
	let symbol = 'googl-' + i
	let name = "google-" + i
	let price = 900 + i
	let category = 'c2'
	let _id = 2000 + i
	let data = {_id,symbol,name,price,category}
	db.stocks.insert(data)
}
