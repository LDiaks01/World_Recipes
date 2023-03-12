package com.example.worldrecipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteQuery;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.worldrecipes.data.AppDAO;
import com.example.worldrecipes.data.AppDatabase;
import com.example.worldrecipes.data.Recipes;

import java.io.File;
import java.util.List;

public class ChoixLangue extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_langue);


        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());

        AppDAO myDao = db.appDAO();



        Button btn= (Button) findViewById(R.id.next_button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoixLangue.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });



        //test pour verfier que les données sont accessibles dans l'app
                List<Recipes> resultat = myDao.getAllRecipes();
        System.out.println("-------------- nb de lignes : " + resultat.toString());


                String sql = "SELECT * FROM recipe_details_light";
                SupportSQLiteQuery query = new SimpleSQLiteQuery(sql);
                Cursor cursor = myDao.getRecipesByName(query);
                if (cursor != null && cursor.moveToFirst()) {
                    // Récupérer les données du Cursor
                    System.out.println("-------------- nb de lignes : " + resultat);

                }




    }
}