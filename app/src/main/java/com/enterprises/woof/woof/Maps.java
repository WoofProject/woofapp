package com.enterprises.woof.woof;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.DataSetObserver;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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
            .position(new LatLng(0,0));
    Marker m;
    Double lat;
    Double lng;

    CurrentUser user = new CurrentUser(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        listView = (ExpandableListView)findViewById(R.id.expandableView);
        initData();
        listAdapter = new ExpandableAdapter(this, listDataHeader, listHash);
        listView.setAdapter(listAdapter);

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
        listDataHeader.add(user.getDogName() + "'s Information:");
        listDataHeader.add("OTHER INFORMATION");

        dropDowns.add("Current Location: \n" + lat + ", " + lng);
        dropDowns.add("Time last taken of location: " );
        dropDowns.add("Speed of recent recording: ");

        dropDowns2.add("Hello");
        dropDowns2.add("Hello there");
        dropDowns2.add("Hello, hi whats up");

        listHash.put(listDataHeader.get(0), dropDowns);
        listHash.put(listDataHeader.get(1), dropDowns2);
    }

    private void editData() {
        listHash.clear();
        dropDowns.set(0, "Current Location: \n" + lat + ", " + lng);
        dropDowns.set(1, "Time last taken of location: ");
        dropDowns.set(2, "Speed of recent recording: ");

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
        mMap = googleMap;
        m = mMap.addMarker(a);
        // Add a marker in Sydney and move the camera
        // LatLng sydney = new LatLng(-34, 151);
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
    }

    public void getLocation () {
        getResponse(this);
    }




    public void getResponse (final Context context) {
        String url = "https://app.trackimo.com/api/v3/accounts/81361/locations/filter?limit=";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        try {
            final JSONObject jsonObject = new JSONObject("{\"device_ids\":[1098105]}");
            CustomRequest jsonObjectRequest = new CustomRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    response1 = response;
                    Log.d("trackimo", response1.toString());
                    try {
                        obj = (JSONObject) response1.get(0);
                        lat = obj.getDouble("lat");
                        lng = obj.getDouble("lng");
                        latlng = new LatLng(lat, lng);

                        editData();
                        listAdapter = new ExpandableAdapter(context, listDataHeader, listHash);
                        listView.setAdapter(listAdapter);
                        m.setPosition(latlng);
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
