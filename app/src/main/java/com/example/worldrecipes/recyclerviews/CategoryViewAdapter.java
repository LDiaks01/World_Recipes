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
import com.example.worldrecipes.RecyclerViewAdapter;
import com.example.worldrecipes.data.AppDAO;
import com.example.worldrecipes.data.Recipes;

import java.util.List;

public class CategoryViewAdapter extends RecyclerView.Adapter<CategoryViewAdapter.CategoryViewHolder>{

    Context context;
    List<String> categories;
    List<Integer> nombredeRecettes;
    List<Integer> imageRes;


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

        return new CategoryViewAdapter.CategoryViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull CategoryViewAdapter.CategoryViewHolder holder, int position) {

            holder.titreRecettes.setText(categories.get(position));
            holder.nbRecettes.setText(nombredeRecettes.get(position).toString());
            holder.imageGauche.setImageResource(imageRes.get(position));



    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder{

        ImageView imageGauche;
        TextView titreRecettes, nbRecettes;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            imageGauche = (ImageView) itemView.findViewById(R.id.imageGauche);

            titreRecettes = itemView.findViewById(R.id.titreRecettes);
            nbRecettes = itemView.findViewById(R.id.nbRecettes);
        }
    }
}
