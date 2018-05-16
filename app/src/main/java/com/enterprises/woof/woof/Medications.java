package com.enterprises.woof.woof;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Medications extends AppCompatActivity {

    private ArrayList<MedicationObject> medications = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medications);

        findViewById(R.id.addMedication).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String title = ((TextView)findViewById(R.id.medicationTitle)).getText().toString();
                String startDate = ((TextView)findViewById(R.id.medicationStart)).getText().toString();
                String endDate = ((TextView)findViewById(R.id.medicationEnd)).getText().toString();
                medications.add(0, new MedicationObject(title, startDate, endDate));
                MedicationObject m = medications.get(0);
                ((TextView)findViewById(R.id.currentMedication)).setText(m.getTitle()+" from "+m.getStartDate()+" to "+m.getEndDate());

                ((TextView)findViewById(R.id.medicationTitle)).setText("");
                ((TextView)findViewById(R.id.medicationStart)).setText("");
                ((TextView)findViewById(R.id.medicationEnd)).setText("");

                Context context = getApplicationContext();
                CharSequence message = "Added to medications";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, message, duration);
                toast.setGravity(Gravity.TOP, 0, 700);
                toast.show();
            }
        });
    }

}
