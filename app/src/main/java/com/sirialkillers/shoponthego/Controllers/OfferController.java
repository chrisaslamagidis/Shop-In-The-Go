package com.sirialkillers.shoponthego.Controllers;

import android.util.Log;

import com.sirialkillers.shoponthego.Interfaces.IController;
import com.sirialkillers.shoponthego.Models.OfferModel;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ioakeim James Theologou
 * @version 12/11/2017
 *
 */
public class OfferController implements IController<OfferModel, String> {
    /* Rest Template is a template that is given by Spring Framework */
    private RestTemplate restTemplate;

    /* params is a hash map used to set the parameters that are going to be used
     in the Rest Template */
    private Map<String, String> params;

    /**
     * Initializes the Rest template and adds a
     * Jackson message converter so it can parse
     * a JSON file.
     */
    public OfferController() {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        params = new HashMap<>();
    }

    /**
     * Returns a list of offers that are accessible for further use.
     * @return the list of offers
     */
    @Override
    public List<OfferModel> get(){
        try {
            final String url = "http://localhost:8080/offers";
            return restTemplate.getForObject(url, OfferModel.class);
        }catch (Exception e){
            Log.e("getOffers", e.getMessage(),e);
        }
        return null;
    }

    /**
     * Returns a offer that matches the offerId.
     * @param offerId The id of the offer
     * @return the offer
     */
    @Override
    public OfferModel getById(String offerId){
        try {
            final String url = "http://localhost:8080/offers/{id}";
            params.clear();
            params.put("id", offerId);
            return restTemplate.getForObject(url, OfferModel.class, params);
        }catch (Exception e){
            Log.e("getOfferById", e.getMessage(),e);
        }
        return null;
    }

    /**
     * Creates a new offer.
     * @param offer is the offer that will be created
     * @return the offer that was created
     */
    @Override
    public OfferModel create(OfferModel offer){
        try {
            final String url = "http://localhost:8080/offers";
            return restTemplate.postForObject(url, offer, OfferModel.class);
        }catch (Exception e){
            Log.e("CreateOffer", e.getMessage(),e);
        }
        return null;
    }

    /**
     * Updates a already existing offer.
     * @param offer is the offer that will get updated.
     */
    @Override
    public void update(String targetOffer, OfferModel offer){
        try {
            final String url = "http://localhost:8080/offers/{targetOffer}";
            params.clear();
            params.put("targetOffer", targetOffer);
            restTemplate.put(url, offer, params);
        }catch (Exception e){
            Log.e("updateOffer", e.getMessage(),e);
        }
    }

    /**
     * Deletes a offer that already exists.
     * @param offerId the id of the offer that will get deleted
     */
    @Override
    public void delete(String offerId){
        try {
            final String url = "http://localhost:8080/offers/{id}";
            params.put("id", offerId);
            restTemplate.delete(url, params);
        }catch (Exception e){
            Log.e("deleteOffer", e.getMessage(),e);
        }
    }
}
