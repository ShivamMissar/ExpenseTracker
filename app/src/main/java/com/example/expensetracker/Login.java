package com.example.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login extends AppCompatActivity {

    private Button _user_sign_in;
    private EditText _user_email;


    private static final String SUCCESS = "LOGIN SUCCESS";
    private static final String FAILURE = "LOGIN FAILED";
    private EditText _user_password;



    private FirebaseAuth userAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //Obtain the input for email and password
        userAuth = FirebaseAuth.getInstance();
        _user_sign_in = findViewById(R.id.buttonSignin);
        _user_email = findViewById(R.id.login_email);
        _user_password = findViewById(R.id.login_password);



        _user_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = _user_email.getText().toString();
                String password = _user_password.getText().toString();
                userAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult)
                    {
                        // if the user has successfully entered their login, move to the next page.
                        Intent homepage = new Intent(Login.this, ManageAccount.class);
                        startActivity(homepage);
                        System.out.println("Logged in");

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        // If login has failed, have the user re-try
                        System.out.println("FAILED");

                    }
                });
            }
        });
    }


    public void forgotPassword(View view)
    {
        Intent resetPasswordActivty = new Intent(Login.this,ResetPassword.class);
        startActivity(resetPasswordActivty);
    }






}