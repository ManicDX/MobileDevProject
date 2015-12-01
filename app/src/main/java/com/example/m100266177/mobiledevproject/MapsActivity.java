package com.example.m100266177.mobiledevproject;
/**
 * Made By 100486790
 * I made this to use the google API maps, they user can get directions by using an instance
 * of this map. In order for me to run this properly however I had to download GenyMotion and use
 * it as an emulator as I can install google play services on that. A sample map marker for UOIT
 * has been put onto the map and the camera zooms in towards it.
 */

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume(){
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        if (mMap == null){
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap(){
        mMap.addMarker(new MarkerOptions().position(new LatLng(43.9448, -78.8917)).title("UOIT"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(43.9448, -78.8917), 14.9f));
    }
}
