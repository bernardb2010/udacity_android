package com.example.android.pets.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import static android.text.TextUtils.isEmpty;
import static com.example.android.pets.data.PetContract.*;
import static com.example.android.pets.data.PetContract.PetEntry.*;

public class PetProvider extends ContentProvider {

    private PetDbHelper petDbHelper;
    private final String LOG_TAG = getClass().getSimpleName();
    private static final int PETS = 100;
    private static final int PET_ID = 101;
    private static final int INSERT_FAIL = -1;
    private static UriMatcher petUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        petUriMatcher.addURI(CONTENT_AUTHORITY, PATH_PETS, PETS);
        petUriMatcher.addURI(CONTENT_AUTHORITY,PATH_PET_ROW , PET_ID);
    }

    @Override
    public boolean onCreate() {
        petDbHelper = new PetDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

       // Cursor queryResult;
        switch(petUriMatcher.match(uri)){
            case PETS:
                selection = null;
                selectionArgs = null;
                break;
            case PET_ID:
               selection = PetEntry._ID + "=?";
               selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown uri " + uri);
        }
        Cursor petProvider = petDbHelper.getReadableDatabase().query(TABLE_NAME, projection, selection, selectionArgs,
                null, null, sortOrder);
        petProvider.setNotificationUri(getContext().getContentResolver() ,uri);
        return petProvider;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        switch(petUriMatcher.match(uri)){
            case PETS:
                return CONTENT_LIST_TYPE;
            case PET_ID:
                return CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        switch(petUriMatcher.match(uri)){
            case PETS:
                return insertPet(uri, values);
            default:
            throw new IllegalArgumentException("Insertion not supported for " + uri);
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        switch(petUriMatcher.match(uri)){
            case PETS:
                return removeData(uri, selection, selectionArgs);
            case PET_ID:
                selection = PetEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                return removeData(uri, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Delete is not supported for " + uri);
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {

        switch(petUriMatcher.match(uri)){
            case PETS:
                return updatePet(uri, values, selection, selectionArgs);
            case PET_ID:
                selection = PetEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                return updatePet(uri, values, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }

    private Uri insertPet(Uri uri, ContentValues values) {

        if(!isValidData(values)){
            return null;
        }

        long id = petDbHelper.getWritableDatabase().insert(TABLE_NAME, null, values);
        if(id == INSERT_FAIL) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }

    private boolean isValidData(ContentValues values) {
        String name;
        int gender;
        Integer weight;

        if(values.containsKey(COLUMN_PET_NAME)){
            name = values.getAsString(COLUMN_PET_NAME);
            if(isEmpty(name)){
                throw new IllegalArgumentException("Pet requires a name");
            }
        }
        if(values.containsKey(COLUMN_PET_GENDER)){
            gender = values.getAsInteger(COLUMN_PET_GENDER);
            if(!isValidGender(gender)){
                throw new IllegalArgumentException("Gender must be Male, Female, or Unknown");
            }
        }
        if(values.containsKey(COLUMN_PET_WEIGHT)){
            weight = values.getAsInteger(COLUMN_PET_WEIGHT);
            if(weight != null && weight < 0){
                throw new IllegalArgumentException("Pet weight must be positive value.");
            }
        }

        return true;
    }

    private boolean isValidGender(int gender) {
        if(gender == GENDER_MALE || gender == GENDER_FEMALE || gender == GENDER_UNKNOWN){
            return true;
        }
        return false;
    }

    private int updatePet(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        if(values.size() == 0 || !isValidData(values)){
            return 0;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return petDbHelper.getWritableDatabase().update(TABLE_NAME, values, selection, selectionArgs);
    }

    private int removeData(Uri uri, String selection, String[] selectionArgs) {
        int rowsToDelete = petDbHelper.getWritableDatabase().delete(TABLE_NAME, selection, selectionArgs);

        if(rowsToDelete != 0){
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsToDelete;
    }
}
