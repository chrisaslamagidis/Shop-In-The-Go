package com.sirialkillers.shoponthego;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.SphericalUtil;
import com.sirialkillers.shoponthego.Controllers.ShopController;
import com.sirialkillers.shoponthego.Models.ShopModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by Xristos Aslamagidis on 29/10/2017.
 */
public class ListOfShops {

    private List<ShopModel> shops = new ArrayList<>();
    private ArrayList<LatLng> shopsLocation =new ArrayList<LatLng>();
    private ArrayList<Marker>markers =new ArrayList<Marker>();
    private ArrayList<MarkerInformation> markerinfo=new ArrayList<>();
    ShopController shopController;

    public void addShop() {

        shops=shopController.get();
    }

    public List<ShopModel> getShop(){

        return shops;
    }


    public void creatPositionOfShop(){
        for (ShopModel shop:shops) {
            LatLng position =new LatLng(shop.getPosition().getLatitude(),shop.getPosition().getLongitude());
            String name=shop.getName();
            markerinfo.add(new MarkerInformation(position, name));

        }
    }

    //Used to find the correct shopName from the shopId(currently not implemented)
    public String getShopName(String shopId){
        String id=shopId;
        String name=null;
        String s;
        for(ShopModel shop:shops){
            s=shop.getId();
            if(Objects.equals(id,s)){
                name=shop.getName();
            }
        }
        return name;
    }

    //used to find the correct shopId from the title of marker
    // (which is always the name of a shop in the Arraylist shops)
    public String findCorrectShop(String title,List<ShopModel> shops){
        String name=title;
        String s;
        String id=null;
        for (ShopModel shop:shops){
            s=shop.getName();
            if(Objects.equals(name, s)){
                id=shop.getId();
            }
        }
        if(id==null){
            id="Not Found";
        }
        return id;

    }

    public ArrayList<Marker> creatMarkerOfShop(GoogleMap googleMap){
        this.addShop();
        this.creatPositionOfShop();

        for (MarkerInformation m:markerinfo) {

            markers.add( googleMap.addMarker(new MarkerOptions().position(m.getMarkerPosition()).title(m.getMarkerTitle())));

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
