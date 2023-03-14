package com.example.worldrecipes.data;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteQuery;

import java.util.List;

@Dao
public interface AppDAO {

    @Query("Select * from recipe_details_light")
    public List<Recipes> getAllRecipes();

    @Query("Select * from recipe_details_light where cuisine =:cuisine")
    public List<Recipes> getRecipesByCuisine(String cuisine);

    @Query("Select * from recipe_details_light where recipe_id =:id")
    public Recipes getOneRecipes(int id);

    @Query("SELECT COUNT(*) FROM recipe_details_light where cuisine =:category")
    public int coutRecipesByCategory(String category);

    @Query("SELECT distinct cuisine from recipe_details_light")
    public List<String> getAllCategories();

    @Query("SELECT count(*) from recipes_aliases_light2 where RecipeID=:recipe_id")
    public int getNbIngredientsPerRecipes(int recipe_id);


    @RawQuery
    Cursor getRecipesByName(SupportSQLiteQuery query);





    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertRecipes(Recipes... recipe);
}
