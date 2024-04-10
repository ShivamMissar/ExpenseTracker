package com.example.expensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button register = findViewById(R.id.goToRegister);
        TextView reset_password = findViewById(R.id.forgottenpassword);
        Button login = findViewById(R.id.goToLogin);


        //go to the register page
       register.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent go_to_register = new Intent(MainActivity.this,Register.class);
               startActivity(go_to_register);
           }
       });

        //go to the login page
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_to_login = new Intent(MainActivity.this,Login.class);
                startActivity(go_to_login);
            }
        });

        reset_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent goto_reset_password = new Intent(MainActivity.this,ResetPassword.class);
                startActivity(goto_reset_password);
            }
        });




    }
}