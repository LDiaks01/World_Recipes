package com.example.worldrecipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.worldrecipes.data.AppDAO;
import com.example.worldrecipes.data.AppDatabase;
import com.example.worldrecipes.data.Recipes;
import com.example.worldrecipes.recyclerviews.RecetteViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecettesDetailActivity extends AppCompatActivity {

    public List<String> titreRecettes = new ArrayList<>();
    public List<Integer> nbIngredients = new ArrayList<>();
    public List<Integer> images = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recettes_detail);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recettesDetailRecyclerView);

        TextView titre = (TextView) findViewById(R.id.titleCategory);

        Intent intent = getIntent();

        String cuisine = "Africa";
        // Vérifier si l'Intent a des extras
        Bundle extras = intent.getExtras();
        if (extras != null) {

            cuisine = extras.getString("cuisine");

            titre.setText(titre.getText() + cuisine);



        }


        setupDatasForRecycler(cuisine);

        RecetteViewAdapter adapter = new RecetteViewAdapter(this, titreRecettes, nbIngredients,images);

        recyclerView.setAdapter(adapter);


        GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);


    }

    public void setupDatasForRecycler(String cuisine)
    {
        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
        AppDAO myDao = db.appDAO();
        List<Integer> imgPreset = new ArrayList(Arrays.asList(R.drawable.africa,
                R.drawable.china,
                R.drawable.thailand,
                R.drawable.greece,
                R.drawable.italy,
                R.drawable.easterneurope,
                R.drawable.spain,
                R.drawable.usa));

        List<Recipes> recipes = myDao.getRecipesByCuisine(cuisine);

        int i = 0;
        for (Recipes recipe: recipes
             ) {

            titreRecettes.add(recipe.title);
            nbIngredients.add(myDao.getNbIngredientsPerRecipes(recipe.recipeId));
            i++;
            if(i == imgPreset.size() - 1){
                i = 0;
            }
            images.add(imgPreset.get(i));

        }


    }
}