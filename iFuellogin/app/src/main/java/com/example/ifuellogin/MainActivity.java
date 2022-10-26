package com.example.ifuellogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.Window;
public class MainActivity extends AppCompatActivity {
    //define variables
    EditText email,password,name,phone;
    Button signup, signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        //variable initialization
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phone);
        signup = (Button) findViewById(R.id.btnsignup);
        signin = (Button) findViewById(R.id.btnsignin);
        DB = new DBHelper(this);

        //create listener for sign up
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eml = email.getText().toString();
                String pass = password.getText().toString();
                String nam = name.getText().toString();
                String phon = phone.getText().toString();

                if(eml.equals("")||pass.equals("")||nam.equals("")|| phon.equals(""))
                    Toast.makeText(MainActivity.this,"All fields required",Toast.LENGTH_SHORT).show();

                else {
                    Boolean checkuser = DB.checkemail(eml);
                    if (checkuser == false) {
                        Boolean insert = DB.insertData(eml,pass,nam,phon);
                        if(insert==true){
                            Toast.makeText(MainActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(MainActivity.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                        }

                    }
                    else {
                        Toast.makeText(MainActivity.this,"User already exists !!! please sign in", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //create listener for sign in
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

}