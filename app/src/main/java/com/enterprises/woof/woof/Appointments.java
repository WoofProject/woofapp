package com.enterprises.woof.woof;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Appointments extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        findViewById(R.id.addAppointment).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ((TextView)findViewById(R.id.addNewAppointment)).setText("candy paint");
            }
        });

    }
}
