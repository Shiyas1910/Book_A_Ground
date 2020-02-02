package com.android.shiyas.bookaground.activities;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.shiyas.bookaground.R;
import com.android.shiyas.bookaground.adapters.AvailableSportsAdapter;
import com.android.shiyas.bookaground.adapters.FacilityAdapter;
import com.android.shiyas.bookaground.models.AvailableSport;
import com.android.shiyas.bookaground.models.Facility;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission_group.CAMERA;

public class VenueDetailsActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener, View.OnClickListener {

    private AppBarLayout appBarLayout;
    private TextView venueMainLocation, seeMap;
    private CardView callNowButton, bookNowButton;
    private boolean isHideToolbarView = false;
    private List<AvailableSport> availableSports = new ArrayList<>();
    private RecyclerView availableRecyclerView;
    private AvailableSportsAdapter availableSportsAdapter;
    private List<Facility> facilities = new ArrayList<>();
    private RecyclerView venueFacilityRecyclerView;
    private FacilityAdapter facilityAdapter;
    int[] covers_carousel = new int[]{
            R.drawable.football,
            R.drawable.football1,
            R.drawable.football2,
            R.drawable.football3};
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_details);

        initialize();
    }

    private void initialize() {
        appBarLayout = findViewById(R.id.app_bar_layout);
        venueMainLocation = findViewById(R.id.venueMainLocation);

        seeMap = findViewById(R.id.seeMap);
        seeMap.setOnClickListener(this);

        callNowButton = findViewById(R.id.call_now_button);
        callNowButton.setOnClickListener(this);

        bookNowButton = findViewById(R.id.book_now_button);
        bookNowButton.setOnClickListener(this);

        CarouselView carouselView = findViewById(R.id.carouselView);

        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(covers_carousel[position]);
            }
        });
        carouselView.setPageCount(covers_carousel.length);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        createFacilitiesName();
        createAvailableSports();

        availableRecyclerView = findViewById(R.id.availableRecyclerView);
        availableSportsAdapter = new AvailableSportsAdapter(availableSports, this);
        availableRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        availableRecyclerView.setItemAnimator(new DefaultItemAnimator());
        LayoutAnimationController leftAnimation = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_from_left);
        availableRecyclerView.setLayoutAnimation(leftAnimation);
        availableRecyclerView.setNestedScrollingEnabled(false);
        availableRecyclerView.setAdapter(availableSportsAdapter);

        venueFacilityRecyclerView = findViewById(R.id.venueFacilityRecyclerView);
        facilityAdapter = new FacilityAdapter(facilities, this);
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(this);
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        flexboxLayoutManager.setJustifyContent(JustifyContent.FLEX_START);
        venueFacilityRecyclerView.setLayoutManager(flexboxLayoutManager);
        venueFacilityRecyclerView.setItemAnimator(new DefaultItemAnimator());
        venueFacilityRecyclerView.setLayoutAnimation(leftAnimation);
        venueFacilityRecyclerView.setNestedScrollingEnabled(false);
        venueFacilityRecyclerView.setAdapter(facilityAdapter);


        appBarLayout.addOnOffsetChangedListener(this);
    }

    private void callIntent() {
        try {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "9746199813"));
            startActivity(intent);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }


    private List<AvailableSport> createAvailableSports() {
        availableSports.add(new AvailableSport("Football", R.drawable.soccer_ball));
        availableSports.add(new AvailableSport("Badminton", R.drawable.badminton));
        availableSports.add(new AvailableSport("Basketball", R.drawable.baskteball));
        availableSports.add(new AvailableSport("Football", R.drawable.soccer_ball));
        availableSports.add(new AvailableSport("Badminton", R.drawable.badminton));
        availableSports.add(new AvailableSport("Basketball", R.drawable.baskteball));
        return availableSports;
    }

    private List<Facility> createFacilitiesName() {
        facilities.add(new Facility("Parking"));
        facilities.add(new Facility("Drinking water"));
        facilities.add(new Facility("Restroom"));
        facilities.add(new Facility("Washroom"));
        facilities.add(new Facility("Toilet"));
        facilities.add(new Facility("Power backup"));
        facilities.add(new Facility("Parking"));
        facilities.add(new Facility("Drinking water"));
        facilities.add(new Facility("Restroom"));
        facilities.add(new Facility("Washroom"));
        facilities.add(new Facility("Toilet"));
        facilities.add(new Facility("Power backup"));
        return facilities;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(offset) / (float) maxScroll;

        venueMainLocation.setAlpha((float) (1.0 - percentage));

        if (percentage == 1f && isHideToolbarView) {
            venueMainLocation.setVisibility(View.GONE);
            isHideToolbarView = !isHideToolbarView;

        } else if (percentage < 1f && !isHideToolbarView) {
            venueMainLocation.setVisibility(View.VISIBLE);
            isHideToolbarView = !isHideToolbarView;
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callIntent();
            } else {
                new AlertDialog.Builder(this)
                        .setMessage("Permission denied. Please go to Settings to allow access.")
                        .setPositiveButton("OK", null)
                        .create()
                        .show();
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.book_now_button:
                startActivity(new Intent(this, SlotBookingActivity.class));
                break;
            case R.id.call_now_button:
                if (ActivityCompat.checkSelfPermission(this, CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            CALL_PHONE)) {
                        showMessageOKCancel("You need to allow access to phone permissions.",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                            requestPermissions(new String[]{CALL_PHONE},
                                                    MY_PERMISSIONS_REQUEST_CALL_PHONE);
                                        }
                                    }
                                });
                        return;
                    } else {
                        ActivityCompat.requestPermissions(this, new String[]{CALL_PHONE},
                                MY_PERMISSIONS_REQUEST_CALL_PHONE);
                    }
                } else {
                    callIntent();
                }
                break;
            case R.id.seeMap:
                startActivity(new Intent(this, VenueMapActivity.class));
        }
    }
}