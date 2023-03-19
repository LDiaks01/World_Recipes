package com.example.worldrecipes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.worldrecipes.fragments.Notification_fragment;
import com.example.worldrecipes.manager.UserManager;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.worldrecipes.data.AppDAO;
import com.example.worldrecipes.data.AppDatabase;
import com.example.worldrecipes.fragments.Add_recipe_fragment;
import com.example.worldrecipes.fragments.Home_fragment;
import com.example.worldrecipes.fragments.Home_fragment;
import com.example.worldrecipes.fragments.Profile_fragment;
import com.example.worldrecipes.fragments.Setting_fragment;
import com.example.worldrecipes.recyclerviews.CategoryViewAdapter;
import com.firebase.ui.auth.data.model.User;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ///adding code for recycler view
    List<String> categories = new ArrayList<>();
    List<Integer> nombreRecettes = new ArrayList<>();
    List<Integer> imageRes = new ArrayList<>();

    ///
    BottomNavigationView bottomNavigationView;
    Home_fragment home_fragment=new Home_fragment();
    Setting_fragment setting_fragment=new Setting_fragment();
    Add_recipe_fragment add_recipe_fragment= new Add_recipe_fragment();
    Profile_fragment profile_fragment=new Profile_fragment();
    Notification_fragment notification_fragment=new Notification_fragment();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView=findViewById(R.id.bottom_navigation);


        BadgeDrawable badgeDrawable= bottomNavigationView.getOrCreateBadge(R.id.notification);
        badgeDrawable.setVisible(true);
        badgeDrawable.setNumber(1);




        getSupportFragmentManager().beginTransaction().replace(R.id.containere,home_fragment).commit();


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containere,home_fragment).commit();
                        return true;

                    case R.id.profile:

                        UserManager userManager = UserManager.getInstance();
                        if(userManager.isCurrentUserLogged()){
                            getSupportFragmentManager().beginTransaction().replace(R.id.containere,profile_fragment).commit();
                            return true;

                        }else{
                            Toast.makeText(getApplicationContext(), R.string.needconnexion, Toast.LENGTH_SHORT).show();
                            return true;
                            //startSignInActivity();
                        }


                    case R.id.setting:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containere, setting_fragment).commit();
                        return true;

                    case R.id.addrecipe:
                        userManager = UserManager.getInstance();
                        if(userManager.isCurrentUserLogged()){
                            getSupportFragmentManager().beginTransaction().replace(R.id.containere,add_recipe_fragment).commit();
                            return true;

                        }else{
                            Toast.makeText(getApplicationContext(), R.string.needconnexion, Toast.LENGTH_SHORT).show();
                            return true;
                            //startSignInActivity();
                        }

                    case R.id.notification:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containere,notification_fragment).commit();
                        return true;
                }



                return false;
            }
        });


    }


}