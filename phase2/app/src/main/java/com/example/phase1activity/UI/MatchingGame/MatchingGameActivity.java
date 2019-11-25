package com.example.phase1activity.UI.MatchingGame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phase1activity.Core.Transmission.MatchingGame.MatchingGameModule;
import com.example.phase1activity.Core.Transmission.MatchingGame.MatchingGamePresenterInterface;
import com.example.phase1activity.R;
import com.example.phase1activity.UI.Abstract.AbstractActivities;
import com.example.phase1activity.UI.MazeGame.MazeMenuActivity;
import com.example.phase1activity.UI.MenuScreens.StartActivity;

import com.example.phase1activity.Core.Transmission.MatchingGame.DaggerMatchingGameComponent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/** A MatchingGameActivity. */
public class MatchingGameActivity extends AbstractActivities
    implements View.OnClickListener, MatchingGameActivityInterface {

  /**
   * Presenter responsible for handling user actions. Injected using Dagger dependency injection.
   */
  @Inject MatchingGamePresenterInterface presenter;

  /** The string to be displayed on the back of each card. */
  public static final String BACKOFCARD = "CLICK ME!";

  /** The string to be displayed next to the number of turns taken. */
  static final String TURNSTAKEN = "Turns Taken: ";

  /** The View that displays the user's stats. */
  TextView statDisplay;

  /** A button that allows the user to advance to next level, after all matches are made. */
  Button advanceToNextLevel;

  /** A button that allows the user to advance to next level, anytime. */
  Button skipLevel;

  /** A view that display's the user's nickname. */
  TextView nickname;

  /** A pop-up that notifies the user of a failed match. */
  ImageView popUp;

  Button menu;

  List<Button> buttonList;

  /** The user's selected colour in their profile */
  int colour;

  /**
   * Set content view to this activity. Randomly assign values to this activity's cards, and store
   * the resulting assignments in cardsToValues. Set the initial appearances of this activity's View
   * objects.
   *
   * @param savedInstanceState the saved instance state of the activity, if any.
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_matching_game);

    initializeViews();
    populateButtonList();

    // Inject presenter.
    presenter =
        DaggerMatchingGameComponent.builder()
            .matchingGameModule(new MatchingGameModule(this, buttonList))
            .build()
            .injectMatchingGamePresenter();

    setInitialButtonAppearances();

    String userNickname = app.getProfile().getNickname();
    setDisplayNickname(userNickname);

    final Activity activity = this;

    advanceToNextLevel.setOnClickListener(this);

    menu.setOnClickListener(
        new View.OnClickListener() {
          /** Allow user to continue to the game using the button. */
          @Override
          public void onClick(View v) {
            startActivity(new Intent(MatchingGameActivity.this, StartActivity.class));
          }
        });

    skipLevel.setOnClickListener(
        new View.OnClickListener() {
          /** Allow user to continue to the game using the button. */
          @Override
          public void onClick(View v) {
            app.getProfile().setGameLevel(activity, 2);
            startActivity(new Intent(MatchingGameActivity.this, MazeMenuActivity.class));
          }
        });

    advanceToNextLevel.setOnClickListener(
        new View.OnClickListener() {
          /** Allow user to continue to the game using the button. */
          @Override
          public void onClick(View v) {
            app.getProfile().setGameLevel(activity, 2);
            startActivity(new Intent(MatchingGameActivity.this, MazeMenuActivity.class));
          }
        });
  }

  /**
   * Set the displayed nickname to be displayed to newNickname.
   *
   * @param newNickname the new nickname to be displayed.
   */
  void setDisplayNickname(String newNickname) {
    String displayText = "Hi " + newNickname + "!";
    nickname.setText(displayText);
  }

  /** Set initial appearances of the level's buttons. */
  private void setInitialButtonAppearances() {
    colour = getAppManager().getProfileColour();
    colourButton(menu, R.drawable.main_red, R.drawable.main_blue, R.drawable.main_green);
    colourButton(skipLevel, R.drawable.next_red, R.drawable.next_blue, R.drawable.next_green);
    colourButton(
        advanceToNextLevel, R.drawable.next_red, R.drawable.next_blue, R.drawable.next_green);

    String statDisplayText = TURNSTAKEN + 0;
    statDisplay.setText(statDisplayText);

    // TODO: decide if nickname is necessary here
    nickname.setVisibility(View.INVISIBLE);

    hideNoMatchPopup();

    for (Button card : buttonList) {
      card.setOnClickListener(this);
      card.setText(BACKOFCARD);
      colourButton(card, R.drawable.square_red, R.drawable.square_blue, R.drawable.square_green);
    }
  }

  /** Populate buttonList with the buttons representing cards. */
  private void populateButtonList() {
    buttonList = new ArrayList<>();

    // Initialize card buttons.
    final Button button1 = findViewById(R.id.button1);
    buttonList.add(button1);
    final Button button2 = findViewById(R.id.button2);
    buttonList.add(button2);
    final Button button3 = findViewById(R.id.button3);
    buttonList.add(button3);
    final Button button4 = findViewById(R.id.button4);
    buttonList.add(button4);
    final Button button5 = findViewById(R.id.button5);
    buttonList.add(button5);
    final Button button6 = findViewById(R.id.button6);
    buttonList.add(button6);
  }

  private void initializeViews() {
    popUp = findViewById(R.id.noMatch);
    skipLevel = findViewById(R.id.nextLevel);
    nickname = findViewById(R.id.hello);
    advanceToNextLevel = findViewById(R.id.advanceButton);
    statDisplay = findViewById(R.id.statDisplay);
    menu = findViewById(R.id.menu1);
  }

  /**
   * Set the statistic to be displayed to statDisplayText.
   *
   * @param statDisplayText a statistic.
   */
  public void setDisplayStat(String statDisplayText) {
    statDisplay.setText(statDisplayText);
  }

  /** Move the next level button to the centre of the screen. */
  public void updateNextLevelButton() {
    advanceToNextLevel.setVisibility(View.VISIBLE);
    skipLevel.setVisibility(View.INVISIBLE);
  }

  /**
   * Record that a card was clicked in manager.
   *
   * @param view the card that was clicked.
   */
  @Override
  public void onClick(View view) {
    popUp.setVisibility(View.INVISIBLE);
    Button button = (Button) view;
    presenter.handleClick(button, app);
  }

  /** NEEDS DESC.  */
  public void showNoMatchPopup() {
    popUp.setVisibility(View.VISIBLE);
  }

  /** NEEDS DESC.  */
  public void hideNoMatchPopup() {
      popUp.setVisibility(View.INVISIBLE);
  }
}
