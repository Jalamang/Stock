package com.jbbwebsolutions.stocks.driver;

import com.jbbwebsolutions.stocks.dao.IQuery;
import com.jbbwebsolutions.stocks.dao.SectorDAO;
import com.jbbwebsolutions.stocks.model.Sector;

public class DriverSectorDAO {

	public static void main(String[] args) {

		
		IQuery<Sector> dao = new SectorDAO();
		dao.findAll().forEach(System.out::println);
		
	}

}
