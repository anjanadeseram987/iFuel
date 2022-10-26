package com.example.ifuellogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText email,password;
    Button signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getSupportActionBar().hide();

        email = findViewById(R.id.email1);
        password = findViewById(R.id.password1);
        signin = findViewById(R.id.btnsignin1);
        DB = new DBHelper(this);

        signin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String eml = email.getText().toString();
                String pass = password.getText().toString();

                if(eml.equals("")||pass.equals(""))
                    Toast.makeText(LoginActivity.this,"All fields required",Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuser = DB.checkemailpass(eml,pass);
                    if (checkuser== true) {
                        Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                        if(eml.equals("admin@ifuel.com")){
                            Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
//                            Bundle bundle = new Bundle();
//                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                        else{
                            Intent intent1 = new Intent(getApplicationContext(),ShedActivity.class);
//                            Bundle b = new Bundle();
//                            intent1.putExtras(b);
                            startActivity(intent1);
                        }
                    }
                    else {
                        Toast.makeText(LoginActivity.this,"Email or Password Invalid",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

//    public void openSignup(View view){
//        Intent intent = new Intent(this,MainActivity.class);
//        Bundle b = new Bundle();
//        intent.putExtras(b);
//        startActivity(intent);
//    }

}
