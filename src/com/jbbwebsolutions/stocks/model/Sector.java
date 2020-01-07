package com.jbbwebsolutions.stocks.model;

public class Sector {
	
	private String sector, sectorDescription;

	public Sector(String sector, String sectorDescription) {
		super();
		this.sector = sector;
		this.sectorDescription = sectorDescription;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getSectorDescription() {
		return sectorDescription;
	}

	public void setSectorDescription(String sectorDescription) {
		this.sectorDescription = sectorDescription;
	}

	@Override
	public String toString() {
		return "Sector [sector=" + sector + ", sectorDescription=" + sectorDescription + "]";
	}
	
	


	
}
