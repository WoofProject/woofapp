package com.enterprises.woof.woof;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;

public class Appointments extends AppCompatActivity {

    private ArrayList<AppointmentObject> appointments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        findViewById(R.id.addAppointment).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String title = ((TextView)findViewById(R.id.appointmentTitle)).getText().toString();
                String time = ((TextView)findViewById(R.id.appointmentTime)).getText().toString();
                String date = ((TextView)findViewById(R.id.appointmentDate)).getText().toString();
                appointments.add(0, new AppointmentObject(title, date, time));
                AppointmentObject a = appointments.get(0);
                ((TextView)findViewById(R.id.currentAppointment)).setText(a.getTitle()+" on "+a.getDate()+" at "+a.getTime());

                ((TextView)findViewById(R.id.appointmentTitle)).setText("");
                ((TextView)findViewById(R.id.appointmentTime)).setText("");
                ((TextView)findViewById(R.id.appointmentDate)).setText("");

                Context context = getApplicationContext();
                CharSequence message = "Added to appointments";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, message, duration);
                toast.setGravity(Gravity.TOP, 0, 700);
                toast.show();

            }
        });

    }
}
