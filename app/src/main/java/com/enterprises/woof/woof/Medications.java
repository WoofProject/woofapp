package com.enterprises.woof.woof;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Medications extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medications);

        findViewById(R.id.addMedication).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ((TextView)findViewById(R.id.addNewMedication)).setText("gucci gang");
            }
        });
    }

}
