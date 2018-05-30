package com.enterprises.woof.woof;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Popup extends AppCompatActivity{

    DatabaseHelper helper = new DatabaseHelper(getApplication());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custompopup);
    }

}
