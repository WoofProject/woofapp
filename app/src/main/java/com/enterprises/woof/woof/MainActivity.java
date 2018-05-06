package com.enterprises.woof.woof;

import android.app.Dialog;
import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private CardView appointmentsCard, medicationsCard, routinesCard, accountCard, locationCard;
    Dialog myDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //defining cards in constructor
        appointmentsCard = (CardView) findViewById(R.id.appointments_card);
        medicationsCard = (CardView) findViewById(R.id.medications_card);
        routinesCard = (CardView) findViewById(R.id.routines_card);
        accountCard = (CardView) findViewById(R.id.account_card);
        locationCard = (CardView) findViewById(R.id.location_card);

        //adding click listeners to card
        appointmentsCard.setOnClickListener(this);
        medicationsCard.setOnClickListener(this);
        routinesCard.setOnClickListener(this);
        accountCard.setOnClickListener(this);
        locationCard.setOnClickListener(this);

        //pop-up initialisation
        myDialog = new Dialog(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;

        switch(v.getId()) {
            case R.id.appointments_card : intent = new Intent(this, Appointments.class); startActivity(intent); break;
            case R.id.medications_card : intent = new Intent(this, Medications.class); startActivity(intent); break;
            case R.id.routines_card : intent = new Intent(this, Routines.class); startActivity(intent); break;
            case R.id.account_card : intent = new Intent(this, Accounts.class); startActivity(intent); break;
            case R.id.location_card : intent = new Intent(this, Maps.class); startActivity(intent); break;
            default : break;
        }
    }

    public void showPopUp(View v) {
        TextView txtClose;
        myDialog.setContentView(R.layout.custompopup);
        txtClose = (TextView) myDialog.findViewById(R.id.txtclose);
        txtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }
}