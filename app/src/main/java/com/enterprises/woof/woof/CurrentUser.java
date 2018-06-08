package com.enterprises.woof.woof;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

public final class CurrentUser {
    DatabaseHelper helper;

    private static String name;
    private static String email;
    private static String password;
    private static String dogName;
    private static String dogBreed;
    private static double lat = 0.0;
    private static double lng = 0.0;

    public CurrentUser(Context context) {
        helper = new DatabaseHelper(context);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        name = helper.searchName(email);
        return name;
    }

    public String getDogName() {
        dogName = helper.searchDog(email);
        return dogName;
    }

    public String getDogBreed() {
        dogBreed = helper.searchDogBreed(email);
        return dogBreed;
    }

    public void setLocationStatus(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public double getLatStatus() {
        return lat;
    }
    public double getLngStats() {
        return lng;
    }

}
