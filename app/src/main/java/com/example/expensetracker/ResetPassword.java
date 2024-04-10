package com.example.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

public class ResetPassword extends AppCompatActivity {

    EditText email_to_reset_password;
    private Button submit_to_reset_email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        TextView message = findViewById(R.id.messagetobeshown);

        FirebaseAuth connect = FirebaseAuth.getInstance();

        email_to_reset_password = findViewById(R.id.resetwithemail);

        submit_to_reset_email = findViewById(R.id.resetbutton);

        submit_to_reset_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String email_for_reset = email_to_reset_password.getText().toString();

                            connect.sendPasswordResetEmail(email_for_reset).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                    if(TextUtils.isEmpty(email_for_reset))
                                    {
                                        email_to_reset_password.setError("Please enter the email that you want to password link to come to");
                                    }
                                    else
                                    {
                                        if(task.isSuccessful())
                                        {
                                            message.setText("The reset link has been sent to" + email_for_reset);
                                            message.setVisibility(View.VISIBLE);

                                        }

                                    }

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e)
                                {

                                }
                            });
                        }
                    });
    }
}