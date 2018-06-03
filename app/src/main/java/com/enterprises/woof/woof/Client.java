package com.enterprises.woof.woof;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class Client {
    static String name, email, password, dogName, dogBreed;

    public Client() {
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public void setPassword (String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public void setDogName (String dogName) {this.dogName = dogName;}
    public String getDogName() { return dogName; }

    public void setDogBreed (String dogBreed) {this.dogBreed = dogBreed;}
    public String getDogBreed() { return dogBreed; }


}
