package ru.onyanov.accessibletimetable;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ru.onyanov.accessibletimetable.entity.TimetableItem;

class TimetableItemAdapter extends RecyclerView.Adapter<TimetableItemAdapter.ViewHolder> {
    private final TimePicHelper timePicHelper;
    private List<TimetableItem> data;
    private static final String TAG = "TimetableItemAdapter";

    public TimetableItemAdapter(Activity activity) {
        timePicHelper = new TimePicHelper();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.timetable_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        TimetableItem item = data.get(position);

        Log.d(TAG, "onBindViewHolder: " + item.action);

        String timeFile = timePicHelper.getTimeIcon(item.time);
        Picasso.get()
                .load("https://sclera.be/resources/pictos/"+timeFile).fit().centerCrop()
                //.error(R.drawable.ic_error_image)
                .into(holder.timeView);

        Picasso.get()
                .load("https://sclera.be/resources/pictos/"+item.action).fit().centerCrop()
                //.error(R.drawable.ic_error_image)
                .into(holder.actionView);


    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public void setData(List<TimetableItem> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView timeView;
        ImageView actionView;
        ImageView doneView;

        public ViewHolder(View itemView) {
            super(itemView);
            timeView = itemView.findViewById(R.id.time);
            actionView = itemView.findViewById(R.id.action);
            doneView = itemView.findViewById(R.id.button_done);
        }
    }
}
