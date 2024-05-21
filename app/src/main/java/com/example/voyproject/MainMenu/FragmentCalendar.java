package com.example.voyproject.MainMenu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.voyproject.Calendar.CalendarAdapter;
import com.example.voyproject.Database.DatabaseHelper;
import com.example.voyproject.R;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FragmentCalendar extends Fragment implements CalendarAdapter.OnItemListener {

    Button lastMonth, nextMonth;
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        calendarRecyclerView = view.findViewById(R.id.calendarRecyclerView);
        monthYearText = view.findViewById(R.id.monthYearTV);

        selectedDate = LocalDate.now();
        setMonthView();

        lastMonth = view.findViewById(R.id.lastMonth);
        nextMonth = view.findViewById(R.id.nextMonth);

        lastMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousMonthAction();
            }
        });

        nextMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextMonthAction();
            }
        });

        return view;
    }

    private void setMonthView()
    {
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);
        ArrayList<String> kcals = allKcals(selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this, getActivity(), kcals);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(requireActivity().getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    private ArrayList<String> allKcals(LocalDate date)
    {
        DatabaseHelper db = new DatabaseHelper(getActivity());
        ArrayList<String> kcals = new ArrayList<>();

        YearMonth yearMonth = YearMonth.from(date);

        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        String query;
        float value;

        for(int i = 1; i <= 42; i++)
        {
            if(i <= dayOfWeek || i > daysInMonth + dayOfWeek)
            {
                kcals.add("");
            }
            else
            {
                query = yearMonth +"-"+ String.valueOf(i - dayOfWeek);
                value = db.getSum("kcal", "", query);
                if(value == 0f)
                    kcals.add(null);
                else
                    kcals.add(String.valueOf(value));

            }
        }

        return  kcals;
    }

    private ArrayList<String> daysInMonthArray(LocalDate date)
    {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);

        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for(int i = 1; i <= 42; i++)
        {
            if(i <= dayOfWeek || i > daysInMonth + dayOfWeek)
            {
                daysInMonthArray.add("");
            }
            else
            {
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }
        return  daysInMonthArray;
    }

    private String monthYearFromDate(LocalDate date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    public void previousMonthAction()
    {
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
    }

    public void nextMonthAction()
    {
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();
    }

    @Override
    public void onItemClick(int position, String kcalText, String dayText)
    {
        if(!dayText.equals(""))
        {
            String message = kcalText + "кКал - " + dayText + " " + monthYearFromDate(selectedDate);
            Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
        }
    }
}