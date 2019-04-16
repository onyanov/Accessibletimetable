package ru.onyanov.accessibletimetable.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TimetableItem {

    @Expose
    public String time;

    @Expose
    public String action;

    @SerializedName("is_done")
    @Expose
    public boolean isDone;

}