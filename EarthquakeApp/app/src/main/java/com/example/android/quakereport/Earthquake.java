package com.example.android.quakereport;

public class Earthquake {

    private String mLocation;
    private long mDate;
    private double mMagnitude;
    private String mUrl;

    public Earthquake(String location, long date, double magnitude, String url){
        mLocation = location;
        mDate = date;
        mMagnitude = magnitude;
        mUrl = url;
    }

    public String getLocation() {
        return mLocation;
    }

    public long getDate() {
        return mDate;
    }

    public Double getMagnitude() {
        return mMagnitude;
    }

    public String getURL(){
        return mUrl;
    }
}
