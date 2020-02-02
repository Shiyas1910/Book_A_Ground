package com.android.shiyas.bookaground.models;

/**
 * Created by Shiyas on 08-04-2019.
 */

public class Facility {

    private String facilityName;

    public Facility(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }
}
