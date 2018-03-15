package com.example.bernardb2010.footballscorekeeper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final int TEAM_A = 0;
    private final int TEAM_B = 1;
    private final int BOTH_TEAMS = 2;

    private int teamAScore = 0;
    private int teamBScore = 0;
    private TextView teamAScoreBoard;
    private TextView teamBScoreBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        teamAScoreBoard = findViewById(R.id.team_a_score);
        teamBScoreBoard = findViewById(R.id.team_b_score);
    }

    public void updateScore(View v) {

        int scoreboardToUpdate = -1;
        switch (v.getId()) {
            case R.id.tdButtonA:
                teamAScore += 6;
                scoreboardToUpdate = TEAM_A;
                break;
            case R.id.fgButtonA:
                teamAScore += 3;
                scoreboardToUpdate = TEAM_A;
                break;
            case R.id.extraPnTButtonA:
                teamAScore += 1;
                scoreboardToUpdate = TEAM_A;
                break;
            case R.id.twoPtConvButtonA:
                teamAScore += 2;
                scoreboardToUpdate = TEAM_A;
                break;
            case R.id.tdButtonB:
                teamBScore += 6;
                scoreboardToUpdate = TEAM_B;
                break;
            case R.id.fgButtonB:
                teamBScore += 3;
                scoreboardToUpdate = TEAM_B;
                break;
            case R.id.extraPnTButtonB:
                teamBScore += 1;
                scoreboardToUpdate = TEAM_B;
                break;
            case R.id.twoPtConvButtonB:
                teamBScore += 2;
                scoreboardToUpdate = TEAM_B;
                break;
            case R.id.resetButton:
                teamAScore = 0;
                teamBScore = 0;
                scoreboardToUpdate = BOTH_TEAMS;
        }
        updateScoreBoard(scoreboardToUpdate);
    }

    private void updateScoreBoard(int teamToUpdate) {
        switch (teamToUpdate) {
            case TEAM_A:
                teamAScoreBoard.setText(String.valueOf(teamAScore));
                break;
            case TEAM_B:
                teamBScoreBoard.setText(String.valueOf(teamBScore));
                break;
            case BOTH_TEAMS:
                teamAScoreBoard.setText(String.valueOf(teamAScore));
                teamBScoreBoard.setText(String.valueOf(teamBScore));
                break;
        }
    }
}
