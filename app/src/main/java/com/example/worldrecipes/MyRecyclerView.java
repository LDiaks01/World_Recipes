package com.example.worldrecipes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.worldrecipes.data.AppDAO;
import com.example.worldrecipes.data.AppDatabase;
import com.example.worldrecipes.data.Recipes;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerView extends AppCompatActivity {

    List<Recipes> recipesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);


        RecyclerView recyclerView = findViewById(R.id.myRecyclerView);


        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
        AppDAO myDao = db.appDAO();
        setRecipesList(myDao.getAllRecipes());

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this,recipesList);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void setRecipesList(List<Recipes> recipesList){
        this.recipesList = recipesList;
    }
}