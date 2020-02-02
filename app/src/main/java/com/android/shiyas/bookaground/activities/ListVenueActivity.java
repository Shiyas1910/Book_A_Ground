package com.android.shiyas.bookaground.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.android.shiyas.bookaground.R;
import com.android.shiyas.bookaground.adapters.VenueAdapter;
import com.android.shiyas.bookaground.interfaces.OnItemClickListener;
import com.android.shiyas.bookaground.models.Venue;

import java.util.ArrayList;
import java.util.List;

public class ListVenueActivity extends AppCompatActivity implements OnItemClickListener {

    List<Venue> venueList = new ArrayList<>();
    RecyclerView venueRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    VenueAdapter venueAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_venue);

        venueList = createVenueList();
        initialize();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void initialize() {
        venueRecyclerView = findViewById(R.id.venuesRecyclerView);
        venueAdapter = new VenueAdapter(venueList, this, new OnItemClickListener() {
            @Override
            public void onItemClick(Venue item) {
                startActivity(new Intent(ListVenueActivity.this, VenueDetailsActivity.class));
            }
        });
        mLayoutManager = new LinearLayoutManager(this);
        venueRecyclerView.setLayoutManager(mLayoutManager);
        venueRecyclerView.setItemAnimator(new DefaultItemAnimator());
        LayoutAnimationController leftAnimation = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_fall_down);
        venueRecyclerView.setLayoutAnimation(leftAnimation);
        venueRecyclerView.setNestedScrollingEnabled(false);
        venueRecyclerView.setAdapter(venueAdapter);
    }

    private List<Venue> createVenueList() {
        venueList.add(new Venue("Futsal Club", "Near Pangappara Bridge", "5.0", 5.0f, "13", R.drawable.football, true, true));
        venueList.add(new Venue("Kalikkalam Football Ground", "Opposite Infosys Gate", "3.8", 3.8f, "16", R.drawable.football1, false, true));
        venueList.add(new Venue("Club De", "Near KIMS Hospital", "5.0", 5.0f, "10", R.drawable.football2, false, false));
        venueList.add(new Venue("Friday FC Arena", "Kazhakuttom", "4.4", 4.4f, "56", R.drawable.football3, false, false));
        venueList.add(new Venue("Abhis Indoor Court", "Chalakuzhy Rd, Vivekanand Nagar", "5.0", 5.0f, "1", R.drawable.football, false, false));
        venueList.add(new Venue("Futsal Club", "Near Pangappara Bridge", "5.0", 5.0f, "13", R.drawable.football, true, true));
        venueList.add(new Venue("Kalikkalam Football Ground", "Opposite Infosys Gate", "3.8", 3.8f, "16", R.drawable.football1, false, false));
        venueList.add(new Venue("Club De", "Near KIMS Hospital", "5.0", 5.0f, "10", R.drawable.football2, false, true));
        venueList.add(new Venue("Friday FC Arena", "Kazhakuttom", "4.4", 4.4f, "56", R.drawable.football3, false, false));
        venueList.add(new Venue("Abhis Indoor Court", "Chalakuzhy Rd, Vivekanand Nagar", "5.0", 5.0f, "1", R.drawable.football, false, true));
        return venueList;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onItemClick(Venue item) {
        startActivity(new Intent(this, VenueDetailsActivity.class));
    }
}
