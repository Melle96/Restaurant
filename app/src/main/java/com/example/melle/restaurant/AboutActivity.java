package com.example.melle.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    // options menu wordt gemaakt
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // informatie over restaurant weergeven
    public void restaurant(android.view.MenuItem item) {
        Intent intent = new Intent(AboutActivity.this, AboutActivity.class);
        startActivity(intent);
    }

    // terug naar menukaart
    public void back(android.view.MenuItem item) {
        Intent intent = new Intent(AboutActivity.this, CategoriesActivity.class);
        startActivity(intent);
    }
}
