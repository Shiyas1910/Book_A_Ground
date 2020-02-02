package com.android.shiyas.bookaground.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.shiyas.bookaground.R;
import com.android.shiyas.bookaground.activities.VenueDetailsActivity;
import com.android.shiyas.bookaground.interfaces.OnItemClickListener;
import com.android.shiyas.bookaground.models.Venue;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Shiyas on 03-04-2019.
 */

public class VenueAdapter extends RecyclerView.Adapter<VenueAdapter.MyViewHolder> {

    private WeakReference<Context> mContextWeakReference;
    private List<Venue> venues;
    private OnItemClickListener listener;

    public VenueAdapter(List<Venue> venues, Context context, OnItemClickListener listener) {
        this.venues= venues;
        this.mContextWeakReference = new WeakReference<>(context);
        this.listener = listener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView venueName, venueLocation, avgRating, publicReview, bookableLayout;
        private ImageView courtImage;
        private RatingBar ratingBar;
        private LinearLayout featuredLayout;
        private View containerLayout;

        private MyViewHolder(View view) {
            super(view);
            containerLayout = view;
            venueName = view.findViewById(R.id.venueName);
            venueLocation = view.findViewById(R.id.venueLocation);
            avgRating = view.findViewById(R.id.averageRatingTextView);
            publicReview = view.findViewById(R.id.publicRatingTextView);
            courtImage = view.findViewById(R.id.courtImage);
            ratingBar = view.findViewById(R.id.ratingBar);
            featuredLayout = view.findViewById(R.id.featuredLayout);
            bookableLayout = view.findViewById(R.id.bookableLayout);
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_venue, parent, false);
        return new MyViewHolder(itemView);
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final Venue venue = venues.get(position);

        holder.containerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(venues.get(position));
            }
        });

        holder.venueName.setText(venue.getVenueName());
        holder.venueLocation.setText(venue.getVenueLOcation());
        holder.avgRating.setText(venue.getAverageRating());
        holder.publicReview.setText(mContextWeakReference.get().getString(R.string.public_rating, venue.getPublicRatingValue()));
        holder.courtImage.setImageDrawable(mContextWeakReference.get().getResources().getDrawable(venue.getCourtImage()));
        holder.ratingBar.setRating(venue.getRatingBarValue());
        if (venue.isFeatured()) {
            holder.featuredLayout.setVisibility(View.VISIBLE);
        }
        if (venue.isBookable()) {
            holder.bookableLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return venues.size();
    }

}
