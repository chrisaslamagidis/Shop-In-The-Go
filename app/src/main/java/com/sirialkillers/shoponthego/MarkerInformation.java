package com.sirialkillers.shoponthego;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by User on 13-Nov-17.
 */

public class MarkerInformation {
    LatLng markerPosition;
    String markerTitle;

    public MarkerInformation(LatLng markerPosition, String markerTitle){
        this.markerPosition=markerPosition;
        this.markerTitle=markerTitle;
    }


    public void setMarkerPosition(LatLng markerPosition) {
        this.markerPosition = markerPosition;
    }

    public void setMarkerTitle(String markerTitle) {
        this.markerTitle = markerTitle;
    }

    public LatLng getMarkerPosition() {
        return markerPosition;
    }

    public String getMarkerTitle() {
        return markerTitle;
    }
}
