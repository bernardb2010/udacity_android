package com.example.android.pets.data;

import com.example.android.pets.CatalogActivity;
import com.example.android.pets.EditorActivity;
import com.example.android.pets.data.PetContract.*;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.pets.R;

import static com.example.android.pets.data.PetContract.PetEntry.*;


public class PetCursorAdapter extends CursorAdapter {

    public PetCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.pet_list_item, parent, false);
    }

    @Override
    public void bindView(final View view, Context context, Cursor cursor) {
        TextView petName = view.findViewById(R.id.name);
        TextView petBreed = view.findViewById(R.id.summary);

        int colPetName = cursor.getColumnIndex(COLUMN_PET_NAME);
        int colPetBreed = cursor.getColumnIndex(COLUMN_PET_BREED);
       /* int colGender = cursor.getColumnIndex(COLUMN_PET_GENDER);
        int colWeight = cursor.getColumnIndex(COLUMN_PET_WEIGHT);
        int colId = cursor.getColumnIndex(_ID); */

        String name = cursor.getString(colPetName);
        String breed = cursor.getString(colPetBreed);


    /*    final int gender = cursor.getInt(colGender);
        final int weight = cursor.getInt(colWeight);
        final int id = cursor.getInt(colId);*/

        petName.setText(name);

        if(TextUtils.isEmpty(breed)){
            breed = "Unknown Breed";
        }
        petBreed.setText(breed);

    }
}
