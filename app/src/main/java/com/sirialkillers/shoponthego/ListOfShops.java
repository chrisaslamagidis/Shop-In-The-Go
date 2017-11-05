package com.sirialkillers.shoponthego;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.SphericalUtil;

import java.util.ArrayList;

/**
 * Created by Xristos Aslamagidis on 29/10/2017.
 */

public class ListOfShops {

    private ArrayList<Shop> shops = new ArrayList<Shop>();
    private ArrayList<LatLng> shopsLocation =new ArrayList<LatLng>();
    private ArrayList<Marker>markers =new ArrayList<Marker>();


    public void addShop() {

        //polixnh
        shops.add(new Shop("rafaele", 40.6657785, 22.9468865));
        shops.add(new Shop("mavidis", 40.6666259,22.9455427));
        shops.add(new Shop("porkys", 40.663449,22.9475822));
        shops.add(new Shop("seven", 40.6595399,22.9445063));
        shops.add(new Shop("mad gym",40.6566813,22.9328894));

        //serres
        shops.add(new Shop("Driving School",41.078405,23.5492328));
        shops.add(new Shop("Alldayserres",41.0799818,23.5429958));
        shops.add(new Shop("Ktel",41.0778696,23.5463639));

    }


    public void creatPositionOfShop(){
        for (Shop shop:shops) {
            LatLng position =new LatLng(shop.getLatitude(),shop.getLongtitude());
            shopsLocation.add(position);

        }
    }

    public ArrayList<Marker> creatMarkerOfShop(GoogleMap googleMap){
        this.addShop();
        this.creatPositionOfShop();

        for (LatLng shopLocation:shopsLocation) {

            markers.add( googleMap.addMarker(new MarkerOptions().position(shopLocation)));

        }

        return markers;
    }


    public void ShowShopsMarkersInUserLocationRadious(LatLng location,int seekBarProgress){


        for (Marker marker :markers) {
            if (SphericalUtil.computeDistanceBetween(location,marker.getPosition())<seekBarProgress ) {
                marker.setVisible(true);
            }else{
                marker.setVisible(false);
            }

        }
    }

}
