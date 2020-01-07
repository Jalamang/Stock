package com.jbbwebsolutions.stocks.dao;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.google.gson.Gson;
import com.jbbwebsolutions.stocks.model.Sector;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class SectorDAO implements IQuery<Sector> {

	@Override
	public List<Sector> findBy(Map<String, String> map) {

		List<Sector> sectors = new ArrayList<Sector>();
		Gson gson = new Gson();

		Bson bson = new Document();

		if (map.containsKey("sector")) {
			String sectorSearch = map.get("sector");
			String[] parts = sectorSearch.split(":");

			if (parts[1].equalsIgnoreCase("all")) {

			} else if (parts[0].contentEquals("eq")) {
				bson = eq("sector", parts[1]);
			}

			System.out.println(Arrays.toString(parts));

		}


		try (MongoClient mc = new MongoClient("localhost", 27017);) {

			MongoDatabase db = mc.getDatabase("stockdb");
			MongoCollection<Document> collection = db.getCollection("sectors");

			FindIterable<Document> list = collection.find(bson);

			for (Document document : list) {
				String json = document.toJson();
				Sector sector = gson.fromJson(json, Sector.class);
				sectors.add(sector);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return sectors;
	}

	@Override
	public List<Sector> findAll() {
		Map<String, String> map = new HashMap<>();
		return this.findBy(map);
	}
}