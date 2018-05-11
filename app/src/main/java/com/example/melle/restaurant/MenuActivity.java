package com.example.melle.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuItemsRequest.Callback{
    ListView list_menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // menusoort wordt verkregen
        Intent intent = getIntent();
        String dishType = (String) intent.getSerializableExtra("sort_of_menu");
        MenuItemsRequest request = new MenuItemsRequest(getApplicationContext(), dishType);
        request.getMenuItems(this, dishType);

        list_menu = findViewById(R.id.listMenu);
        list_menu.setOnItemClickListener(new ListClickListener());

    }

    // menu's bij bepaalde menusoort worden aangeroepen en weergeven
    @Override
    public void gotMenuItems(ArrayList<MenuItem> menuItems) {
        MenuItemAdapter adapter = new MenuItemAdapter(this, R.layout.menu_item_view, menuItems);
        list_menu.setAdapter(adapter);
    }

    // toast weergeven indien een error optreedt
    @Override
    public void gotMenuItemsError(String message) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, message, duration);
        toast.show();
    }

    // kliks toevoegen voor menu's om meer informatie van menu te krijgen
    public class ListClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View v, int position, long l) {
            Intent intent = new Intent(MenuActivity.this, MenuItemActivity.class);
            intent.putExtra("menu_details", (Serializable) adapterView.getItemAtPosition(position));
            startActivity(intent);
        }
    }

    // options menu wordt gemaakt
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // informatie over restaurant weergeven
    public void restaurant(android.view.MenuItem item) {
        Intent intent = new Intent(MenuActivity.this, AboutActivity.class);
        startActivity(intent);
    }

    // terug naar mennukaart
    public void back(android.view.MenuItem item) {
        Intent intent = new Intent(MenuActivity.this, CategoriesActivity.class);
        startActivity(intent);
    }
}