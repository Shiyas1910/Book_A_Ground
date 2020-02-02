package com.android.shiyas.bookaground.models;

/**
 * Created by mohamed on 16-04-2019.
 */

public class BookingTime {
    private String time;
    private boolean isBooked;
    private boolean isPrime;

    public BookingTime(String time, boolean isBooked, boolean isPrime) {
        this.time = time;
        this.isBooked = isBooked;
        this.isPrime = isPrime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public boolean isPrime() {
        return isPrime;
    }

    public void setPrime(boolean prime) {
        isPrime = prime;
    }
}
