package com.enterprises.woof.woof;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Maps extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng latlng;
    private LatLng initial;
    Locator locator = new Locator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        findViewById(R.id.goToLocation).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getLocation();
                mMap.addMarker(new MarkerOptions().position(latlng).title("Current loc of trackimo"));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 14), 5000, null);
            }
        });
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
        initial = new LatLng(-43.490377, 172.314774);
        // Add a marker in Sydney and move the camera
        // LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(initial).title("Current location of trackimo"));
        /*if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);*/
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(initial, 14), 5000, null);
    }

    public void getLocation () {
        locator.getResponse(this);


        TextView lat = this.findViewById(R.id.latitude2);
        TextView lng = this.findViewById(R.id.longitude2);

        Double deviceLat = Double.parseDouble(lat.getText().toString());
        Double deviceLng = Double.parseDouble(lng.getText().toString());

        latlng = new LatLng(deviceLat.doubleValue(), deviceLng.doubleValue());
    }


}
