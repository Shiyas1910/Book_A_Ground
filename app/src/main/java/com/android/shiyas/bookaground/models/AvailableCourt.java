package com.android.shiyas.bookaground.models;

/**
 * Created by mohamed on 17-04-2019.
 */

public class AvailableCourt {
    private String courtName;

    public AvailableCourt(String courtName) {
        this.courtName = courtName;
    }

    public String getCourtName() {
        return courtName;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }
}
