package com.example.worldrecipes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.worldrecipes.fragments.Add_recipe_fragment;
import com.example.worldrecipes.fragments.Home_fragment;
import com.example.worldrecipes.fragments.Home_fragment;
import com.example.worldrecipes.fragments.Profile_fragment;
import com.example.worldrecipes.fragments.Setting_fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Home_fragment home_fragment=new Home_fragment();
    Setting_fragment setting_fragment=new Setting_fragment();
    Add_recipe_fragment add_recipe_fragment= new Add_recipe_fragment();
    Profile_fragment profile_fragment=new Profile_fragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView=findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.containere,home_fragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containere,home_fragment).commit();
                        return true;

                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containere,profile_fragment).commit();
                        return true;

                    case R.id.setting:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containere,setting_fragment).commit();
                        return true;

                    case R.id.addrecipe:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containere,add_recipe_fragment).commit();
                        return true;
                }
                return false;
            }
        });
    }
}