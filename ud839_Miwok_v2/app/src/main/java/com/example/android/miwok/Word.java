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
    private int mImageResId = NO_IMAGE_PROVIDED;
    private int mAudioResId;

    /**
     * Default constructor for a Word object
     */
    public Word(String defaultTranslation, String miwokTranslation, int imageRes, int audioRes) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResId = imageRes;
        mAudioResId = audioRes;
    }

    public Word(String defaultTranslation, String miwokTranslation, int audioRes) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mAudioResId = audioRes;
    }

    /**
     * Returns the string representation of the {@link Word} object.
     */
    @Override
    public String toString() {
        return "Word{" +
                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", mAudioResourceId=" + mAudioResId +
                ", mImageResourceId=" + mImageResId +
                '}';
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
    public int getImageResourceId() {
        return mImageResId;
    }

    public boolean hasImage() {
        return mImageResId != NO_IMAGE_PROVIDED;
    }

    /**
     * Method to get resource Id of audio
     */
    public int getAudioResId() {
        return mAudioResId;
    }
}
