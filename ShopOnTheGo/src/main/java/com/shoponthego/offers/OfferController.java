package com.shoponthego.offers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OfferController {

	@Autowired
	private OfferService offerService;
	
	@RequestMapping("/offers")
	public List<Offer> getAllOffers() {
		return offerService.getAllOffers();
	}
	
	@RequestMapping("/offers/{id}")
	public Offer getOfferById(@PathVariable String id) {
		return offerService.getOfferById(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/offers")
	public void addOffer(@RequestBody Offer offer) {
		offerService.addOffer(offer);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/offers/{id}")
	public void updateOffer(@RequestBody Offer offer, @PathVariable String id) {
		offerService.updateOffer(id, offer);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/offers/{id}")
	public void deleteOffer(@PathVariable String id) {
		offerService.deleteOffer(id);
	}
	
}
