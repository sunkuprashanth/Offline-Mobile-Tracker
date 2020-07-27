package com.example.offlinemobiletracker;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.StringTokenizer;

public class Locate_device extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    //Button locate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locate_device);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //locate=findViewById(R.id.locate);
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
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        //mMap.setMinZoomPreference(18);
        //mMap.setMaxZoomPreference(5);

        Intent in = getIntent();
        String locate = in.getStringExtra("locate");
        Toast.makeText(this, locate, Toast.LENGTH_SHORT).show();
        StringTokenizer st = new StringTokenizer(locate,",");
        LatLng marker = new LatLng(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
        mMap.addMarker(new MarkerOptions().position(marker).title("Device location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));

        UiSettings mUiSet = mMap.getUiSettings();
        mUiSet.setAllGesturesEnabled(true);
        mUiSet.setMyLocationButtonEnabled(true);

    }
    public void locate(View v) {
        startActivity(new Intent(this,Track.class));        //for testing
    }
}