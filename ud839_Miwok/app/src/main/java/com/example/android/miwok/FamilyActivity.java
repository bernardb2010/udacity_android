package com.example.android.miwok;

import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class FamilyActivity extends AppCompatActivity {

    private ArrayList<Word> family;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        rootView = findViewById(R.id.activityRootView);
        mAudioManager = (AudioManager) getSystemService(this.AUDIO_SERVICE);

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


        family = new ArrayList<>(Arrays.asList(
                new Word("father", "әpә", R.drawable.family_father, R.raw.family_father),
                new Word("mother", "әṭa", R.drawable.family_mother, R.raw.family_mother),
                new Word("son", "angsi", R.drawable.family_son, R.raw.family_son),
                new Word("daughter", "tune", R.drawable.family_daughter, R.raw.family_daughter),
                new Word("older brother", "taachi", R.drawable.family_older_brother, R.raw.family_older_brother),
                new Word("younger brother", "chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother),
                new Word("older sister", "teṭe", R.drawable.family_older_sister, R.raw.family_older_sister),
                new Word("younger sister", "kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister),
                new Word("grandmother", "ama", R.drawable.family_grandmother, R.raw.family_grandmother),
                new Word("grandfather", "paapa", R.drawable.family_grandfather, R.raw.family_grandfather)
        ));

        rootView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Get the {@link Word} object at the given position the user clicked on
                Word word = family.get(position);

                // Create and setup the {@link MediaPlayer} for the audio resource associated
                // with the current word
                mMediaPlayer = MediaPlayer.create(FamilyActivity.this, word.getAudioResId());

                mMediaPlayer.setOnCompletionListener(mCompletionListener);
                int audioFocus = mAudioManager.requestAudioFocus(mFocusRequest);
                if (audioFocus == 1) {
                    mMediaPlayer.start();
                }
            }
        });

        words = new WordAdapter(this, family, R.color.category_family);
        rootView.setAdapter(words);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mMediaPlayer!= null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            isPaused = true;
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(isPaused){
            if(mMediaPlayer != null) {
                mMediaPlayer.start();
                isPaused = false;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMediaPlayer();
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
