package com.example.bernardb2010.happybirthday;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer m_MediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_MediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.hp_theme);
        m_MediaPlayer.setScreenOnWhilePlaying(true);
        m_MediaPlayer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        m_MediaPlayer.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        m_MediaPlayer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        m_MediaPlayer.stop();
        m_MediaPlayer.release();
        m_MediaPlayer = null;
    }

}
