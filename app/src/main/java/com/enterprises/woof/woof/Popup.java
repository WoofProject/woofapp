package com.enterprises.woof.woof;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Popup extends AppCompatActivity{

    Client c = new Client();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custompopup);

        ((TextView)findViewById(R.id.userNameP)).setText(c.getName());

    }

}
