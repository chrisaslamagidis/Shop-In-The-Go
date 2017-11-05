package com.shoponthego.offers;

import java.util.Date;

public class Offer {

	private String id;
	//TODO we can change the string state in order to make it an enumaration
	private String state;
	//TODO we can change the format date later
	private Date date;
	
	public Offer() {
		
	}
	
	public Offer(String givenState) {
		state = givenState;
		date = new Date();
	}

	public String getState() {
		return state;
	}

	public String getDate() {
		return date.toString();
	}

	public String getId() {
		return id;
	}
	
}
