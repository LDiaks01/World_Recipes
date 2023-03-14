package com.example.worldrecipes.recyclerviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worldrecipes.R;

import java.util.List;

public class RecetteViewAdapter extends RecyclerView.Adapter<RecetteViewAdapter.RecetteViewHolder>{
    Context context;
    public List<String> titreRecettes;
    public List<Integer> nbIngredients;
    public List<Integer> imageRes;


    public RecetteViewAdapter(Context context, List<String> titreRecettes, List<Integer> nbIngredients, List<Integer> imageRes) {
        this.context = context;
        this.titreRecettes = titreRecettes;
        this.nbIngredients = nbIngredients;
       // this.imageRes = imageRes;
    }

    @NonNull
    @Override
    public RecetteViewAdapter.RecetteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recettes_detail_row, parent, false);

        return new RecetteViewAdapter.RecetteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecetteViewAdapter.RecetteViewHolder holder, int position) {
        holder.titreRecette.setText(titreRecettes.get(position));
        //holder.imageRecette.setImageResource(imageRes.get(position));
        holder.nbIngredients.setText("Composé de " + nbIngredients.get(position).toString() + " Ingredients");

    }

    @Override
    public int getItemCount() {
        return titreRecettes.size();
    }

    public static class RecetteViewHolder extends RecyclerView.ViewHolder
    {

        TextView titreRecette;
        ImageView imageRecette;
        TextView nbIngredients;

        public RecetteViewHolder(@NonNull View itemView) {
            super(itemView);

            titreRecette = itemView.findViewById(R.id.NomRecettes);
            imageRecette = itemView.findViewById(R.id.imageRecette);
            nbIngredients = itemView.findViewById(R.id.NbIngredients);
        }


    }



}
