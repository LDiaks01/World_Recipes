package com.example.worldrecipes.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;



@Database(entities = {Recipes.class,Ingredients.class,Aliases.class }, version = 3)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "data2")
                            .createFromAsset("database/data2.sqlite")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;

    }

    public abstract AppDAO appDAO();







}

