package com.android.shiyas.bookaground.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.shiyas.bookaground.R;
import com.android.shiyas.bookaground.models.AvailableCourt;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by mohamed on 17-04-2019.
 */

public class AvailableCourtAdapter extends RecyclerView.Adapter<AvailableCourtAdapter.MyViewHolder> {

    private WeakReference<Context> mContextWeakReference;
    private List<AvailableCourt> availableCourts;
    private int selectedPosition = -1;

    public AvailableCourtAdapter(List<AvailableCourt> availableCourts, Context context) {
        this.availableCourts= availableCourts;
        this.mContextWeakReference = new WeakReference<>(context);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView courtName;
        private RelativeLayout courtLayout;

        private MyViewHolder(View view) {
            super(view);
            courtName = view.findViewById(R.id.select_court_title);
            courtLayout = view.findViewById(R.id.select_court_layout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedPosition = getAdapterPosition();
                    notifyDataSetChanged();
                }
            });
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_court, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final AvailableCourt availableCourt = availableCourts.get(position);

        if (position == selectedPosition) {
            holder.courtName.setTextColor(mContextWeakReference.get().getResources().getColor(android.R.color.white));
            holder.courtLayout.setBackgroundResource(R.drawable.green_rectangle);
        } else {
            holder.courtName.setTextColor(mContextWeakReference.get().getResources().getColor(R.color.green));
            holder.courtLayout.setBackgroundResource(R.drawable.green_rectangle_border);
        }

        holder.courtName.setText(availableCourt.getCourtName());
    }

    @Override
    public int getItemCount() {
        return availableCourts.size();
    }

}