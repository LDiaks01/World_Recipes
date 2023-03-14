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

import com.example.worldrecipes.R;
import com.example.worldrecipes.RecettesDetailActivity;
import com.example.worldrecipes.RecyclerViewAdapter;
import com.example.worldrecipes.data.AppDAO;
import com.example.worldrecipes.data.Recipes;

import java.util.ArrayList;
import java.util.List;

public class CategoryViewAdapter extends RecyclerView.Adapter<CategoryViewAdapter.CategoryViewHolder>{

    Context context;
    public List<String> categories;
    public List<Integer> nombredeRecettes;
    public List<Integer> imageRes;


    public CategoryViewAdapter(Context context, List<String> categories, List<Integer> nbRecettes, List<Integer> imageRes) {
        this.context = context;
        this.categories = categories;
        this.nombredeRecettes = nbRecettes;
        this.imageRes = imageRes;
    }


    @NonNull
    @Override
    public CategoryViewAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.category_view_row, parent, false);

        return new CategoryViewAdapter.CategoryViewHolder(view, categories, context);

    }


    @Override
    public void onBindViewHolder(@NonNull CategoryViewAdapter.CategoryViewHolder holder, int position) {

            holder.titreRecettes.setText(categories.get(position));
            holder.nbRecettes.setText(nombredeRecettes.get(position).toString() + " Recettes");
            holder.imageGauche.setImageResource(imageRes.get(position));




    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder{

        private CardView categoryCard;
        ImageView imageGauche;
        TextView titreRecettes, nbRecettes;
        List<String> test = new ArrayList<>();

        public CategoryViewHolder(@NonNull View itemView, List<String> test, Context ctx) {
            super(itemView);
            this.test = test;

            categoryCard = (CardView) itemView.findViewById(R.id.categoryCard);
            categoryCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Intent it = new Intent(ctx, RecettesDetailActivity.class);
                    it.putExtra("cuisine",test.get(position));
                    it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    ctx.startActivity(it);
                    //Toast.makeText(itemView.getContext(), "Working Fine" + test.get(position) , Toast.LENGTH_SHORT).show();

                }
            });

            imageGauche = (ImageView) itemView.findViewById(R.id.imageGauche);

            titreRecettes = itemView.findViewById(R.id.titreRecettes);
            nbRecettes = itemView.findViewById(R.id.nbRecettes);
        }
    }
}
