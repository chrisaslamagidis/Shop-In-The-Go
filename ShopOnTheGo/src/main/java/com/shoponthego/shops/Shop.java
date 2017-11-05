package com.shoponthego.shops;


public class Shop {

	private String id;
	private String name;
	
	public Shop() {
		
	}
	
	public Shop(String givenId, String givenName) {
		id = givenId;
		name = givenName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
