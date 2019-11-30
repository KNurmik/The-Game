package com.example.phase1activity.UI.MenuScreens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.phase1activity.Core.Transmission.Overseers.GlobalStats;
import com.example.phase1activity.Core.Transmission.Overseers.LeaderboardPresenter;
import com.example.phase1activity.Core.Transmission.Overseers.LeaderboardPresenterImpl;
import com.example.phase1activity.R;
import com.example.phase1activity.UI.Abstract.AbstractActivity;

import java.util.ArrayList;
import java.util.List;

/** A leaderboard view. */
public class LeaderboardViewImpl extends AbstractActivity implements LeaderboardView {
  /** An instance of a GlobalStats that updates sortedUsers */
  GlobalStats globalStats;

  /** This view's presenter. */
  LeaderboardPresenter presenter;

  /** The textview that displays the user that has the best high score. */
  TextView firstPlace;
  /** The textview that displays the user that has the second best high score. */
  TextView secondPlace;
  /** The textview that displays the user that has the third best high score. */
  TextView thirdPlace;
  /** The textview that displays the user that has the fourth best high score. */
  TextView fourthPlace;
  /** The textview that displays the user that has the fifth best high score. */
  TextView fifthPlace;
  /** A list of textviews that display the user scores. */
  List<TextView> userTextViews;

  /** The textview that displays the best high score. */
  TextView firstPlaceScore;
  /** The textview that displays the second best high score. */
  TextView secondPlaceScore;
  /** The textview that displays the third best high score. */
  TextView thirdPlaceScore;
  /** The textview that displays the fourth best high score. */
  TextView fourthPlaceScore;
  /** The textview that displays the fifth best high score. */
  TextView fifthPlaceScore;
  /** A list of textviews that display the user scores. */
  List<TextView> scoreTextViews;

  /** The textview that displays a description of the statistic the leaderboard is sorted by. */
  TextView statDescription;

  /**
   * Create references to all LeaderboardViewImpl view objects. Set the text of each TextView to
   * "-". Set button on-click listeners.
   *
   * @param savedInstanceState the saved instance state.
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_global_stats);
    globalStats = new GlobalStats(this);
    presenter = new LeaderboardPresenterImpl(this, new GlobalStats(this));

    firstPlace = findViewById(R.id.firstPlace);
    secondPlace = findViewById(R.id.secondPlace);
    thirdPlace = findViewById(R.id.thirdPlace);
    fourthPlace = findViewById(R.id.fourthPlace);
    fifthPlace = findViewById(R.id.fifthPlace);

    firstPlaceScore = findViewById(R.id.firstPlaceStat);
    secondPlaceScore = findViewById(R.id.secondPlaceStat);
    thirdPlaceScore = findViewById(R.id.thirdPlaceStat);
    fourthPlaceScore = findViewById(R.id.fourthPlaceStat);
    fifthPlaceScore = findViewById(R.id.fifthPlaceStat);

    statDescription = findViewById(R.id.statDescription);

    userTextViews = new ArrayList<>();
    userTextViews.add(firstPlace);
    userTextViews.add(secondPlace);
    userTextViews.add(thirdPlace);
    userTextViews.add(fourthPlace);
    userTextViews.add(fifthPlace);

    scoreTextViews = new ArrayList<>();
    scoreTextViews.add(firstPlaceScore);
    scoreTextViews.add(secondPlaceScore);
    scoreTextViews.add(thirdPlaceScore);
    scoreTextViews.add(fourthPlaceScore);
    scoreTextViews.add(fifthPlaceScore);

    for (int i = 0; i < userTextViews.size(); i++) {
      userTextViews.get(i).setText("-");
      scoreTextViews.get(i).setText("-");
    }

    statDescription.setText("");

    Button sortByScore = findViewById(R.id.sortByScore);
    Button sortByMoves = findViewById(R.id.sortByMoves);
    Button sortByReaction = findViewById(R.id.sortByReaction);

    sortByScore.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            sortByScore();
          }
        });

    sortByMoves.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            sortByMoves();
          }
        });

    sortByReaction.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            sortByReaction();
          }
        });

    Button backButton = findViewById(R.id.backButton);
    backButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            startActivity(new Intent(LeaderboardViewImpl.this, StartActivity.class));
            finish();
          }
        });
  }

  /** Set sortedUsers to a list of users, sorted by total score. */
  void sortByScore() {
    presenter.presentBestScores();
    String LEADERBOARD_SORT_MSG = "The leaderboard is sorted by: total score.";
    statDescription.setText(LEADERBOARD_SORT_MSG);
  }

  /** Set sortedUsers to a list of users, sorted by total moves. */
  void sortByMoves() {
    presenter.presentMostMoves();
    String LEADERBOARD_SORT_MSG = "The leaderboard is sorted by: total moves.";
    statDescription.setText(LEADERBOARD_SORT_MSG);
  }

  /** Set sortedUsers to a list of users, sorted by fastest reaction. */
  void sortByReaction() {
    presenter.presentFastestReactions();
    String LEADERBOARD_SORT_MSG = "The leaderboard is sorted by: reaction time.";
    statDescription.setText(LEADERBOARD_SORT_MSG);
  }

  /** Set the text of all TextView objects to the stats of their respective users in sortedUsers. */
  public void setTextFields(List<List<Object>> sortedUsers) {
    for (int i = 0; i < userTextViews.size(); i++) {
      if (i < sortedUsers.size()) {
        userTextViews.get(i).setText(sortedUsers.get(i).get(0).toString());
        scoreTextViews.get(i).setText(sortedUsers.get(i).get(1).toString());
      } else {
        userTextViews.get(i).setText("-");
        scoreTextViews.get(i).setText("-");
      }
    }
  }
}
