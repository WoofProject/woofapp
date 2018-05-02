package com.enterprises.woof.woof;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Medications extends AppCompatActivity {

    private String medicationName;
    private String startDate;
    private String endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medications);
    }
}
