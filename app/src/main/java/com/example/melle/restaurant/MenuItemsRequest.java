package com.example.melle.restaurant;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MenuItemsRequest implements Response.Listener<JSONObject>, Response.ErrorListener {


    public interface Callback {
        void gotMenuItems(ArrayList<MenuItem> categories);
        void gotMenuItemsError(String message);
    }

    private String dish_type;
    private Callback callback;
    private Context contextt;
    private ArrayList<MenuItem> menu = new ArrayList<>();

    MenuItemsRequest(Context context, String dishType) {
        contextt = context;
        dish_type = dishType;
    }


    // menusoorten verkrijgen
    void getMenuItems(final Callback activity, String category) {
        String url = "https://resto.mprog.nl/menu";
        callback = activity;
        dish_type = category;
        RequestQueue queue = Volley.newRequestQueue(contextt);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url,
                null, this, this);
        queue.add(jsonObjectRequest);
    }


    // JSONobjecten in arrays zetten
    @Override
    public void onResponse(JSONObject response) {
        JSONArray array;
        try {
            array = response.getJSONArray("items");
            int length = array.length();
            JSONObject json_object;

            //itereren over aantal menu's
            for (int i = 0; i < length; i++) {
                json_object = array.getJSONObject(i);
                String category = json_object.getString("category");
                if (dish_type.equals(category)) {

                    // naam, omschrijving, plaatje en prijs opslaan
                    String menuname = json_object.getString("name");
                    String description = json_object.getString("description");
                    String url = json_object.getString("image_url");
                    Double price = json_object.getDouble("price");
                    menu.add(new MenuItem(menuname, description, url, price, category));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        callback.gotMenuItems(menu);

    }

    // error doorsturen indien fout optreedt
    @Override
    public void onErrorResponse(VolleyError error) {
        callback.gotMenuItemsError(error.getMessage());

    }
}