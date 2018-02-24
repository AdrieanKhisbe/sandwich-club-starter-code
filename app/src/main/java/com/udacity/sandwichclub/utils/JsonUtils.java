package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject jsonSandwich = new JSONObject(json);
            JSONObject sandwichNames = jsonSandwich.getJSONObject("name");

            List<String> otherNames = new ArrayList<>();
            JSONArray jsonOtherNames = sandwichNames.getJSONArray("alsoKnownAs");
            for (int i = 0; i < jsonOtherNames.length(); i++) {
                otherNames.add(jsonOtherNames.getString(i));
            }
            List<String> ingredients = new ArrayList<>();
            JSONArray jsonIngredients = jsonSandwich.getJSONArray("ingredients");
            for (int i = 0; i < jsonIngredients.length(); i++) {
                ingredients.add(jsonIngredients.getString(i));
            }

            return new Sandwich(
                    sandwichNames.getString("mainName"),
                    otherNames,
                    jsonSandwich.getString("placeOfOrigin"),
                    jsonSandwich.getString("description"),
                    jsonSandwich.getString("image"),
                    ingredients
            );
        } catch (JSONException exception) {
            Log.e("JsonUtils", "Failed to parse JSON: " + exception.getMessage());
            return null;
        }
    }
}
