package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class PhrasesActivity extends AppCompatActivity {

    private ArrayList<Word> phrases;
    private ListView rootView;
    private ArrayAdapter<Word> words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        rootView = findViewById(R.id.activityRootView);

        phrases = new ArrayList<>(Arrays.asList(
                new Word("Where are you going?", "minto wuksus"),
                new Word("What is your name?", "tinnә oyaase'nә"),
                new Word("My name is...", "oyaaset..."),
                new Word("How are you feeling?", "michәksәs?"),
                new Word("I’m feeling good.", "kuchi achit"),
                new Word("Are you coming?", "әәnәs'aa?"),
                new Word("Yes, I’m coming.", "hәә’ әәnәm"),
                new Word("I’m coming.", "әәnәm"),
                new Word("Let’s go.", "yoowutis"),
                new Word("Come here.", "әnni'nem")
        ));

        words = new WordAdapter(this, phrases, R.color.category_phrases);
        rootView.setAdapter(words);

    }


}
