package com.jbbwebsolutions.stocks.model;



public class Stock {
	
	private String symbol, name,category;
	private float netIncome;
	private float dividendYield;
	private Float price;
	private String sector;


	public Stock(String symbol, String name, String category, Float price) {
		super();
		this.symbol = symbol;
		this.name = name;
		this.category = category;
		this.price = price;
	}
	
	public Stock(String symbol, Float price, String category, float netIncome, float dividendYield, String sector) {
		super();
		this.symbol = symbol;
		//this.name = name;
		this.category = category;
		this.netIncome = netIncome;
		this.dividendYield = dividendYield;
		this.price = price;
		this.sector = sector;
	}

	
	
	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public float getNetIncome() {
		return netIncome;
	}


	public void setNetIncome(int netIncome) {
		this.netIncome = netIncome;
	}


	public float getDividendYield() {
		return dividendYield;
	}


	public void setDividendYield(float dividendYield) {
		this.dividendYield = dividendYield;
	}


	public String getCategory() {
		return category;
	}



	public void setCategory(String category) {
		this.category = category;
	}



	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	

	
	@Override
	public String toString() {
		return "Stock [symbol=" + symbol + ", name=" + name + ", category=" + category +  ", price=" + price + "]";
	}

	
	
	
}
