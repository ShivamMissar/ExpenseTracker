package com.example.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Document;


public class ManageAccount extends AppCompatActivity
{
    private Button signOutButton;
    private Button updateEmailButton;
    private Button updateCurrencyButton;
    private Button updatePasswordButton;

    Spinner updateUserCurrency;

    private String update_user_currency_to;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_account);

        signOutButton = findViewById(R.id.SignOutButton);
        updateEmailButton = findViewById(R.id.UpdateEmailButton);
        updateCurrencyButton = findViewById(R.id.UpdateCurrencyButton);
        updatePasswordButton = findViewById(R.id.UpdatePasswordButton);
        updateUserCurrency = findViewById(R.id.update_currency);


        String[] currency_options = {"GBP (£)", "United States Dollar ($)", "Euros(€)", "Yen (¥)", "Canadian Dollar (C$)", "Australian Dollar (A$)"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, currency_options);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        updateUserCurrency.setAdapter(adapter);



        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
               FirebaseAuth signUserOut = FirebaseAuth.getInstance();
               signUserOut.signOut();
               if(signUserOut.getCurrentUser() == null)
               {
                   Intent intent = new Intent(ManageAccount.this, MainActivity.class);
                   startActivity(intent);
               }
            }
        });


        updateEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(ManageAccount.this, UpdateEmail.class);
                startActivity(intent);
            }
        });

        updatePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManageAccount.this, UpdatePassword.class);
                startActivity(intent);
            }
        });

        updateUserCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = (String) parent.getItemAtPosition(position);
                update_user_currency_to = selectedItem;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
            }
        });

        updateCurrencyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                FirebaseUser user = firebaseAuth.getCurrentUser(); // this gets the current logged in user;
                FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
                DocumentReference user_data_ref = firebaseFirestore.collection("UserData").document(user.getUid());

                user_data_ref.update("currency",update_user_currency_to).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused)
                    {
                        System.out.println("Successfully updated");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        System.out.println("Not updated");
                    }
                });
            }
        });





    }
}