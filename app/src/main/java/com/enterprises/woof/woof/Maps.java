package com.enterprises.woof.woof;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Maps extends FragmentActivity implements OnMapReadyCallback {

    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader = new ArrayList<>();
    private HashMap<String, List<String>> listHash = new HashMap<>();
    List<String> dropDowns = new ArrayList<>();
    List<String> dropDowns2 = new ArrayList<>();

    private GoogleMap mMap;
    private LatLng latlng;


    JSONArray response1 = new JSONArray();
    JSONObject obj = new JSONObject();
    MarkerOptions a = new MarkerOptions()
            .position(new LatLng(0, 0));
    Marker m;
    Double lat;
    Double lng;
    int PROXIMITY_RADIUS = 1000;

    CurrentUser user = new CurrentUser(this);


    private String getUrl(double latitude, double longitude, String nearbyPlace) {
        StringBuilder googlePlaceUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlaceUrl.append("location=" + latitude + "," + longitude);
        googlePlaceUrl.append("&radius=" + PROXIMITY_RADIUS);
        googlePlaceUrl.append("&keyword=" + nearbyPlace);
        googlePlaceUrl.append("&sensor=true");
        googlePlaceUrl.append("&key=AIzaSyBJKMaCC5NKpNnKU5K2cw6OR4OHzlE4pTw");

        return googlePlaceUrl.toString();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        listView = (ExpandableListView) findViewById(R.id.expandableView);
        initData();
        listAdapter = new ExpandableAdapter(this, listDataHeader, listHash);
        listView.setAdapter(listAdapter);
        listView.expandGroup(0);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        findViewById(R.id.goToLocation).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getLocation();
            }
        });
        findViewById(R.id.findParks).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String park = "park";
                String url = getUrl(lat, lng, park);
                Object dataTransfer[] = new Object[2];
                dataTransfer[0] = mMap;
                dataTransfer[1] = url;

                GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
                getNearbyPlacesData.execute(dataTransfer);
                Toast.makeText(Maps.this, "Showing the parks that are within walking distance in your area", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putDouble("latitudeValue", lat);
        savedInstanceState.putDouble("longitudeValue", lng);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        lat = savedInstanceState.getDouble("latitudeValue");
        lng = savedInstanceState.getDouble("longitudeValue");
    }


    private void initData() {
        listDataHeader.add(user.getDogName() + "'s Information");
        listDataHeader.add("Other Information");

        dropDowns.add("Email: "+user.getEmail());
        dropDowns.add("Name: "+user.getDogName());
        dropDowns.add("Breed: "+user.getDogBreed());

        dropDowns2.add("Current Location: \n" + lat + ", " + lng);
        dropDowns2.add("Time last taken of location: ");
        dropDowns2.add("Speed of recent recording: ");
        dropDowns2.add("GPS: ");

        listHash.put(listDataHeader.get(0), dropDowns);
        listHash.put(listDataHeader.get(1), dropDowns2);
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
        LatLng temp = new LatLng(user.getLatStatus(), user.getLngStats());
        lat = user.getLatStatus();
        lng = user.getLngStats();
        mMap = googleMap;
        a = new MarkerOptions().position(new LatLng(user.getLatStatus(), user.getLngStats()));
        m = mMap.addMarker(a);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(temp, 18f), 2500, null);
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
        // Add a marker in Sydney and move the camera
        // LatLng sydney = new LatLng(-34, 151);
    }

    public void getLocation() {
        getResponse(this);
    }


    public void getResponse(final Context context) {
        String url = "https://app.trackimo.com/api/v3/accounts/81361/locations/filter?limit=";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        try {
            final JSONObject jsonObject = new JSONObject("{\"device_ids\":[1098105]}");
            CustomRequest jsonObjectRequest = new CustomRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    boolean gpsMode = false;
                    String gpsMessage = "";

                    response1 = response;
                    Log.d("trackimo", response1.toString());
                    try {
                        obj = (JSONObject) response1.get(0);
                        gpsMode = obj.getBoolean("gps");
                        if (!gpsMode) {
                            gpsMessage = "False. Location may not be accurate as device is triangulating data";
                        } else {
                            gpsMessage = "True, Location should be accurate.";
                        }
                        lat = obj.getDouble("lat");
                        lng = obj.getDouble("lng");
                        user.setLocationStatus(lat, lng);
                        latlng = new LatLng(lat, lng);

                        Date currentTime = Calendar.getInstance().getTime();
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm               dd/mm/yyyy");
                        String currentDateandTime = sdf.format(currentTime);

                        listHash.clear();
                        dropDowns.set(0, "Email: " + user.getEmail());
                        dropDowns.set(1, "Name: " + user.getDogName());
                        dropDowns.set(2, "Breed: " + user.getDogBreed());
                        dropDowns2.set(0, "Current Location: \n" + lat + ", " + lng);
                        dropDowns2.set(1, "Time last taken of location: \n" + currentDateandTime);
                        dropDowns2.set(2, "Speed of last recording: \n" + obj.getDouble("speed"));
                        dropDowns2.set(3, "GPS: \n" + gpsMessage);

                        listHash.put(listDataHeader.get(0), dropDowns);
                        listHash.put(listDataHeader.get(1), dropDowns2);


                        listAdapter = new ExpandableAdapter(context, listDataHeader, listHash);
                        listView.setAdapter(listAdapter);
                        listView.expandGroup(0);
                        m.setPosition(latlng);
                        m.setTitle(user.getDogName()+ "'s current location");
                        Toast.makeText(Maps.this, "Showing " + user.getDogName() + "'s location", Toast.LENGTH_LONG).show();
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 18f), 2500, null);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                       /*
                       ERROR CODE NEEDS TO BE ADDED
                        */
                    response1 = null;
                    error.printStackTrace();
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    final Map<String, String> headers = new HashMap<>();
                    headers.put("Authorization", "Bearer 5ede7f24-8ebe-4cf7-9452-b84aecfe56b6");
                    headers.put("Content-Type", "application/json; charset=UTF-8");
                    headers.put("Host", "app.trackimo.com");
                    headers.put("Referer", "https://app.trackimo.com/new_client/");
                    return headers;
                }
            };
            Log.d("trackimoTest", jsonObjectRequest.toString());
            requestQueue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

