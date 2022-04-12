package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText fname = findViewById(R.id.fname_field);
        EditText lname = findViewById(R.id.lname_field);
        EditText email = findViewById(R.id.email_field);
        EditText phone = findViewById(R.id.phone_field);
        EditText pass = findViewById(R.id.password_field);
        EditText confirm = findViewById(R.id.con_password_field);

        Button submit = findViewById(R.id.submit_button);
        Button cancel = findViewById(R.id.cancel_button);

        DB = new DBHelper(this);


        /*
        Need to enable benefits!!!!!!!!!
         */

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String first_name = fname.getText().toString();
                String last_name = lname.getText().toString();
                String c_email = email.getText().toString();
                String c_phone = phone.getText().toString();
                String password = pass.getText().toString();
                String con_password = confirm.getText().toString();

                if(first_name.equals("") || last_name.equals("") || c_email.equals("") || c_phone.equals("") || password.equals("") || con_password.equals("")) {
                    Toast.makeText(Register.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    if(password.equals(con_password)) {
                        Boolean checkCustomer = DB.checkemail(c_email);
                        if(checkCustomer == false) { // Inserts customer to database
                            Boolean insertCustomer = DB.insertData(first_name, last_name, c_email, c_phone, password);
                            if(insertCustomer == true) {
                                Toast.makeText(Register.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Register.this, Setup.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(Register.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Register.this, "User already exists! Please sign in", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Register.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(getIntent());
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });
    }
}