package com.example.expensetracker;

import java.util.Date;

public class Users
{
    private String fname,lname,email,currency;

    public Users(){}
    public Users(String fname, String lname, String email, String currency)
    {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.currency = currency;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getEmail() {
        return email;
    }

    public String getCurrency() {
        return currency;
    }
}
