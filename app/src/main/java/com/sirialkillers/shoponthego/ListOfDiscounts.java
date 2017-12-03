package com.sirialkillers.shoponthego;


import com.sirialkillers.shoponthego.Controllers.ShopController;
import com.sirialkillers.shoponthego.Models.DiscountModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 16-Nov-17.
 */

public class ListOfDiscounts{
    List<DiscountModel> discountlist=new ArrayList<>();
    ShopController shopController;

    public void addDiscounts(String shopId){
        discountlist=shopController.getShopDiscounts(shopId);
    }

    public List<DiscountModel> getDiscountlist() {

        return discountlist;
    }



}