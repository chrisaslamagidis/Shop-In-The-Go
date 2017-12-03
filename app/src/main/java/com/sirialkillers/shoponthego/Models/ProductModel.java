package com.sirialkillers.shoponthego.Models;

import com.sirialkillers.shoponthego.Interfaces.IProduct;

import java.util.ArrayList;

/**
 * @author Ioakeim James Theologou
 * @version 16/11/2017
 * TODO: Create a test class for this model.
 *
 */
public class ProductModel extends ArrayList implements IProduct {
    private String productName;
    private String productId;
    private int quantityPerUnit;
    private long unitPrice;

    public ProductModel(String productId, String productName){
        this.productId = productId;
        this.productName = productName;
    }
    @Override
    public String getProductName() {

        return productName;
    }

    @Override
    public String getProductId() {

        return productId;
    }

    @Override
    public int getQuantityPerUnit() {

        return quantityPerUnit;
    }

    @Override
    public long getUnitPrice() {

        return unitPrice;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setQuantityPerUnit(int quantityPerUnit) {
        this.quantityPerUnit = quantityPerUnit;
    }

    public void setUnitPrice(long unitPrice) {
        this.unitPrice = unitPrice;
    }
}
