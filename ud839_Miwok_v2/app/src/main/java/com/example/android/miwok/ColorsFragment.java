package com.example.android.miwok;


import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColorsFragment extends Fragment {

    private ArrayList<Word> colors;
    private ListView rootView;
    private ArrayAdapter<Word> words;
    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;
    private boolean isPaused = false;

    AudioManager.OnAudioFocusChangeListener afChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (mMediaPlayer != null) {
                        //pause
                        if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                            mMediaPlayer.pause();
                            isPaused = true;
                            //stop
                        } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                            mMediaPlayer.pause();
                            isPaused = true;
                            //pause
                        } else if (focusChange ==
                                AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                            mMediaPlayer.pause();
                            isPaused = true;
                            //resume
                        } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                            mMediaPlayer.start();
                            isPaused = false;
                        }
                    }
                }
            };
    private AudioFocusRequest mFocusRequest;
    private AudioAttributes mPlaybackAttributes;
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            mAudioManager.abandonAudioFocusRequest(mFocusRequest);
            releaseMediaPlayer();
        }
    };

    public ColorsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentRootView = inflater.inflate(R.layout.word_list, container, false);

        /** TODO: Insert all the code from the NumberActivity’s onCreate() method after the setContentView method call */
        rootView = fragmentRootView.findViewById(R.id.activityRootView);
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        mPlaybackAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build();
        mFocusRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)
                .setAudioAttributes(mPlaybackAttributes)
                .setAcceptsDelayedFocusGain(true)
                .setWillPauseWhenDucked(true)
                .setOnAudioFocusChangeListener(afChangeListener)
                .build();

        colors = new ArrayList<>(Arrays.asList(
                new Word("red", "weṭeṭṭi", R.drawable.color_red, R.raw.color_red),
                new Word("green", "chokokki", R.drawable.color_green, R.raw.color_green),
                new Word("brown", "ṭakaakki", R.drawable.color_brown, R.raw.color_brown),
                new Word("gray", "ṭopoppi", R.drawable.color_gray, R.raw.color_gray),
                new Word("black", "kululli", R.drawable.color_black, R.raw.color_black),
                new Word("white", "kelelli", R.drawable.color_white, R.raw.color_white),
                new Word("dust yellow", "ṭopiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow),
                new Word("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow)
        ));

        rootView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Get the {@link Word} object at the given position the user clicked on
                Word word = colors.get(position);

                // Create and setup the {@link MediaPlayer} for the audio resource associated
                // with the current word
                mMediaPlayer = MediaPlayer.create(getActivity(), word.getAudioResId());
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
                int audioFocus = mAudioManager.requestAudioFocus(mFocusRequest);
                if (audioFocus == 1) {
                    mMediaPlayer.start();
                }
            }
        });

        words = new WordAdapter(getActivity(), colors, R.color.category_colors);
        rootView.setAdapter(words);

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mMediaPlayer!= null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            isPaused = true;
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        if(isPaused){
            if(mMediaPlayer != null) {
                mMediaPlayer.start();
                isPaused = false;
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(getActivity());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }
}
