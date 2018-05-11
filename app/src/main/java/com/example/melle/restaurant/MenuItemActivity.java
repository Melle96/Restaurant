package com.example.melle.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

// hierin wordt een bepaald menu met karakterisieken gedefinieerd
public class MenuItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        // juiste menuelement wordt weergeven
        Intent intent = getIntent();
        MenuItem menuItemm = (MenuItem) intent.getSerializableExtra("menu_details");

        // naam, beschrijving, prijs en plaatje worden geinstantieerd
        TextView description = findViewById(R.id.description);
        TextView price = findViewById(R.id.price);
        TextView name = findViewById(R.id.name);
        ImageView image = findViewById(R.id.image);

        // tekst wordt gezet
        name.setText(menuItemm.getMenuname());
        description.setText(menuItemm.getDescription());
        String imagee = menuItemm.getUrl();
        price.setText("€" + menuItemm.getPrice().toString());

        // plaatje wordt weergeven
        Picasso.with(this)
                .load(imagee)

                // als er een error voor het plaatje is, wordt het plaatje van het restaurant weergeven
                .error(R.drawable.restaurant)
                .into(image);
    }

    // options menu wordt gemaakt
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // informatie over restaurant weergeven
    public void restaurant(android.view.MenuItem item) {
        Intent intent = new Intent(MenuItemActivity.this, AboutActivity.class);
        startActivity(intent);
    }

    // terug naar menukaart
    public void back(android.view.MenuItem item) {
        Intent intent = new Intent(MenuItemActivity.this, CategoriesActivity.class);
        startActivity(intent);
    }
}