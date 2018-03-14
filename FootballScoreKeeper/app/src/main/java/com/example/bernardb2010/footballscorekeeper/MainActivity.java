package com.example.bernardb2010.footballscorekeeper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final int teamA = 0;
    private final int teamB = 1;
    private final int BOTHTEAMS = 2;

    private int teamAScore = 0;
    private int teamBScore = 0;
    private TextView teamATextView;
    private TextView teamBTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        teamATextView = findViewById(R.id.team_a_score);
        teamBTextView = findViewById(R.id.team_b_score);
    }

    public void updateScore(View v) {

        int teamToUpdate = -1;
        switch (v.getId()) {
            case R.id.tdButtonA:
                teamAScore += 6;
                teamToUpdate = teamA;
                break;
            case R.id.fgButtonA:
                teamAScore += 3;
                teamToUpdate = teamA;
                break;
            case R.id.extraPnTButtonA:
                teamAScore += 1;
                teamToUpdate = teamA;
                break;
            case R.id.twoPtConvButtonA:
                teamAScore += 2;
                teamToUpdate = teamA;
                break;
            case R.id.tdButtonB:
                teamBScore += 6;
                teamToUpdate = teamB;
                break;
            case R.id.fgButtonB:
                teamBScore += 3;
                teamToUpdate = teamB;
                break;
            case R.id.extraPnTButtonB:
                teamBScore += 1;
                teamToUpdate = teamB;
                break;
            case R.id.twoPtConvButtonB:
                teamBScore += 2;
                teamToUpdate = teamB;
                break;
            case R.id.resetButton:
                teamAScore = 0;
                teamBScore = 0;
                teamToUpdate = BOTHTEAMS;
        }
        displayScore(teamToUpdate);
    }

    private void displayScore(int teamToUpdate) {
        switch (teamToUpdate) {
            case teamA:
                teamATextView.setText(String.valueOf(teamAScore));
                break;
            case teamB:
                teamBTextView.setText(String.valueOf(teamBScore));
                break;
            case BOTHTEAMS:
                teamATextView.setText(String.valueOf(teamAScore));
                teamBTextView.setText(String.valueOf(teamBScore));
                break;
        }
    }
}
