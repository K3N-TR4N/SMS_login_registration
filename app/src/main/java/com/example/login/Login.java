package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    DBHelper DB;
    private int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DB = new DBHelper(this);

        ImageView blue = findViewById(R.id.blue_bg);
        blue.setImageResource(R.drawable.blue_background);

        ImageView logo = findViewById(R.id.logo);
        logo.setImageResource(R.drawable.sms_logo);

        ImageView white = findViewById(R.id.white_bg);
        white.setImageResource(R.drawable.white_bg);

        TextView title = findViewById(R.id.welcome_title);
        title.setText("Welcome to ServeMe System!");

        EditText email = findViewById(R.id.email_field);
        EditText pass = findViewById(R.id.password_field);

        Button login = findViewById(R.id.login_button);
        Button cancel = findViewById(R.id.cancel_button);

        TextView register = findViewById(R.id.register_link);
        TextView forget = findViewById(R.id.forgotID_link);
        TextView guest = findViewById(R.id.guest_link);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = email.getText().toString();
                String password = pass.getText().toString();

                if(username.equals("") || password.equals("")) {
                    Toast.makeText(Login.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkuserpass = DB.checkemailpassword(username, password);
                    if(checkuserpass == true) {
                        Toast.makeText(Login.this, "Login successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, Home.class);
                        startActivity(intent);
                    } else {
                        counter--;
                        Toast.makeText(Login.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                        if(counter == 0) {
                            Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            Toast.makeText(Login.this, "Too Many Attempts", Toast.LENGTH_SHORT).show();
                            login.setEnabled(false);
                        }
                    }
                }
                /*
                if(username.matches("admin") && password.matches("12345")) {
                    Intent intent = new Intent(Login.this, Home.class);
                    startActivity(intent);
                    Toast toast = Toast.makeText(getApplicationContext(),"Success!", Toast.LENGTH_SHORT);
                    toast.show();
                    //login successful
                } else {
                    counter--;
                    Toast toast = Toast.makeText(getApplicationContext(),"Try again", Toast.LENGTH_SHORT);
                    toast.show();
                    //login failed
                    if(counter == 0) {
                        Toast toast2 = Toast.makeText(getApplicationContext(),"Login Failed", Toast.LENGTH_SHORT);
                        toast2.show();
                        Toast toast3 = Toast.makeText(getApplicationContext(),"Too Many Attempts", Toast.LENGTH_SHORT);
                        toast3.show();
                        login.setEnabled(false);
                    }
                }
                */
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(getIntent());
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Guest.class);
                startActivity(intent);
            }
        });

    }

}

