package com.example.worldrecipes.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "02_ingredients")
public class Ingredients {

    @ColumnInfo(name = "AliasedIngredientName")
    public String ingredientName;

    @ColumnInfo(name = "IngredientSynonyms")
    public String ingredientSynonym;

    @PrimaryKey
    @Nullable
    @ColumnInfo(name = "EntityID")
    public int entityId;


    @ColumnInfo(name = "Category")
    public String category;









}
