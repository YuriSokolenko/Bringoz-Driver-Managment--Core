package com.bringoz.driverscore.model;


public enum DriverStatus {

	 ACTIVE("ACTIVE"),
	 INACTIVE("INACTIVE"), 
	 DELIVERING("DELIVERING");
 
    private String name;
 
    
    private DriverStatus (String name) {
        this.name = name;
    }

	public String getName() {
		return name;
	}
}
