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

}
