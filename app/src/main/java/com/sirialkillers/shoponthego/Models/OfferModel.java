package com.sirialkillers.shoponthego.Models;

import com.sirialkillers.shoponthego.Interfaces.IOffer;
import com.sirialkillers.shoponthego.Interfaces.IProduct;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Ioakeim James Theologou
 * @version 16/10/2017
 * TODO: Create a test class for this model.
 *
 */
public class OfferModel extends ArrayList implements IOffer{
    private String shopId;
    private String offerId;
    private String title;
    private String description;
    private Date expirationDate;
    private List<IProduct> products;


    public OfferModel(String shopId, String offerId, String title, String description){
        this.shopId = shopId;
        this.offerId = offerId;
        this.title = title;
        this.description = description;

    }
    @Override
    public String getShopId() {

        return shopId;
    }

    @Override
    public String getOfferId() {

        return offerId;
    }

    @Override
    public List<IProduct> getProducts() {

        return products;
    }

    @Override
    public String getTitle()
    {
        return title;
    }

    @Override
    public String getDescription() {

        return description;
    }

    @Override
    public Date getExpirationDate() {

        return expirationDate;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setProducts(List<IProduct> products) {
        this.products = products;
    }
}
