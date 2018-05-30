package com.enterprises.woof.woof;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends AppCompatActivity {

    Button btn;
    Button btn2;
    Button btn3;
    DatabaseHelper helper = new DatabaseHelper(this);
    CurrentUser user = new CurrentUser(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn3 = (Button)findViewById(R.id.learnmore);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, MainActivity.class));
            }
        });

        btn = (Button)findViewById(R.id.login);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText a = (EditText)findViewById(R.id.enteredemail);
                String str = a.getText().toString();
                EditText b = (EditText)findViewById(R.id.enteredpassword);
                String pass = b.getText().toString();

                    String password = helper.searchPassword(str);
                    if (pass.equals(password)) {
                        user.setEmail(str);
                        Toast correct = Toast.makeText(Login.this, "Logged In Successfully", Toast.LENGTH_SHORT);
                        correct.show();
                        startActivity(new Intent(Login.this, MainActivity.class));
                    } else {
                        Toast incorrect = Toast.makeText(Login.this, "Username and password don't match!", Toast.LENGTH_SHORT);
                        incorrect.show();
                    }
            }
        });

        btn2 = (Button)findViewById(R.id.signup);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, SignUp.class));
            }
        });
    }


}
