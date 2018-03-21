package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class ColorsActivity extends AppCompatActivity {

    private ArrayList<Word> colors;
    private ListView rootView;
    private ArrayAdapter<Word> words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        rootView = findViewById(R.id.activityRootView);

        colors = new ArrayList<>(Arrays.asList(
                new Word("red", "weṭeṭṭi", R.drawable.color_red),
                new Word("green", "chokokki", R.drawable.color_green),
                new Word("brown", "ṭakaakki", R.drawable.color_brown),
                new Word("gray", "ṭopoppi", R.drawable.color_gray),
                new Word("black", "kululli", R.drawable.color_black),
                new Word("white", "kelelli", R.drawable.color_white),
                new Word("dust yellow", "ṭopiisә", R.drawable.color_dusty_yellow),
                new Word("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow)
        ));

        words = new WordAdapter(this, colors, R.color.primary_color);
        rootView.setAdapter(words);

    }
}
