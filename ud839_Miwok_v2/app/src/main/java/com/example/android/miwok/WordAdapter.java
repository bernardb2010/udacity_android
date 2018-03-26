package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Class to extend array adapter to use two TextViews
 */
public class WordAdapter extends ArrayAdapter<Word> {

    private int mBackgroundColor;

    public WordAdapter(@NonNull Context context, @NonNull List<Word> objects, int bgColor) {
        super(context, 0, objects);
        mBackgroundColor = bgColor;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        RelativeLayout myLayout = convertView.findViewById(R.id.itemListView);
        myLayout.setBackgroundColor(ContextCompat.getColor(getContext(), mBackgroundColor));


        ((TextView) convertView.findViewById(R.id.miwokText))
                .setText(getItem(position).getMiwokTranslation());
        ((TextView) convertView.findViewById(R.id.englishText))
                .setText(getItem(position).getDefaultTranslation());

        if (getItem(position).hasImage()) {
            ((ImageView) convertView.findViewById(R.id.image))
                    .setImageResource(getItem(position).getImageResourceId());
            convertView.findViewById(R.id.image).setVisibility(View.VISIBLE);
        } else {
            convertView.findViewById(R.id.image).setVisibility(View.GONE);
        }
        return convertView;
    }
}
