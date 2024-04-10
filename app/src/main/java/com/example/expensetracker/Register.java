package com.example.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    private EditText user_firstname;

    private  EditText user_lastname;
    private EditText user_email;

    private EditText user_password;

    private EditText user_confirm_password;

    private Spinner user_currency;

    private Button register_user;

    private Users users;
    private Calendar dobCal;

    private FirebaseAuth create_user;
    private FirebaseFirestore firebaseFirestore;

    private String currencySelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        user_firstname = findViewById(R.id.register_firstname);
        user_lastname = findViewById(R.id.register_lastname);
        user_email = findViewById(R.id.register_email);
        user_password = findViewById(R.id.register_password);
        user_confirm_password = findViewById(R.id.register_password_confirm);
        user_currency = findViewById(R.id.register_currency);

        register_user = findViewById(R.id.buttonRegister);

        String[] currency_options = {"GBP (£)", "United States Dollar ($)", "Euros(€)", "Yen (¥)", "Canadian Dollar (C$)", "Australian Dollar (A$)"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, currency_options);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        user_currency.setAdapter(adapter);




        create_user = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        //Allow the options from the string to be displayed on the screen of the user.
        user_currency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                currencySelected = selectedItem;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        register_user.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongViewCast")
            @Override
            public void onClick(View v) {
                String fname = user_firstname.getText().toString();
                String lname = user_lastname.getText().toString();
                String email = user_email.getText().toString();
                String password = user_password.getText().toString();
                String confirm_password = user_confirm_password.getText().toString();
                String currency = currencySelected;
                Log.d("SelectedItem", currency);


                //validate the inputs to confirm no fake information
                Validation check_inputs = new Validation();

                //check 1. No missing fields as all fields are required.
                if(TextUtils.isEmpty(fname) || check_inputs.name_checker(fname) != true)
                {
                    user_firstname.setError("This field should be blank and should be no numbers or special characters in the firstname field");
                    return;
                } else if(TextUtils.isEmpty(lname) || check_inputs.name_checker(lname) != true)
                {
                    user_email.setError("This field should not be blank and there should be no numbers or special characters in the lastname field");
                   return;
                } else if(TextUtils.isEmpty(email) || check_inputs.email_checker(email) != true)
                {
                    user_email.setError("Enter a valid email address");
                    return;
                }else if(TextUtils.isEmpty(password) || check_inputs.password_checker(password) != true)
                {
                    user_password.setError("Please enter a strong password");
                    return;
                } else if (TextUtils.isEmpty(confirm_password))
                {
                    user_password.setError("Please ensure your passwords match");
                    return;
                }




                create_user.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        Toast.makeText(Register.this,"Account Creation Success!",Toast.LENGTH_SHORT).show();

                        Users user = new Users(fname,lname,email,currency);
                        firebaseFirestore.collection("UserData").document(FirebaseAuth.getInstance().getUid()).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused)
                            {
                                System.out.println("This has been saved to the DB");
                                Toast toast = Toast.makeText(Register.this,"You have successfully registered",Toast.LENGTH_SHORT);
                                toast.show();

                                Intent intent  = new Intent(Register.this, Homepage.class);
                                startActivity(intent);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast toast = Toast.makeText(Register.this,"You have not registered",Toast.LENGTH_SHORT);
                                toast.show();
                                System.out.println("This has not been saved to the DB");
                            }
                        });

                    }
                });

                    }
                });
            }




}