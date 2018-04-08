package com.example.android.pets;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import static com.example.android.pets.data.PetContract.PetEntry.*;

import com.example.android.pets.data.PetDbHelper;
import com.example.android.pets.data.PetProvider;


/**
 * Displays list of pets that were entered and stored in the app.
 */
public class CatalogActivity extends AppCompatActivity {

    public static final String LOG_TAG = CatalogActivity.class.getName();
    private static final String seperatorString = " - ";
    private PetDbHelper mDbHelper;
    private PetProvider petProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        mDbHelper = new PetDbHelper(this);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        displayDatabaseInfo();
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the pets database.
     */
    private void displayDatabaseInfo() {
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.

        String[] projection = new String[] {
                _ID,
                COLUMN_PET_NAME,
                COLUMN_PET_BREED,
                COLUMN_PET_GENDER,
                COLUMN_PET_WEIGHT
        };

        Cursor cursor = getContentResolver().query(
                CONTENT_URI,
                projection,
                null,
                null,
                null
        );

        try {
            // Display the number of rows in the Cursor (which reflects the number of rows in the
            // pets table in the database).
            TextView displayView = (TextView) findViewById(R.id.text_view_pet);
            displayView.setText("Number of rows in pets database table: " + cursor.getCount() + "\n\n");

            //Column indices
            int petIdCol = cursor.getColumnIndex(_ID);
            int petNameCol = cursor.getColumnIndex(COLUMN_PET_NAME);
            int petBreedCol = cursor.getColumnIndex(COLUMN_PET_BREED);
            int petGenderCol = cursor.getColumnIndex(COLUMN_PET_GENDER);
            int petWeightCol = cursor.getColumnIndex(COLUMN_PET_WEIGHT);

            //table header
            displayView.append(
                    _ID + seperatorString + COLUMN_PET_NAME + seperatorString + COLUMN_PET_BREED + seperatorString
                            + COLUMN_PET_GENDER + seperatorString + COLUMN_PET_WEIGHT + "\n\n");

            while(cursor.moveToNext()){
                //data
                displayView.append(
                        cursor.getInt(petIdCol) + seperatorString
                        + cursor.getString(petNameCol) + seperatorString
                        + cursor.getString(petBreedCol) + seperatorString
                        + getGender(cursor.getInt(petGenderCol)) + seperatorString
                        + cursor.getInt(petWeightCol) + "\n");
            }

        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    private String getGender(int gender) {

        String genderString = "";

        switch(gender){
            case GENDER_UNKNOWN:
                genderString = "Unknown";
                break;
            case GENDER_MALE:
                genderString = "Male";
                break;
            case GENDER_FEMALE:
                genderString = "Female";
                break;
        }

        return genderString;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertPet();
                displayDatabaseInfo();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void insertPet() {
        ContentValues values = new ContentValues();
        values.put(COLUMN_PET_NAME, "Toto");
        values.put(COLUMN_PET_BREED, "Terrier");
        values.put(COLUMN_PET_GENDER, GENDER_MALE);
        values.put(COLUMN_PET_WEIGHT, 7);
       long rowId = mDbHelper.getWritableDatabase().insert(TABLE_NAME, null, values);
       Log.d(LOG_TAG, "Row ID = " + rowId);
    }
}