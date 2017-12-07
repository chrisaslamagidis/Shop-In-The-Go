package com.sirialkillers.shoponthego.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.sirialkillers.shoponthego.Interfaces.ICategory;
import com.sirialkillers.shoponthego.Interfaces.IDiscount;
import com.sirialkillers.shoponthego.Interfaces.IOffer;
import com.sirialkillers.shoponthego.Interfaces.IProduct;
import com.sirialkillers.shoponthego.Interfaces.IShop;
import com.sirialkillers.shoponthego.Maps_Related_Activities.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ioakeim James Theologou
 * @version 16/10/2017
 * TODO: Create a test class for this model.
 *
 */
@Entity(tableName="Shop")
public class ShopModel extends ArrayList implements IShop {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name="shopId")
    private String id;
    @ColumnInfo(name="shopName")
    private String name;
    @Embedded
    private Position position;
    @Ignore
    private List<IDiscount> discounts;
    @Ignore
    private List<IOffer> offers;
    @Ignore
    private List<IProduct> products;
    @Ignore
    private List <CategoryModel> categories;


    public ShopModel(String id, String name, Position position){
        this.id = id;
        this.name = name;
        this.position = position;
    }
    @Ignore
    public ShopModel(String id, String name){
        this.id = id;
        this.name = name;
        this.position = new Position(0,0);
    }
    @Ignore
    public ShopModel(String id){
        this.id = id;
        this.name = "";
        this.position = new Position(0,0);
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

    public void setCategories(List<CategoryModel> categories) {

        this.categories = categories;
    }
}
