package com.example.worldrecipes.recyclerviews;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worldrecipes.IngredientsActivity;
import com.example.worldrecipes.R;
import com.example.worldrecipes.RecettesDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class RecetteViewAdapter extends RecyclerView.Adapter<RecetteViewAdapter.RecetteViewHolder>{
    Context context;
    public List<String> titreRecettes;
    public List<Integer> nbIngredients;
    public List<Integer> imageRes;
    public List<Integer> recettesId;


    public RecetteViewAdapter(Context context, List<String> titreRecettes, List<Integer> nbIngredients, List<Integer> imageRes, List<Integer> recettesId) {
        this.context = context;
        this.titreRecettes = titreRecettes;
        this.nbIngredients = nbIngredients;
        this.imageRes = imageRes;
        this.recettesId = recettesId;
    }

    @NonNull
    @Override
    public RecetteViewAdapter.RecetteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recettes_detail_row, parent, false);

        return new RecetteViewAdapter.RecetteViewHolder(view, context, recettesId, titreRecettes);
    }

    @Override
    public void onBindViewHolder(@NonNull RecetteViewAdapter.RecetteViewHolder holder, int position) {
        holder.titreRecette.setText(titreRecettes.get(position));
        holder.imageRecette.setImageResource(imageRes.get(position));
        holder.nbIngredients.setText("Composé de " + nbIngredients.get(position).toString() + " Ingredients");

    }

    @Override
    public int getItemCount() {
        return titreRecettes.size();
    }

    public static class RecetteViewHolder extends RecyclerView.ViewHolder
    {
        private CardView recetteCard;

        TextView titreRecette;
        ImageView imageRecette;
        TextView nbIngredients;

        List<Integer> recettesId = new ArrayList<>();
        List<String> titreRecettes = new ArrayList<>();

        public RecetteViewHolder(@NonNull View itemView, Context ctx, List<Integer> recettesId, List<String> titreRecettes) {
            super(itemView);
            this.recettesId = recettesId;
            this.titreRecettes = titreRecettes;

            titreRecette = itemView.findViewById(R.id.NomRecettes);
            imageRecette = itemView.findViewById(R.id.imageRecette);
            nbIngredients = itemView.findViewById(R.id.NbIngredients);

            recetteCard = (CardView) itemView.findViewById(R.id.detailRecettesCard);
            recetteCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Intent it = new Intent(ctx, IngredientsActivity.class);
                    it.putExtra("recipeId",recettesId.get(position));
                    it.putExtra("recipeName",titreRecettes.get(position));
                    it.putExtra("position",position);
                    it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    ctx.startActivity(it);
                    //Toast.makeText(itemView.getContext(), "Working Fine" + recettesId.get(position) , Toast.LENGTH_SHORT).show();


                }
            });
        }


    }



}
