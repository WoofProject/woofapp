package com.enterprises.woof.woof;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

import java.util.ArrayList;

public class Appointments extends AppCompatActivity {

    private ArrayList<AppointmentObject> appointments = new ArrayList<>();
    public Date thisDate = new Date();
    public SimpleDateFormat dateForm = new SimpleDateFormat("dd MMMM YYYY");
    public SimpleDateFormat timeForm  = new SimpleDateFormat("h : mm a");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        TextView scrollingView = (TextView) findViewById(R.id.currentAppointment);
        scrollingView.setMovementMethod(new ScrollingMovementMethod());

        ((TextView)findViewById(R.id.appointmentDate)).setText(dateForm.format(thisDate));
        ((TextView)findViewById(R.id.appointmentTime)).setText(timeForm.format(thisDate));

        findViewById(R.id.addAppointment).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try {

                String title = ((TextView)findViewById(R.id.appointmentTitle)).getText().toString();
                String date = ((TextView)findViewById(R.id.appointmentDate)).getText().toString();
                String time = ((TextView)findViewById(R.id.appointmentTime)).getText().toString();
                appointments.add(0, new AppointmentObject(title, date, time));
                AppointmentObject a = appointments.get(0);
                    dateForm.setLenient(false);
                    timeForm.setLenient(false);
                    thisDate = dateForm.parse(a.getDate());
                    thisDate = timeForm.parse(a.getTime());

                    String texts = "";
                    for (int i = 0; i < appointments.size(); i++) {
                        a = appointments.get(i);
                        if(i == 0){
                            texts = texts.concat("• "+a.getTitle() + " on " + a.getDate() + " at " + a.getTime());
                        } else {
                            texts = texts.concat("\n\n"+"• "+a.getTitle() + " on " + a.getDate() + " at " + a.getTime());
                        }

                    }

                    if (a.getTitle() != null) {

                        ((TextView) findViewById(R.id.currentAppointment)).setText(texts);
                        ((TextView) findViewById(R.id.appointmentTitle)).setText("");
                        ((TextView) findViewById(R.id.appointmentTime)).setText(timeForm.format(thisDate));
                        ((TextView) findViewById(R.id.appointmentDate)).setText(dateForm.format(thisDate));
                        Context context = getApplicationContext();
                        CharSequence message = "Successfully added to appointments!";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, message, duration);
                        toast.setGravity(Gravity.TOP, 0, 700);
                        toast.show();
                    } else {
                        Context context = getApplicationContext();
                        CharSequence message = "Please fill out all inputs!";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, message, duration);
                        toast.setGravity(Gravity.TOP, 0, 700);
                        toast.show();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    Context context = getApplicationContext();
                    CharSequence message = "Input invalid!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, message, duration);
                    toast.setGravity(Gravity.TOP, 0, 700);
                    toast.show();
                }
            }
        });
    }
}
