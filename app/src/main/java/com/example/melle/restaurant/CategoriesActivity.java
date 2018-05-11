package com.example.melle.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {

    // list definiëren
    ListView listt;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        listt = findViewById(R.id.list);

        // er kan geklikt worden op list
        listt.setOnItemClickListener(new ListClickListener());

        // menusoorten worden aangeroepen
        CategoriesRequest request = new CategoriesRequest(getApplicationContext());
        request.obtainCategories(this);
    }

    // in de listt worden de menusoorten geplaatst
    @Override
    public void gotCategories(ArrayList<String> categories) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.menu_sort, categories);
        listt.setAdapter(adapter);
    }

    // als er een error plaatsvindt wordt er een toast geplaatst
    @Override
    public void gotCategoriesError(String message) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, message, duration);
        toast.show();
    }

    // als er op een menuelement wordt geklikt komt er een nieuwe activity met meer info over menu
    private class ListClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View v, int position, long l) {
            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
            intent.putExtra("sort_of_menu", (String) adapterView.getItemAtPosition(position));
            startActivity(intent);
        }
    }

    // options menu wordt gemaakt
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // informatie over restaurant weergeven
    public void restaurant(MenuItem item) {
        Intent intent = new Intent(CategoriesActivity.this, AboutActivity.class);
        startActivity(intent);
    }

    // terug naar menukaart
    public void back(MenuItem item) {
        Intent intent = new Intent(CategoriesActivity.this, CategoriesActivity.class);
        startActivity(intent);
    }
}