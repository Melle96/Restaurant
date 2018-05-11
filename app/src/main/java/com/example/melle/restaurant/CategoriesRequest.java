package com.example.melle.restaurant;

import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    // interface maken met info over verkregen categorieën
    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    ArrayList<String> categories = new ArrayList<>();
    Context contextt;
    Callback callback;

    CategoriesRequest(Context context) {
        contextt = context;
    }

    // catergorieën verkrijgen
    void obtainCategories(final Callback activity) {
        String url = "https://resto.mprog.nl/categories";
        callback = activity;
        RequestQueue queue = Volley.newRequestQueue(contextt);
        JsonObjectRequest json = new JsonObjectRequest(url,
                null, this, this);
        queue.add(json);
    }

    // verkregen objecten allen in array zetten
    @Override
    public void onResponse(JSONObject response) {
        JSONArray jsonarray;
        try {
            jsonarray = response.getJSONArray("categories");
            int length = jsonarray.length();
            for (int i = 0; i < length; i=i+1) {
                categories.add(jsonarray.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        callback.gotCategories(categories);
    }

    // error weergeven indien fout optreedt
    @Override
    public void onErrorResponse(VolleyError error) {
        callback.gotCategoriesError(error.getMessage());
        error.printStackTrace();
    }
}