package com.example.android.pets.data;

import static com.example.android.pets.CatalogActivity.LOG_TAG;
import static com.example.android.pets.data.PetContract.PetEntry.*;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class PetDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "shelter.db";
    public static final int DATABASE_VERSION = 1;

    public PetDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_PETS_TABLE = "CREATE TABLE " + TABLE_NAME +
                "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_PET_NAME + " TEXT NOT NULL," +
                COLUMN_PET_BREED + " TEXT," +
                COLUMN_PET_GENDER + " INTEGER NOT NULL," +
                COLUMN_PET_WEIGHT + " INTEGER NOT NULL DEFAULT 0)";
        Log.e(LOG_TAG, SQL_CREATE_PETS_TABLE);
        db.execSQL(SQL_CREATE_PETS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
