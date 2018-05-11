package com.example.melle.restaurant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

// hierin worden de connectie gelegd tussen menu_item_view.xml en activity_menu.xml
public class MenuItemAdapter extends ArrayAdapter<MenuItem> {


    private ArrayList<MenuItem> dish_list;

    MenuItemAdapter(@NonNull Context context, int resource, @NonNull ArrayList<MenuItem> objects) {
        super(context, resource, objects);
        dish_list = objects;
    }


    @NonNull
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_item_view, parent, false);
        }

        // naam, prijs en plaatje worden aangeroepen
        TextView pricee = convertView.findViewById(R.id.price);
        TextView namee = convertView.findViewById(R.id.name);
        ImageView imagee = convertView.findViewById(R.id.image);

        // naam, prijs en plaatje krijgen de juiste waarde
        MenuItem sort_of_dish = dish_list.get(position);
        String picture = sort_of_dish.getUrl();
        String menu = sort_of_dish.getMenuname();
        String price = sort_of_dish.getPrice().toString();

        // tekst wordt gezet
        namee.setText(menu);
        pricee.setText("€"+ price);

        // plaathe wordt geplaatst
        Picasso.with(getContext())
                .load(picture)

                //indien error plaatje restaurant getoond
                .error(R.drawable.restaurant)
                .into(imagee);


        return convertView;
    }
}