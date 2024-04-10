package com.example.expensetracker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.OAuthCredential;

public class UpdatePassword extends AppCompatActivity
{

    private FirebaseUser get_current_user_logged_in;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);


        // call the function here
        //re_authenticate_user(email,password)
    }

    public void re_authenticate_user(String email, String password)
    {
        String current_email = email;
        String current_password = password;



        get_current_user_logged_in = firebaseAuth.getCurrentUser();

        // in order to re-authenticate, AuthCredential class is needed which takes email and password to confirm login
        AuthCredential credential = EmailAuthProvider.getCredential(current_email, current_password);
        // then apply the re-authenticate
        get_current_user_logged_in.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    // here it will show up with the input field to update password
                }
            }
        });
    }
}