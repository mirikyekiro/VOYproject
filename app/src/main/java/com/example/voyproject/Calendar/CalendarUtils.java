package com.example.voyproject.Calendar;

import android.util.Log;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class CalendarUtils {

    public static LocalDate selectedDate;
    public static String monthDayFromDate(LocalDate date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM y");
        return date.format(formatter);
    }

}
