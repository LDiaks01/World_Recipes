package com.example.worldrecipes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.worldrecipes.data.AppDAO;
import com.example.worldrecipes.data.AppDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IngredientsActivity extends AppCompatActivity {

    public List<String> ingredients ;
    public List<Integer> images;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredients_activity);

        Intent intent = getIntent();
        int recipeId = 2610;
        String recipeName = "Couscous";
        int position = 0;
        Bundle extras = intent.getExtras();
        if (extras != null) {

            recipeId = extras.getInt("recipeId");
            recipeName = extras.getString("recipeName");
            position = extras.getInt("position");


        }
        TextView tvRecipeName = (TextView) findViewById(R.id.titreRecette);
        Button btnFermer = (Button) findViewById(R.id.closeDetails);
        ImageView imageRecette = (ImageView) findViewById(R.id.imagexRecette);


        tvRecipeName.setText(recipeName.toString());


        btnFermer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        setupDatas(recipeId);

        if(position > images.size()){
            position = 0;
        }
        imageRecette.setImageResource(images.get(position));
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, R.layout.ingredients_row, ingredients);
        ListView listView = (ListView) findViewById(R.id.listeIngredients);
        listView.setAdapter(itemsAdapter);


    }

    public void setupDatas(int recipeId)
    {
        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
        AppDAO myDao = db.appDAO();

        this.ingredients = myDao.getRecipeIngredients(recipeId);

        List<Integer> imgPreset = new ArrayList(Arrays.asList(R.drawable.africa,
                R.drawable.china,
                R.drawable.thailand,
                R.drawable.greece,
                R.drawable.italy,
                R.drawable.easterneurope,
                R.drawable.spain,
                R.drawable.usa));

        this.images = imgPreset;


    }
}