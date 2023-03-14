package com.example.worldrecipes.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.worldrecipes.R;
import com.example.worldrecipes.data.AppDAO;
import com.example.worldrecipes.data.AppDatabase;
import com.example.worldrecipes.recyclerviews.CategoryViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Home_fragment extends Fragment {

    List<String> categories = new ArrayList<>();
    List<Integer> nombreRecettes = new ArrayList<>();
    List<Integer> imageRes = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View homeView = inflater.inflate(R.layout.fragment_home_fragement, container, false);
        RecyclerView categoryView = homeView.findViewById(R.id.recyclerCategory);

        AppDatabase db = AppDatabase.getDatabase(getActivity().getApplicationContext());
        setupDatasForRecycler();

        CategoryViewAdapter categoryViewAdapter = new CategoryViewAdapter(getActivity().getApplicationContext(),categories, nombreRecettes, imageRes);
        categoryView.setAdapter(categoryViewAdapter);
        categoryView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        return homeView;
    }

    public void setupDatasForRecycler(){
        AppDatabase db = AppDatabase.getDatabase(getActivity().getApplicationContext());
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