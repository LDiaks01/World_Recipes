package com.example.worldrecipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class LoginActivity extends AppCompatActivity {
TabLayout tabLayout;
ViewPager viewPager;
FloatingActionButton google;
float v=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tabLayout=(TabLayout) findViewById(R.id.tab_layout);
        viewPager=findViewById(R.id.view_pagers);


        tabLayout.addTab(tabLayout.newTab().setText("CONNEXION"));
        tabLayout.addTab(tabLayout.newTab().setText("INSCRIPTION"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final LoginAdapter adapter= new LoginAdapter(getSupportFragmentManager(), this, tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setTranslationY(300);


        tabLayout.setAlpha(v);

    }
}