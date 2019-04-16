package ru.onyanov.accessibletimetable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.onyanov.accessibletimetable.entity.Timetable;
import ru.onyanov.accessibletimetable.network.AtAPI;

public class MainActivity extends AppCompatActivity implements Callback<List<Timetable>> {

    private static final String TAG = "MainActivity";
    private static final String BASE_URL = "https://raw.githubusercontent.com/onyanov/Accessibletimetable/master/samples/";

    private TimetableItemAdapter adapter;
    private RecyclerView timetableList;
    private List<Timetable> timetables;
    private Timetable currentTimetable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new TimetableItemAdapter(this);
        timetableList = findViewById(R.id.timetable_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        timetableList.setLayoutManager(layoutManager);
        timetableList.setAdapter(adapter);

        load();
    }


    public void load() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AtAPI atAPI = retrofit.create(AtAPI.class);

        Log.d(TAG, "load: ");

        atAPI.loadTimetable().enqueue(this);

    }

    @Override
    public void onResponse(Call<List<Timetable>> call, Response<List<Timetable>> response) {
        Log.d(TAG, "onResponse: ");
        if (response.isSuccessful()) {
            Log.d(TAG, "onResponse: " + new Gson().toJson(response.body()));
            timetables = response.body();
            currentTimetable = timetables.get(0);
            adapter.setData(currentTimetable.items);
        }
    }

    @Override
    public void onFailure(Call<List<Timetable>> call, Throwable t) {
        Log.d(TAG, "onFailure: ");
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
