package com.example.phase1activity.ui.matching_game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.phase1activity.R;
import com.example.phase1activity.ui.abstraction.AbstractActivity;

/** Activity where the user can choose to select the difficulty for MatchingGame. */
public class ChooseLevelActivity extends AbstractActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_choose_level);

    Button six = findViewById(R.id.level6);
    Button eight = findViewById(R.id.level8);
    Button ten = findViewById(R.id.level10);
    Button twelve = findViewById(R.id.level12);

    six.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            appManager.setMatchingGameLevel(6);
            startActivity(new Intent(ChooseLevelActivity.this, MatchingGameViewImpl.class));
          }
        });

    eight.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            appManager.setMatchingGameLevel(8);
            startActivity(new Intent(ChooseLevelActivity.this, MatchingGameViewImpl.class));
          }
        });

    ten.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            appManager.setMatchingGameLevel(10);
            startActivity(new Intent(ChooseLevelActivity.this, MatchingGameViewImpl.class));
          }
        });

    twelve.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            appManager.setMatchingGameLevel(12);
            startActivity(new Intent(ChooseLevelActivity.this, MatchingGameViewImpl.class));
          }
        });
  }
}
