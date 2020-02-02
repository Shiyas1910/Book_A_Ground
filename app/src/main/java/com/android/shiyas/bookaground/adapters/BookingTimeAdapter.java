package com.android.shiyas.bookaground.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.shiyas.bookaground.R;
import com.android.shiyas.bookaground.models.BookingTime;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by mohamed on 16-04-2019.
 */

public class BookingTimeAdapter extends RecyclerView.Adapter<BookingTimeAdapter.MyViewHolder> {

    private WeakReference<Context> mContextWeakReference;
    private List<BookingTime> bookingTimeList;
    private int currentHour;

    public BookingTimeAdapter(List<BookingTime> bookingTimeList, Context context, int currentHour) {
        this.bookingTimeList = bookingTimeList;
        this.mContextWeakReference = new WeakReference<>(context);
        this.currentHour = currentHour;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView bookingTime;

        private MyViewHolder(View view) {
            super(view);
            bookingTime = view.findViewById(R.id.booking_time_text_view);
        }
    }


    @NonNull
    @Override
    public BookingTimeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_booking_time, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final BookingTime bookingTime = bookingTimeList.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!bookingTime.isBooked() && getTimeValue(bookingTime.getTime()) > currentHour) {
                    if (holder.itemView.getTag() == null) {
                        holder.bookingTime.setTextColor(mContextWeakReference.get().getResources().getColor(android.R.color.white));
                        holder.bookingTime.setBackgroundColor(mContextWeakReference.get().getResources().getColor(R.color.green));
                        holder.itemView.setTag(bookingTime);
                    } else {
                        if (bookingTime.isPrime()) {
                            holder.bookingTime.setTextColor(mContextWeakReference.get().getResources().getColor(android.R.color.white));
                            holder.bookingTime.setBackgroundColor(mContextWeakReference.get().getResources().getColor(R.color.pink));
                        } else {
                            holder.bookingTime.setTextColor(mContextWeakReference.get().getResources().getColor(R.color.black));
                            holder.bookingTime.setBackgroundResource(R.drawable.red_rectangle_border);
                        }
                        holder.itemView.setTag(null);
                    }
                }
            }
        });

        holder.bookingTime.setText(bookingTime.getTime());

        if (bookingTime.isBooked() && holder.itemView.getTag() == null) {
            holder.bookingTime.setTextColor(mContextWeakReference.get().getResources().getColor(android.R.color.white));
            holder.bookingTime.setBackgroundResource(R.drawable.red_rectangle);
        }
        if (!bookingTime.isBooked() && !bookingTime.isPrime() && holder.itemView.getTag() == null) {
            holder.bookingTime.setTextColor(mContextWeakReference.get().getResources().getColor(R.color.black));
            holder.bookingTime.setBackgroundResource(R.drawable.red_rectangle_border);
        }
        if (!bookingTime.isBooked() && bookingTime.isPrime() && holder.itemView.getTag() == null) {
            holder.bookingTime.setTextColor(mContextWeakReference.get().getResources().getColor(android.R.color.white));
            holder.bookingTime.setBackgroundResource(R.drawable.pink_rectangle);
        }
        if (getTimeValue(bookingTime.getTime()) <= currentHour) {
            holder.bookingTime.setTextColor(mContextWeakReference.get().getResources().getColor(android.R.color.white));
            holder.bookingTime.setBackgroundResource(R.drawable.black_rectangle);
        }
    }

    @Override
    public int getItemCount() {
        return bookingTimeList.size();
    }

    private int getTimeValue(String bookingTime) {
        String[] splitted = bookingTime.split("\\s+");
        if (splitted[1].equals("AM")) {
            if (splitted[0].equals("12")) {
                return 24;
            } else {
                return Integer.parseInt(splitted[0]);
            }
        } else {
            if (splitted[0].equals("12")) {
                return 12;
            } else {
                return Integer.parseInt(splitted[0]) + 12;
            }
        }
    }

//    private void getTimeValue() {
//        SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
//        SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
//        Date date = parseFormat.parse("10:30 PM");
//        System.out.println(parseFormat.format(date) + " = " + displayFormat.format(date));
//    }

}