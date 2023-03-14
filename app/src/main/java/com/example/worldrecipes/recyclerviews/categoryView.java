package com.example.worldrecipes.recyclerviews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.worldrecipes.R;
import com.example.worldrecipes.RecyclerViewAdapter;
import com.example.worldrecipes.data.AppDAO;
import com.example.worldrecipes.data.AppDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class categoryView extends AppCompatActivity {

    List<String> categories = new ArrayList<>();
    List<Integer> nombreRecettes = new ArrayList<>();
    List<Integer> imageRes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_view);

        RecyclerView categoryView = findViewById(R.id.categoryrecyclerview);


        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
        setupDatasForRecycler();


        CategoryViewAdapter categoryViewAdapter = new CategoryViewAdapter(this,categories, nombreRecettes, imageRes);
        categoryView.setAdapter(categoryViewAdapter);
        categoryView.setLayoutManager(new LinearLayoutManager(this));



    }

    public void setupDatasForRecycler(){
        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
        AppDAO myDao = db.appDAO();
        this.categories = myDao.getAllCategories();

        for(int i = 0; i< categories.size(); i++)
        {
            int nb = myDao.coutRecipesByCategory(categories.get(i)) ;
            this.nombreRecettes.add(nb);

        }

        this.imageRes = new ArrayList(Arrays.asList(R.drawable.africa,
                R.drawable.china,
                R.drawable.thailand,
                R.drawable.greece,
                R.drawable.italy,
                R.drawable.easterneurope,
                R.drawable.spain,
                R.drawable.usa));


    }
}