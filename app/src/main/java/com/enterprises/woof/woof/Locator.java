package com.enterprises.woof.woof;

import com.google.gson.Gson;

public class Locator {

    public TrackimoDevice getDevice (String url) {
        /*
        get request
         */
        String jsonObject = "";
        Gson gson = new Gson();
        TrackimoDevice device = gson.fromJson(jsonObject, TrackimoDevice.class);
        return device;
    }
}
