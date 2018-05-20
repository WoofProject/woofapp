package com.enterprises.woof.woof;


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Locator {

    JSONArray response1 = new JSONArray();
    JSONObject obj = new JSONObject();


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

                    Double lat = obj.getDouble("lat");
                    Double lng = obj.getDouble("lng");

                Activity activity = (Activity) context;
                ((TextView)activity.findViewById(R.id.latitude2)).setText(lat.toString());
                ((TextView)activity.findViewById(R.id.longitude2)).setText(lng.toString());

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
                    headers.put("Content-Type","application/json; charset=UTF-8");
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

    public TrackimoDevice getDevice (Context mContext) {
        getResponse(mContext);

        Double lat = 0.0;
        Double lng = 0.0;

        try {
            obj = (JSONObject) response1.get(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("trackimo", obj.toString());

        TrackimoDevice device1 = new TrackimoDevice();



        Log.d("trackimoTestLocation", Double.toString(lat));
        Log.d("trackimoTestLocation", Double.toString(lng));

        device1.setLat(lat);
        device1.setLng(lng);


        return device1;
//        return device;
    }

}






