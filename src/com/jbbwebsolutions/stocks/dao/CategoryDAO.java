package com.jbbwebsolutions.stocks.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.jbbwebsolutions.stocks.dao.IQuery;
import com.jbbwebsolutions.stocks.model.Stock;
import com.mongodb.MongoClient;
import com.mongodb.client.DistinctIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class CategoryDAO implements IQuery<String> {

	private static List<String> list = new ArrayList<>();
	
	@Override
	public List<String> findAll() {
		if (list.size() > 0 ) {
			return list;
		}
		try (MongoClient mc =  new MongoClient("localhost", 27017);) {
				
				MongoDatabase db = mc.getDatabase("stockdb");
				// can do in mongdb terminal and did do in terminal
				DistinctIterable<String> distinctCategory 
				= db.getCollection("stocks").distinct("category", String.class);			
				list = new ArrayList<>();
				for (String category : distinctCategory) {
					list.add(category);
				}
				
				return list;
				
				//not needed
//			    FindIterable<Document> list = collection.find();
//			    
//			    for (Document document : list) {
//			    	
//				}		    			 
			 
			}
			catch (Exception e){
				e.printStackTrace();
			}		return new ArrayList<>();
	}

}
