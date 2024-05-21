package com.example.voyproject.Calendar;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.voyproject.Database.DatabaseHelper;
import com.example.voyproject.R;

import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder>
{
    Context context;
    private final ArrayList<String> daysOfMonth, kcals;
    private final OnItemListener onItemListener;

    public CalendarAdapter(ArrayList<String> daysOfMonth, OnItemListener onItemListener, Context context, ArrayList<String> kcal)
    {
        this.daysOfMonth = daysOfMonth;
        this.kcals = kcal;
        this.onItemListener = onItemListener;
        this.context = context;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 0.166666666);
        return new CalendarViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position)
    {
        holder.dayOfMonth.setText(daysOfMonth.get(position));
        holder.kcal.setText(kcals.get(position));
        if(holder.kcal.getText().equals(""))
            holder.back.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount()
    {
        return daysOfMonth.size();
    }

    public interface  OnItemListener
    {
        void onItemClick(int position, String dayText, String kcalText);
    }
}
