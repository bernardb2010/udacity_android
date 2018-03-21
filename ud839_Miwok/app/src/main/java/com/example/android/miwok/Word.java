package com.example.android.miwok;

/**
 * This class stores english and miwok translations of a word
 */
public class Word {

    private static final int NO_IMAGE_PROVIDED = -1;
    /**
     * State variables
     */
    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mResId = NO_IMAGE_PROVIDED;

    /**
     * Default constructor for a Word object
     */
    public Word(String defaultTranslation, String miwokTranslation, int resId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mResId = resId;
    }

    public Word(String defaultTranslation, String miwokTranslation) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
    }

    /**
     * Method to get default translation
     */
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    /**
     * Method to get Miwok translation
     */
    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    /**
     * Method to get resource Id of image
     */
    public int getResourceId() {
        return mResId;
    }

    public boolean hasImage() {
        return mResId != NO_IMAGE_PROVIDED;
    }
}
