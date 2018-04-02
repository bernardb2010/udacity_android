package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.text.TextUtils;

import java.io.IOException;
import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    private String mUrl = "";

    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    public List<Earthquake> loadInBackground() {

        if(TextUtils.isEmpty(mUrl)){
            return null;
        }
        try{
            return QueryUtils.extractEarthquakes(mUrl);
        } catch (IOException e){
            return null;
        }
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
