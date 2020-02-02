package com.android.shiyas.bookaground.activities;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.android.shiyas.bookaground.R;
import com.android.shiyas.bookaground.adapters.AvailableCourtAdapter;
import com.android.shiyas.bookaground.adapters.BookingTimeAdapter;
import com.android.shiyas.bookaground.models.AvailableCourt;
import com.android.shiyas.bookaground.models.BookingTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SlotBookingActivity extends AppCompatActivity {

    private EditText dateSettingEditText;
    final Calendar myCalendar = Calendar.getInstance();
    List<BookingTime> bookingTimeList = new ArrayList<>();
    List<AvailableCourt> availableCourts = new ArrayList<>();
    RecyclerView bookingTimeRecyclerView, bookingCourtRecyclerView;
    BookingTimeAdapter bookingTimeAdapter;
    AvailableCourtAdapter availableCourtAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot_booking);

        bookingTimeList = createBookingTimeList();
        availableCourts = createAvailableCourts();
        initialize();
    }

    private List<AvailableCourt> createAvailableCourts() {
        availableCourts.add(new AvailableCourt("5X5 Ground 1"));
        availableCourts.add(new AvailableCourt("5X5 Ground 2"));
        availableCourts.add(new AvailableCourt("5X5 Ground 3"));
        return availableCourts;
    }

    private List<BookingTime> createBookingTimeList() {
        bookingTimeList.add(new BookingTime("5 AM", false, true));
        bookingTimeList.add(new BookingTime("6 AM", false, true));
        bookingTimeList.add(new BookingTime("7 AM", true, false));
        bookingTimeList.add(new BookingTime("8 AM", false, false));
        bookingTimeList.add(new BookingTime("9 AM", false, false));
        bookingTimeList.add(new BookingTime("10 AM", false, false));
        bookingTimeList.add(new BookingTime("11 AM", false, false));
        bookingTimeList.add(new BookingTime("12 PM", false, false));
        bookingTimeList.add(new BookingTime("1 PM", false, false));
        bookingTimeList.add(new BookingTime("2 PM", false, false));
        bookingTimeList.add(new BookingTime("3 PM", false, false));
        bookingTimeList.add(new BookingTime("4 PM", true, false));
        bookingTimeList.add(new BookingTime("5 PM", false, false));
        bookingTimeList.add(new BookingTime("6 PM", true, true));
        bookingTimeList.add(new BookingTime("7 PM", true, true));
        bookingTimeList.add(new BookingTime("8 PM", true, true));
        bookingTimeList.add(new BookingTime("9 PM", false, true));
        bookingTimeList.add(new BookingTime("10 PM", false, true));
        bookingTimeList.add(new BookingTime("11 PM", false, true));
        bookingTimeList.add(new BookingTime("12 AM", false, false));
        return bookingTimeList;
    }

    private void initialize() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        dateSettingEditText = findViewById(R.id.date_field1);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        dateSettingEditText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(SlotBookingActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });

        bookingTimeRecyclerView = findViewById(R.id.bookingTimeRecyclerView);
        bookingTimeAdapter = new BookingTimeAdapter(bookingTimeList, this, getCurrentHourIn24HourFormat());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        bookingTimeRecyclerView.setLayoutManager(mLayoutManager);
        bookingTimeRecyclerView.getLayoutManager().scrollToPosition(getPositionFromHour());
        bookingTimeRecyclerView.setNestedScrollingEnabled(false);
        bookingTimeRecyclerView.setAdapter(bookingTimeAdapter);

        bookingCourtRecyclerView = findViewById(R.id.bookingCourtRecyclerView);
        availableCourtAdapter = new AvailableCourtAdapter(availableCourts, this);
        bookingCourtRecyclerView.setLayoutManager((new GridLayoutManager(this, 2)));
        bookingCourtRecyclerView.setNestedScrollingEnabled(false);
        bookingCourtRecyclerView.setAdapter(availableCourtAdapter);
    }

    private int getPositionFromHour() {
        // TODO : 12PM issue fix
        for (BookingTime bookingTime : bookingTimeList) {
            if (bookingTime.getTime().equals(getConvertedTime(getCurrentHourIn24HourFormat()))) {
                return bookingTimeList.indexOf(bookingTime) + 1;
            }
        }
        return 0;
    }

    private String getConvertedTime(int currentHourIn24Hours) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("H");
            Date dateObj = sdf.parse(String.valueOf(currentHourIn24Hours));
            return new SimpleDateFormat("K a").format(dateObj);
        } catch (ParseException e) {
            e.printStackTrace();
            return "Error";
        }
    }

    private int getCurrentHourIn24HourFormat() {
        Calendar rightNow = Calendar.getInstance();
        return rightNow.get(Calendar.HOUR_OF_DAY);
    }

    private void updateLabel() {
        String myFormat = "dd MMMM yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dateSettingEditText.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
