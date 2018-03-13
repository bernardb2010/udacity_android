package com.example.bernardb2010.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    final int threePointShot = 3;
    final int twoPointShot = 2;
    final int freeThrow = 1;
    final int TEAM_A = 0;
    final int TEAM_B = 1;
    TextView teamATextView;
    TextView teamBTextView;

    int teamAScore = 0, teamBScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        teamATextView = findViewById(R.id.teamAScore);
        teamBTextView = findViewById(R.id.teamBScore);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * Displays the given score for the Team .
     *
     * @param team 0 for Team A, 1 for Team B
     */
    private void displayScoreForTeam(int team) {
        if (team == TEAM_A) {
            teamATextView.setText(String.valueOf(teamAScore));

        } else if (team == TEAM_B) {
            teamBTextView.setText(String.valueOf(teamBScore));
        }
    }

    /**
     * Update the score based on which button was pushed : +3 for 3 -point shot, +2 for two-point shot
     * +1 for free throw, or 0 for both teams if RESET
     *
     * @param view - the button that was pressed, Team A or Team B, number of points
     */
    public void updateScore(View view) {
        if (view.getId() == (R.id.teamA3)) {
            teamAScore += threePointShot;
            displayScoreForTeam(TEAM_A);
        } else if (view.getId() == (R.id.teamA2)) {
            teamAScore += twoPointShot;
            displayScoreForTeam(TEAM_A);
        } else if (view.getId() == (R.id.teamA1)) {
            teamAScore += freeThrow;
            displayScoreForTeam(TEAM_A);
        } else if (view.getId() == (R.id.teamB3)) {
            teamBScore += threePointShot;
            displayScoreForTeam(TEAM_B);
        } else if (view.getId() == (R.id.teamB2)) {
            teamBScore += twoPointShot;
            displayScoreForTeam(TEAM_B);
        } else if (view.getId() == (R.id.teamB1)) {
            teamBScore += freeThrow;
            displayScoreForTeam(TEAM_B);
        } else if (view.getId() == (R.id.resetButton)){
            teamAScore = teamBScore = 0;
            displayScoreForTeam(TEAM_A);
            displayScoreForTeam(TEAM_B);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
