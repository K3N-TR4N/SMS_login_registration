package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Guest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);

        EditText fname = findViewById(R.id.fname_field);
        EditText lname = findViewById(R.id.lname_field);
        EditText phone = findViewById(R.id.phone_field);

        TextView benefits = findViewById(R.id.register_link);
        Button login = findViewById(R.id.login_button);
        Button cancel = findViewById(R.id.cancel_button);

        /*
        Need to connect contact information to database??
         */

        benefits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Guest.this, Register.class);
                startActivity(intent);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(getIntent());
                Intent intent = new Intent(Guest.this, Login.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String first_name = fname.getText().toString();
                String last_name = lname.getText().toString();
                String c_phone = phone.getText().toString();

                if(first_name.equals("") || last_name.equals("") || c_phone.equals("")) {
                    Toast.makeText(Guest.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Guest.this, Home.class);
                    startActivity(intent);
                }
            }
        });
    }
}