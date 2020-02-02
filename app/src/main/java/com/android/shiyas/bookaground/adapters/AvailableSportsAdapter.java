package com.android.shiyas.bookaground.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.shiyas.bookaground.R;
import com.android.shiyas.bookaground.models.AvailableSport;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Shiyas on 07-04-2019.
 */

public class AvailableSportsAdapter extends RecyclerView.Adapter<AvailableSportsAdapter.MyViewHolder> {

    private WeakReference<Context> mContextWeakReference;
    private List<AvailableSport> availableSports;

    public AvailableSportsAdapter(List<AvailableSport> availableSports, Context context) {
        this.availableSports= availableSports;
        this.mContextWeakReference = new WeakReference<>(context);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView sportName;
        private ImageView sportIcon;

        private MyViewHolder(View view) {
            super(view);
            sportIcon = view.findViewById(R.id.sportIcon);
            sportName = view.findViewById(R.id.sportName);
        }
    }


    @NonNull
    @Override
    public AvailableSportsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_available_sports, parent, false);

        return new AvailableSportsAdapter.MyViewHolder(itemView);
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    public void onBindViewHolder(@NonNull AvailableSportsAdapter.MyViewHolder holder, int position) {
        AvailableSport availableSport = availableSports.get(position);

        holder.sportName.setText(availableSport.getSportName());
        holder.sportIcon.setImageDrawable(mContextWeakReference.get().getResources().getDrawable(availableSport.getSportIcon()));
    }

    @Override
    public int getItemCount() {
        return availableSports.size();
    }

}