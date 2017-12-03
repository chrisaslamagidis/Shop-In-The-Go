package com.sirialkillers.shoponthego.Models;


import android.os.Parcel;
import android.os.Parcelable;

import com.sirialkillers.shoponthego.Interfaces.IDiscount;

import java.util.ArrayList;
import java.util.Date;


public class DiscountModel extends ArrayList implements IDiscount, Parcelable{
    private String shopId;
    private String discountId;
    private double percentage;
    private String title;
    private String description;
    private Date expirationDate;

    public DiscountModel(String shopId, String discountId, double percentage,
                         String title, String description){
        this.shopId = shopId;
        this.discountId = discountId;
        this.percentage = percentage;
        this.title = title;
        this.description = description;
    }

    @Override
    public String getShopId() {

        return shopId;
    }
    @Override
    public String getDiscountId() {

        return discountId;
    }

    @Override
    public double getPercentage() {

        return percentage;
    }

    @Override
    public String getTitle(){
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

    protected DiscountModel(Parcel in) {
        shopId = in.readString();
        discountId = in.readString();
        percentage = in.readDouble();
        title = in.readString();
        description = in.readString();
        long tmpExpirationDate = in.readLong();
        expirationDate = tmpExpirationDate != -1 ? new Date(tmpExpirationDate) : null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(shopId);
        dest.writeString(discountId);
        dest.writeDouble(percentage);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeLong(expirationDate != null ? expirationDate.getTime() : -1L);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<DiscountModel> CREATOR = new Parcelable.Creator<DiscountModel>() {
        @Override
        public DiscountModel createFromParcel(Parcel in) {
            return new DiscountModel(in);
        }

        @Override
        public DiscountModel[] newArray(int size) {
            return new DiscountModel[size];
        }
    };

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public void setDiscountId(String discountId) {
        this.discountId = discountId;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
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
}




