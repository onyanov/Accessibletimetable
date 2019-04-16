package ru.onyanov.accessibletimetable.entity;

import com.google.gson.annotations.Expose;

import java.util.List;

public class Timetable {

    @Expose
    public String date;

    @Expose
    public List<TimetableItem> items = null;

}