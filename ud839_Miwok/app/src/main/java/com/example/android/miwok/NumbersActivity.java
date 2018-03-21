package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class NumbersActivity extends AppCompatActivity {

    private ArrayList<Word> numbers;
    private ListView rootView;
    private ArrayAdapter<Word> words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        rootView = findViewById(R.id.activityRootView);

        numbers = new ArrayList<>(Arrays.asList(
                new Word("one", "lutti", R.drawable.number_one),
                new Word("two", "otiiko", R.drawable.number_two),
                new Word("three", "tolookosu", R.drawable.number_three),
                new Word("four", "oyyisa", R.drawable.number_four),
                new Word("five", "massokka", R.drawable.number_five),
                new Word("six", "temmokka", R.drawable.number_six),
                new Word("seven", "kenekaku", R.drawable.number_seven),
                new Word("eight", "kawinta", R.drawable.number_eight),
                new Word("nine", "wo’e", R.drawable.number_nine),
                new Word("ten", "na’aacha", R.drawable.number_ten)
        ));

        words = new WordAdapter(this, numbers, R.color.category_numbers);
        rootView.setAdapter(words);

    }
}
