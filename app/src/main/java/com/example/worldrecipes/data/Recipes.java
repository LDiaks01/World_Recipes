package com.example.worldrecipes.data;

import androidx.annotation.Nullable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipe_details_light")
public class Recipes {
    @ColumnInfo(name = "cuisine")
    public String cuisine;

    @ColumnInfo(name = "recipe_id")
    @Nullable
    @PrimaryKey
    public int recipeId;

    @ColumnInfo(name = "source")
    public String source;

    @ColumnInfo(name = "title")
    public String title;


    @Override
    public String toString() {
        return "Recipes{" +
                "recipeId=" + recipeId +
                ", title='" + title + '\'' +
                ", source='" + source + '\'' +
                ", cuisine='" + cuisine + '\'' +
                '}';
    }


}
