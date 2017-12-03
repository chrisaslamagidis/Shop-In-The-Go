package com.sirialkillers.shoponthego.Models;

import com.sirialkillers.shoponthego.Interfaces.ICategory;
import com.sirialkillers.shoponthego.Interfaces.IDiscount;
import com.sirialkillers.shoponthego.Interfaces.IOffer;
import com.sirialkillers.shoponthego.Interfaces.IProduct;
import com.sirialkillers.shoponthego.Interfaces.IShop;
import com.sirialkillers.shoponthego.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ioakeim James Theologou
 * @version 16/10/2017
 * TODO: Create a test class for this model.
 *
 */

public class ShopModel extends ArrayList implements IShop {
    private String id;
    private String name;
    private Position position;
    private List<IDiscount> discounts;
    private List<IOffer> offers;
    private List<IProduct> products;
    private List <ICategory> categories;

    public ShopModel(String id, String name, Position position){
        this.id = id;
        this.name = name;
        this.position = position;

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

    public Position getPosition() {

        return position;
    }

    public void setPosition(Position position) {

        this.position = position;
    }

    @Override
    public List<IOffer> getOffers() {

        return offers;
    }

    @Override
    public IOffer getOffer(String offerId) {

        return null;
    }

    @Override
    public List<IProduct> getAllProducts() {

        return products;
    }

    @Override
    public IProduct getProduct(String productId) {

        return null;
    }

    @Override
    public List<IDiscount> getAllDiscounts() {

        return discounts;
    }

    @Override
    public IDiscount getDiscount(String discountId) {

        return null;
    }

    public void setDiscounts(List<IDiscount> discounts) {
        this.discounts = discounts;
    }

    public void setOffers(List<IOffer> offers) {
        this.offers = offers;
    }

    public void setProducts(List<IProduct> products) {
        this.products = products;
    }

    public void setCategories(List<ICategory> categories) {
        this.categories = categories;
    }
}
