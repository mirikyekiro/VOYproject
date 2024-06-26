package com.example.voyproject.AddFood;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.voyproject.Database.DatabaseHelper;
import com.example.voyproject.MainMenu.FragmentHome;
import com.example.voyproject.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class ProfileListAdapter extends RecyclerView.Adapter<ProfileListAdapter.MyViewHolder>{
        Context context;
        Activity activity;

        DatabaseHelper mydb;
        ArrayList food_id, food_name, food_kcal, food_protein, food_fat, food_carbo, food_gramm;
        ProfileListAdapter adapter = this;
        String textMeal;
        String dataLine;
        ProfileListAdapter(Activity activity,
                Context context,
                ArrayList food_id,
                ArrayList food_name,
                ArrayList food_kcal,
                ArrayList food_protein,
                ArrayList food_fat,
                ArrayList food_carbo,
                ArrayList food_gramm,
                String textMeal){
            this.activity = activity;
            this.context = context;
            this.food_id = food_id;
            this.food_name = food_name;
            this.food_kcal = food_kcal;
            this.food_protein = food_protein;
            this.food_fat = food_fat;
            this.food_carbo = food_carbo;
            this.food_gramm = food_gramm;
            this.textMeal = textMeal;
            }

        @NonNull
        @Override
        public ProfileListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.list_item_profile, parent, false);
            return new MyViewHolder(view);
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onBindViewHolder(@NonNull ProfileListAdapter.MyViewHolder holder, final int position) {

            holder.food_name_text.setText(String.valueOf(food_name.get(position)));
            holder.food_kcal_text.setText(String.valueOf(food_kcal.get(position)) + " кКал");
            holder.food_gramm_text.setText(String.valueOf(food_gramm.get(position)) + " грамм");
            holder.food_protein_text.setText(String.valueOf(food_protein.get(position)) + " белков");
            holder.food_fat_text.setText(String.valueOf(food_fat.get(position)) + " жиров");
            holder.food_carbo_text.setText(String.valueOf(food_carbo.get(position)) + " углеводов");

            holder.button.setOnClickListener(new View.OnClickListener() {
                DatabaseHelper db = new DatabaseHelper(context);
                @Override
                public void onClick(View v) {
                    db.deleteOneRowInProfile(String.valueOf(food_id.get(position)));
                    food_id.remove(food_id.get(position));

                }
            });
        }

    @Override
    public int getItemCount() {
        return food_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView food_name_text, food_kcal_text, food_gramm_text, food_protein_text, food_fat_text, food_carbo_text;
        Button button;
        CardView mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            food_name_text = itemView.findViewById(R.id.listName);
            food_kcal_text = itemView.findViewById(R.id.listKcal);
            food_gramm_text = itemView.findViewById(R.id.listGramm);
            food_protein_text = itemView.findViewById(R.id.listProtein);
            food_fat_text = itemView.findViewById(R.id.listFat);
            food_carbo_text = itemView.findViewById(R.id.listCarbo);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            button = itemView.findViewById(R.id.deleteItem);
        }
    }
}
