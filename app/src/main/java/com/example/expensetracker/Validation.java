package com.example.expensetracker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation
{
    final private Pattern EMAIL_PATTERN = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"); // Regex pattern for validating email
    private Matcher email_valid; // will be used to check if the email coming in as paramter matches the pattern above


    private Pattern NAME_PATTERN = Pattern.compile("[a-zA-z]+");
    private Matcher name_valid;


    final private Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\\\S+$).{8,}$\n");
    private Matcher password_valid;





    public boolean email_checker(String email)
    {
        email_valid = EMAIL_PATTERN.matcher(email); // checks if the email coming matches the pattern defined in the class.
        if (email_valid.matches()) {return true;}
        else {return false;}

    }
    public boolean password_checker(String pass)
    {
        password_valid = PASSWORD_PATTERN.matcher(pass); // checks if the email coming matches the pattern defined in the class.
        if (email_valid.matches()) {return true;}
        else {return false;}
    }

    public boolean name_checker(String first_or_last_name)
    {
        name_valid = NAME_PATTERN.matcher(first_or_last_name);
        if (name_valid.matches()) {return true;}
        else {return false;}
    }



}
