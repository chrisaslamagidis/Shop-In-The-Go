package com.shoponthego.offers;

import java.util.ArrayList;
import java.util.List;

public class OfferService {

	private List<Offer> offers = new ArrayList<>();
	
	public List<Offer> getAllOffers() {
		return offers;
	}

	public Offer getOfferById(String id) {
		for (Offer offer : offers) {
			if(offer.getId().equalsIgnoreCase(id)) {
				return offer;
			}
		}
		return null;
	}

	public void addOffer(Offer offer) {
		offers.add(offer);
	}

	public void updateOffer(String id, Offer newOffer) {
		for(int i=0; i<offers.size(); i++) {
			Offer offer = offers.get(i);
			if(offer.getId().equalsIgnoreCase(id)) {
				offers.set(i, newOffer);
				return;
			}
		}		
	}

	public void deleteOffer(String id) {
		for(int i=0; i<offers.size(); i++) {
			Offer offer = offers.get(i);
			if(offer.getId().equalsIgnoreCase(id)) {
				offers.remove(offer);
				return;
			}
		}		
	}
}
