db = db.getSisterDB("stockdb")
collection = db.stocks;
db.sectors.drop()
result = collection.aggregate( 
            [
                {"$group": { "_id": { code: "$category", sectorName: "$sector" } } },
                {"$project": {"_id.code": 1, "_id.sectorName": 1} },
                {"$project": {_id: "$_id.code", sector: "$_id.code",sectorDescription: "$_id.sectorName" } },
                {"$sort": {_id: 1} },
                { $out: "sectors" }
            ]
        );
