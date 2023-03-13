package com.example.worldrecipes;
import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worldrecipes.data.Recipes;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    Context context;
    List<Recipes> recipesList;

    public RecyclerViewAdapter(Context context, List<Recipes> recipesList) {
        this.context = context;
        this.recipesList = recipesList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);
        return new RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.textView.setText(recipesList.get(position).cuisine);
        holder.imageView.setImageResource(R.drawable.logo2);

    }

    @Override
    public int getItemCount() {
        return recipesList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;
        View view;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageViewRow);
            textView = itemView.findViewById(R.id.textViewRow);
            view = itemView.findViewById(R.id.viewRow);
        }
    }
}


