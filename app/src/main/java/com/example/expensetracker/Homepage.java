package com.example.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Homepage extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        BottomNavigationView mainmenu = findViewById(R.id.bottomNav);
        mainmenu.setSelectedItemId(R.id.home);

        mainmenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home:
                        Intent homepage = new Intent(Homepage.this, Homepage.class);
                        startActivity(homepage);
                    case R.id.manage_account:
                        Intent manage = new Intent(Homepage.this, ManageAccount.class);
                        startActivity(manage);

                    default:
                        return Homepage.super.onOptionsItemSelected(item);
                }
            }
        });
    }
}