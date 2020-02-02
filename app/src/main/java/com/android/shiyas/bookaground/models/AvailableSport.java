package com.android.shiyas.bookaground.models;

/**
 * Created by Shiyas on 07-04-2019.
 */

public class AvailableSport {

    private String sportName;
    private int sportIcon;

    public AvailableSport(String sportName, int sportIcon) {
        this.sportName = sportName;
        this.sportIcon = sportIcon;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public int getSportIcon() {
        return sportIcon;
    }

    public void setSportIcon(int sportIcon) {
        this.sportIcon = sportIcon;
    }
}
