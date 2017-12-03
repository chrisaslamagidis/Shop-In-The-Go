package com.sirialkillers.shoponthego;

public class Shop {

    private String name;
    private Double latitude;
    private Double longtitude;

    public Shop(String name, Double latitude, Double longtitude) {
        this.name = name;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    public String getName() {

        return name;
    }

    public Double getLatitude() {

        return latitude;
    }

    public Double getLongtitude() {

        return longtitude;
    }
}
