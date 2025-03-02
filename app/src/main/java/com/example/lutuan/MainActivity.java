package com.example.lutuan;

import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {

    private List<String> selectedIngredients;
    private ListView selectedIngredientsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView ingredientsListView = findViewById(R.id.ingredients);

        ArrayList<String> ingredientsList = new ArrayList<>();

        // ito yung mga ingredients
        Collections.addAll(ingredientsList,
                "Bay Leaf", "Chicken", "Garlic", "Soy Sauce", "Vinegar",
                "Onion", "Pork", "Radish", "Tamarind", "Tomato", "Pork Belly",
                "Salt", "Water", "Eggplant", "Oxtail", "Peanut Butter", "String Beans",
                "Chili", "Coconut Milk", "Pork", "Shrimp paste", "Beef", "Egg",
                "Rice", "Carrot", "Ground Pork", "Wrapper", "Chicken", "Chayote", "Ginger"
        );

        // tanggalin yung mga duplicates
        ArrayList<String> uniqueIngredients = new ArrayList<>();
        for (String ingredient : ingredientsList) {
            if (!uniqueIngredients.contains(ingredient)) {
                uniqueIngredients.add(ingredient);
            }
        }

        // sort ingredients alphabetically
        Collections.sort(uniqueIngredients);

        // set tayo ng adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_2, uniqueIngredients) {
            @NonNull
            @Override
            public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                if (view instanceof TextView) {
                    ((TextView) view).setTextColor(android.graphics.Color.parseColor("#F5f5f5"));
                }
                return view;
            }
        };
        ingredientsListView.setAdapter(adapter);

        ingredientsListView.setOnItemClickListener((parent, view, pos, id) -> {
            String ingredient = uniqueIngredients.get(pos);
            Toast.makeText(this, "Ingredient: " + ingredient, Toast.LENGTH_SHORT).show();

            selectedIngredients.add(ingredient);
            DisplaySelected();
        });
    }

    private void DisplaySelected()
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, selectedIngredients) {
            @NonNull
            @Override
            public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                if (view instanceof TextView) {
                    ((TextView) view).setTextColor(android.graphics.Color.parseColor("#F5f5f5"));
                }
                return view;
            }
        };

        selectedIngredientsView.setAdapter(adapter);
    }
}