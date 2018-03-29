package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.icu.text.DecimalFormat;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private final String NEAR_THE = "Near the";
    private static final String LOCATION_SEPARATOR = " of ";
    private long timeDate;
    private String supplimentalLocation = "";
    private String primaryLocation = "";
    private SimpleDateFormat sdf;
    private GradientDrawable magnitudeCircle;


    public EarthquakeAdapter(@NonNull Context context, @NonNull List<Earthquake> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }


        timeDate = getItem(position).getDate();
        Double currentMag = getItem(position).getMagnitude();
        String mag = getDecimalFormat(currentMag);

        TextView magnitudeTextView = convertView.findViewById(R.id.magnitudeTextView);
        magnitudeTextView.setText(mag);
         magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();
        int magnitudeColor = getMagnitudeColor(currentMag);

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        convertView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(getItem(position).getURL()));
                getContext().startActivity(i);
            }
        });

        getLocation(getItem(position).getLocation());

        ((TextView) convertView.findViewById(R.id.locationTextView1))
                .setText(supplimentalLocation);
        ((TextView) convertView.findViewById(R.id.locationTextView2))
                .setText(primaryLocation);

        ((TextView) convertView.findViewById(R.id.dateTextView))
                .setText(getDate());
        ((TextView) convertView.findViewById(R.id.timeTextView))
                .setText(getTime());

        return convertView;
    }

    private int getMagnitudeColor(Double currentMag) {
        int magnitudeColorResId;

        switch ((int)Math.floor(currentMag)){
            case 0:
            case 1:
                magnitudeColorResId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResId);
    }

    private String getDecimalFormat(Double magnitude) {
        DecimalFormat formatter = new DecimalFormat("0.0");
        return formatter.format(magnitude);
    }

    private void getLocation(String currentLocation){

        if (currentLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = currentLocation.split(LOCATION_SEPARATOR);
            supplimentalLocation = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];

        } else {
            supplimentalLocation = NEAR_THE;
            primaryLocation = currentLocation;
        }
    }

    private String getDate() {
        sdf = new SimpleDateFormat("LLL dd, yyyy");
        return sdf.format(new Date(timeDate));
    }

    private String getTime() {
        sdf = new SimpleDateFormat("h:mm a");
        return sdf.format(new Date(timeDate));
    }
}
