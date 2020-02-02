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
import com.android.shiyas.bookaground.models.Facility;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Shiyas on 08-04-2019.
 */

public class FacilityAdapter extends RecyclerView.Adapter<FacilityAdapter.MyViewHolder> {

    private WeakReference<Context> mContextWeakReference;
    private List<Facility> facilities;

    public FacilityAdapter(List<Facility> facilities, Context context) {
        this.facilities= facilities;
        this.mContextWeakReference = new WeakReference<>(context);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView facilityName;

        private MyViewHolder(View view) {
            super(view);
            facilityName = view.findViewById(R.id.venueFacilityName);
        }
    }


    @NonNull
    @Override
    public FacilityAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_facility, parent, false);

        return new FacilityAdapter.MyViewHolder(itemView);
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    public void onBindViewHolder(@NonNull FacilityAdapter.MyViewHolder holder, int position) {
        Facility facility = facilities.get(position);

        holder.facilityName.setText(facility.getFacilityName());
    }

    @Override
    public int getItemCount() {
        return facilities.size();
    }

}