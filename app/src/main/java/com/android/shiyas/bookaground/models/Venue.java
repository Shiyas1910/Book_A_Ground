package com.android.shiyas.bookaground.models;

/**
 * Created by Shiyas on 03-04-2019.
 */

public class Venue {

    private String venueName;
    private String venueLOcation;
    private String averageRating;
    private float ratingBarValue;
    private String publicRatingValue;
    private int courtImage;
    private boolean featured;
    private boolean bookable;

    public Venue(String venueName, String venueLOcation, String averageRating, float ratingBarValue, String publicRatingValue, int courtImage, boolean featured, boolean bookable) {
        this.venueName = venueName;
        this.venueLOcation = venueLOcation;
        this.averageRating = averageRating;
        this.ratingBarValue = ratingBarValue;
        this.publicRatingValue = publicRatingValue;
        this.courtImage = courtImage;
        this.featured = featured;
        this.bookable = bookable;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getVenueLOcation() {
        return venueLOcation;
    }

    public void setVenueLOcation(String venueLOcation) {
        this.venueLOcation = venueLOcation;
    }

    public String getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }

    public float getRatingBarValue() {
        return ratingBarValue;
    }

    public void setRatingBarValue(int ratingBarValue) {
        this.ratingBarValue = ratingBarValue;
    }

    public String getPublicRatingValue() {
        return publicRatingValue;
    }

    public void setPublicRatingValue(String publicRatingValue) {
        this.publicRatingValue = publicRatingValue;
    }

    public int getCourtImage() {
        return courtImage;
    }

    public void setCourtImage(int courtImage) {
        this.courtImage = courtImage;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public boolean isBookable() {
        return bookable;
    }

    public void setBookable(boolean bookable) {
        this.bookable = bookable;
    }
}
