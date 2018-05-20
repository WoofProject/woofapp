package com.enterprises.woof.woof;

import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.net.*;

import javax.net.ssl.HttpsURLConnection;

public class Locator {

    public TrackimoDevice getDevice () {
        TrackimoDevice device = null;
        try {
            URL url = new URL("https://app.trackimo.com/api/v3/accounts/81361/locations/filter?limit=");


            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            String urlParameters = "";
            connection.setRequestProperty(" ", " ");
            connection.setRequestProperty(" ", " ");
            connection.setRequestProperty(" ", " ");
            connection.setRequestProperty(" ", " ");
            connection.setRequestProperty(" ", " ");
            connection.setRequestProperty(" ", " ");

            connection.setDoOutput(true);
            DataOutputStream dStream = new DataOutputStream(connection.getOutputStream());

            dStream.writeBytes(urlParameters);
            dStream.flush();
            dStream.close();


            String jsonObject = "";
            Gson gson = new Gson();
            device = gson.fromJson(jsonObject, TrackimoDevice.class);
        } catch (MalformedURLException e){}
        return device;
    }

}