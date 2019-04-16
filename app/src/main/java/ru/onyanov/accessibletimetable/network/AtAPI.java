package ru.onyanov.accessibletimetable.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import ru.onyanov.accessibletimetable.entity.Timetable;

public interface AtAPI {

    @GET("day-timetable.json")
    Call<List<Timetable>> loadTimetable();

}
