package com.example.bernardb2010.footballscorekeeper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final int teamA = 0;
    final int teamB = 1;

    int teamAScore = 0;
    int teamBScore = 0;
    TextView teamATextView;
    TextView teamBTextView;

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
        }
        displayScore(teamToUpdate);
    }

    private void displayScore(int teamToUpdate) {
        if(teamToUpdate == teamA){
            teamATextView.setText(String.valueOf(teamAScore));
        } else if(teamToUpdate == teamB){
            teamBTextView.setText(String.valueOf(teamBScore));
        }
    }
}
