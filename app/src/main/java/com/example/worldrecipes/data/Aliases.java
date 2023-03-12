package com.example.worldrecipes.data;


import android.support.annotation.Nullable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipes_aliases_light2")
public class Aliases {

    @ColumnInfo(name = "ID")
    @Nullable
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "RecipeID")
    public int recipeId;

    @ColumnInfo(name = "OriginalIngredientName")
    public String originalIngredientName;

    @ColumnInfo(name = "AliasedIngredientName")
    public String AliasedIngredientName;

    @ColumnInfo(name = "EntityID")
    public int entityId;

}
