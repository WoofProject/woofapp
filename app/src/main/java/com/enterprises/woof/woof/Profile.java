package com.enterprises.woof.woof;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    FragmentClient user = new FragmentClient();

    private String userName;
    private int numberofdogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custompopup);

        userName = user.getName();
        ((TextView)findViewById(R.id.userNameP)).setText(userName);
    }
}
