package com.example.voyproject.AddFood;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.voyproject.Database.DatabaseHelper;
import com.example.voyproject.R;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder>{
    Context context;
    ArrayList food_id, food_name, food_kcal, food_protein, food_fat, food_carbo, food_gramm;
    ListAdapter(Context context,
                ArrayList food_id,
                ArrayList food_name,
                ArrayList food_kcal,
                ArrayList food_protein,
                ArrayList food_fat,
                ArrayList food_carbo,
                ArrayList food_gramm){
        this.context = context;
        this.food_id = food_id;
        this.food_name = food_name;
        this.food_kcal = food_kcal;
        this.food_protein = food_protein;
        this.food_fat = food_fat;
        this.food_carbo = food_carbo;
        this.food_gramm = food_gramm;
    }

    @NonNull
    @Override
    public ListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.MyViewHolder holder, final int position) {

        holder.food_name_text.setText(String.valueOf(food_name.get(position)));
        holder.food_kcal_text.setText(String.valueOf(food_kcal.get(position)) + " кКал");
        holder.food_gramm_text.setText(String.valueOf(food_gramm.get(position)) + " грамм");

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FoodDetailed.class);
                intent.putExtra("id", String.valueOf(food_id.get(position)));
                intent.putExtra("name", String.valueOf(food_name.get(position)));
                intent.putExtra("kcal", String.valueOf(food_kcal.get(position)));
                intent.putExtra("protein", String.valueOf(food_protein.get(position)));
                intent.putExtra("fat", String.valueOf(food_fat.get(position)));
                intent.putExtra("carbo", String.valueOf(food_carbo.get(position)));
                intent.putExtra("gramm", String.valueOf(food_gramm.get(position)));
            }
        });

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ListDay.class);
                intent.putExtra("id", String.valueOf(food_id.get(position)));
                intent.putExtra("name", String.valueOf(food_name.get(position)));
                intent.putExtra("kcal", String.valueOf(food_kcal.get(position)));
                intent.putExtra("protein", String.valueOf(food_protein.get(position)));
                intent.putExtra("fat", String.valueOf(food_fat.get(position)));
                intent.putExtra("carbo", String.valueOf(food_carbo.get(position)));
                intent.putExtra("gramm", String.valueOf(food_gramm.get(position)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return food_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView food_name_text, food_kcal_text, food_gramm_text;
        Button button;
        CardView mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            food_name_text = itemView.findViewById(R.id.listName);
            food_kcal_text = itemView.findViewById(R.id.listKcal);
            food_gramm_text = itemView.findViewById(R.id.listGramm);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            button = itemView.findViewById(R.id.btnAddProduct);
        }
    }
}
