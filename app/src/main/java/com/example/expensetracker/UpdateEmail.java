package com.example.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class UpdateEmail extends AppCompatActivity {

    private FirebaseUser get_current_user_logged_in;


    private EditText get_user_email;
    private EditText get_user_password;

    private EditText change_to_this_email;

    private Button login;

    private Button update_email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_email);

        get_user_email = findViewById(R.id.update_email_emailinput);
        get_user_password = findViewById(R.id.update_email_password_input);
        change_to_this_email = findViewById(R.id.newemailaddress);


        login = findViewById(R.id.update_email_login);
        update_email = findViewById(R.id.updateEmailButton); // update button


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login_email = get_user_email.getText().toString();
                String login_password = get_user_password.getText().toString();
                re_authenticate_user(login_email, login_password);
            }
        });
        update_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update_email();

            }
        });
    }

    public void re_authenticate_user(String email, String password) {
        String current_email = email;
        String current_password = password;

        get_current_user_logged_in = FirebaseAuth.getInstance().getCurrentUser();

        // in order to re-authenticate, AuthCredential class is needed which takes email and password to confirm login
        AuthCredential credential = EmailAuthProvider.getCredential(current_email, current_password);
        // then apply the re-authenticate
        get_current_user_logged_in.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    System.out.println("Logged In");
                    // here it will show up with the input field to update password
                    change_to_this_email.setVisibility(View.VISIBLE);
                    update_email.setVisibility(View.VISIBLE);

                }
            }
        });
    }





    public void update_email()
    {
        // this will be the new email set in the db
        String newEmail = change_to_this_email.getText().toString().trim();


        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser == null) // confirm the user is logged in
        {
            System.out.println("User is not logged in");
        }


        firebaseUser.updateEmail(newEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                if(task.isSuccessful())
                {
                    Toast.makeText(UpdateEmail.this, "Email address updated successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UpdateEmail.this, ManageAccount.class));
                }
                else
                {
                    Exception e = task.getException();
                    if (e != null) {
                        Log.e("UpdateEmail", "Error updating email address", e);
                        Toast.makeText(UpdateEmail.this, "Failed to update email address", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
}