/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Intent launchActivityIntent;
    private TextView numberTextView;
    private TextView colorsTextView;
    private TextView phrasesTextView;
    private TextView familyTextView;
    private OnClickListener onClickListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);
        getComponents();
        setOnClickListeners();

    }

    private void setOnClickListeners() {

        onClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.numbers:
                        launchActivityIntent = new Intent(v.getContext(), NumbersActivity.class);
                        break;
                    case R.id.colors:
                        launchActivityIntent = new Intent(v.getContext(), ColorsActivity.class);
                        break;
                    case R.id.family:
                        launchActivityIntent = new Intent(v.getContext(), FamilyActivity.class);
                        break;
                    case R.id.phrases:
                        launchActivityIntent = new Intent(v.getContext(), PhrasesActivity.class);
                        break;
                }
                startActivity(launchActivityIntent);
            }
        };

        numberTextView.setOnClickListener(onClickListener);
        colorsTextView.setOnClickListener(onClickListener);
        familyTextView.setOnClickListener(onClickListener);
        phrasesTextView.setOnClickListener(onClickListener);
    }

    private void getComponents() {
        numberTextView = findViewById(R.id.numbers);
        colorsTextView = findViewById(R.id.colors);
        phrasesTextView = findViewById(R.id.phrases);
        familyTextView = findViewById(R.id.family);
    }

}
