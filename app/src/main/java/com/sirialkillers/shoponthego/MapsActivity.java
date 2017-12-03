package com.sirialkillers.shoponthego;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.sirialkillers.shoponthego.Models.ShopModel;

import net.hockeyapp.android.CrashManager;
import net.hockeyapp.android.UpdateManager;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,OnInfoWindowClickListener {
    private int Loadtime = 2000; //2 seconds
    private GoogleMap mMap;
    private BroadcastReceiver broadcastReceiver;
    int realProgress = 750;  //This will be the radius of the circle in which we can see the shops of the map
    ListOfShops listOfShops;
    ArrayList<Marker> markersofShops;
    SeekBar rangeControlSeekBar;
    TextView radiusDisplayTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Button menu = (Button) findViewById(R.id.menuButton);
        if (!runtime_perimissions()) {
            Intent intent = new Intent(getApplicationContext(), GPS_Service.class);
            startService(intent);
        }

        //Initializing the seekbar that controls the radius in which the user can see the shop. Also a textView that will display the meters and two progress Bars for loading the maps.
        rangeControlSeekBar = (SeekBar) findViewById(R.id.viewingRangeControlBar);
        radiusDisplayTextView = (TextView) findViewById(R.id.radiusTextView);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMenu();
            }
        });
        this.configureRangeControlSeekBar();
        this.onChangeRangeControlSeekBar();
        listOfShops = new ListOfShops();

       this.Loading();

        checkForUpdates(); //Used for HockeyApp
    }

    //CrashReporting and Beta-Distribution for HockeyApp.
    @Override
    public void onResume() {
        super.onResume();

        if (broadcastReceiver == null) {
            broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {

                    LatLng userLocation = new LatLng(intent.getExtras().getDouble("Lat"), intent.getExtras().getDouble("Long"));
                    listOfShops.ShowShopsMarkersInUserLocationRadious(userLocation, realProgress);

                }
            };
            registerReceiver(broadcastReceiver, new IntentFilter("location update"));
        }
        // ... your own onResume implementation
        checkForCrashes();
    }

    // Simulates the loading of the maps
    public void Loading(){
        final ProgressBar progressBar =(ProgressBar)findViewById(R.id.progressBar);
        final ProgressBar progressBarSmall = (ProgressBar)findViewById(R.id.progressBarSmall);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBarSmall.setVisibility(View.INVISIBLE);
                radiusDisplayTextView.setVisibility(View.VISIBLE);
                rangeControlSeekBar.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);

            }
        },Loadtime);

    }
    //CrashReporting and Beta-Distribution for HockeyApp.
    @Override
    public void onPause() {
        super.onPause();
        unregisterManagers();
    }

    //CrashReporting and Beta-Distribution for HockeyApp.
    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterManagers();
    }

    //CrashReporting and Beta-Distribution for HockeyApp.
    private void checkForCrashes() {
        CrashManager.register(this);
    }

    //CrashReporting and Beta-Distribution for HockeyApp.
    private void checkForUpdates() {
        // Remove this for store builds!
        UpdateManager.register(this);
    }

    //CrashReporting and Beta-Distribution for HockeyApp.
    private void unregisterManagers() {
        UpdateManager.unregister();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    public void configureRangeControlSeekBar() {
        //Setting the maxumum range of the radius to 1500 meters and the (starting) current progress to 750 meters.
        rangeControlSeekBar.setMax(1400);
        rangeControlSeekBar.setProgress(700);

        //need API level 26 to implement the minimum Range of 100 meters
        //If we had it, it would be like this: rangeControlSeekBar.setMin(100);
        //but now we use the realProgress int in the seekBarListener to do the same thing.

    }

    public void onChangeRangeControlSeekBar() {

        //Initializing a SeekBar Listener to get the range/radius Values.
        rangeControlSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Updating the Textview for the radius as the seekbar progress changes
                //We use realProgress to make the minimum ammount of meters to 100;
                realProgress = progress + 100;
                radiusDisplayTextView.setText("Your current radius is: " + Integer.toString(realProgress) + " meters.");


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        markersofShops = listOfShops.creatMarkerOfShop(mMap);
        mMap.setOnInfoWindowClickListener(this);
       


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        mMap.setMyLocationEnabled(true);




    }
    public void goToMenu(){
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(intent);
    }

    public void goToDiscounts(String id, String shopName){
        Intent i = new Intent (getApplicationContext(), DiscountListView.class);
        i.putExtra("name", shopName);
        i.putExtra("message", id);
        startActivity(i);
    }

    @Override
    public void onInfoWindowClick(Marker marker){
        String shopName = marker.getTitle();
        String id=listOfShops.findCorrectShop(shopName, listOfShops.getShop());
        goToDiscounts(id,shopName);


    }

    private boolean runtime_perimissions() {
        if(Build.VERSION.SDK_INT>=23 && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){

            requestPermissions(new  String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},100);
            return true;

        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(getApplicationContext(), GPS_Service.class);
                startService(intent);
            } else {
                runtime_perimissions();
            }

        }
    }
}