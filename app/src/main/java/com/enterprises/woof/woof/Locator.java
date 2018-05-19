package com.enterprises.woof.woof;


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Locator {

    JSONObject response = new JSONObject();

    private void getResponse (Context context) {

        String url = "https://app.trackimo.com/api/v3/accounts/81361/locations/filter?limit=";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("device_ids", "1098105");
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonResponse) {
                    response = jsonResponse;
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                       /*
                       ERROR CODE NEEDS TO BE ADDED
                        */
                       response = null;
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    final Map<String, String> headers = new HashMap<>();
                    headers.put("Authorization", "Bearer 5ede7f24-8ebe-4cf7-9452-b84aecfe56b6");
                    headers.put("Connection", "keep-alive");
                    headers.put("Content-Type","application/json; charset=UTF-8");
                    headers.put("Content-Length", "24");
                    headers.put("Cookie", "_ga=GA1.2.1062352619.1526505376; _gid=GA1.2.1138906047.1526505376; lc_sso8162611=1526505378619; JSESSIONID=FD794FAE44A03465E12EC3111357B8C1");
                    headers.put("Host", "app.trackimo.com");
                    headers.put("Referer", "https://app.trackimo.com/new_client/");
                    return headers;
                }
            };
            requestQueue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public TrackimoDevice getDevice (Context mContext) {
        getResponse(mContext);
        Log.d("trackimoTest", response.toString());
        Gson gson = new Gson();
//        String jsontext = "{\n" +
//                "\"age\": 86,\n" +
//                "\"altitude\": null,\n" +
//                "\"gps\": false,\n" +
//                "\"location_id\": -1,\n" +
//                "\"lat\": 51.527308,\n" +
//                "\"lng\": -0.115876,\n" +
//                "\"speed\": 0,\n" +
//                "\"battery\": 65,\n" +
//                "\"is_triangulated\": false,\n" +
//                "\"speed_unit\": null,\n" +
//                "\"device_id\": 1098105,\n" +
//                "\"time\": 1526506757,\n" +
//                "\"type\": \"gsm\"\n" +
//                "}\n";
//        TrackimoDevice device = gson.fromJson(jsontext.toString(), TrackimoDevice.class);
        TrackimoDevice device1 = gson.fromJson(response.toString(), TrackimoDevice.class);
        return device1;
//        return device;
    }


}
