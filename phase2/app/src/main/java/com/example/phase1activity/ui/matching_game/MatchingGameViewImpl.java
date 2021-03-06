package com.example.phase1activity.ui.matching_game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phase1activity.R;
import com.example.phase1activity.ui.abstraction.AbstractActivity;
import com.example.phase1activity.ui.maze_game.MazeInstructionsActivity;
import com.example.phase1activity.ui.menu.StartActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;

/** Activity responsible for displaying MatchingGame. */
public class MatchingGameViewImpl extends AbstractActivity
    implements View.OnClickListener, MatchingGameView {

  /**
   * Presenter responsible for handling user actions. Injected using Dagger dependency injection.
   */
  @Inject MatchingGamePresenter presenter;

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

  /** The number of cards to be displayed on the screen at the beginning of the game. */
  int numCards;

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
    numCards = this.appManager.getMatchingGameLevel();

    initializeViews();
    populateButtonList();
    setInitialButtonAppearances();

    // Inject presenter.
    presenter =
        DaggerMatchingGameComponent.builder()
            .matchingGameModule(
                new MatchingGameModule(this, buttonList.subList(0, numCards), numCards))
            .build()
            .injectMatchingGamePresenter();

    final Activity activity = this;

    advanceToNextLevel.setOnClickListener(this);

    menu.setOnClickListener(
        new View.OnClickListener() {
          /** Allow user to continue to the game using the button. */
          @Override
          public void onClick(View v) {
            startActivity(new Intent(MatchingGameViewImpl.this, StartActivity.class));
          }
        });

    skipLevel.setOnClickListener(
        new View.OnClickListener() {
          /** Allow user to continue to the game using the button. */
          @Override
          public void onClick(View v) {
            appManager.setProfileGameLevel(activity, 2);
            startActivity(new Intent(MatchingGameViewImpl.this, MazeInstructionsActivity.class));
          }
        });

    advanceToNextLevel.setOnClickListener(
        new View.OnClickListener() {
          /** Allow user to continue to the game using the button. */
          @Override
          public void onClick(View v) {
            appManager.setProfileGameLevel(activity, 2);
            startActivity(new Intent(MatchingGameViewImpl.this, StartActivity.class));
          }
        });
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

    hideNoMatchPopup();

    for (Button card : buttonList) {
      card.setVisibility(View.VISIBLE);
      card.setOnClickListener(this);
      card.setText(BACKOFCARD);
      colourButton(card, R.drawable.square_red, R.drawable.square_blue, R.drawable.square_green);
    }

    for (int i = numCards; i < 12; i++) {
      buttonList.get(i).setVisibility(View.INVISIBLE);
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
    final Button button7 = findViewById(R.id.button7);
    buttonList.add(button7);
    final Button button8 = findViewById(R.id.button8);
    buttonList.add(button8);
    final Button button9 = findViewById(R.id.button9);
    buttonList.add(button9);
    final Button button10 = findViewById(R.id.button10);
    buttonList.add(button10);
    final Button button11 = findViewById(R.id.button11);
    buttonList.add(button11);
    final Button button12 = findViewById(R.id.button12);
    buttonList.add(button12);
  }

  /** Create refrences to objects on the screen. */
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
    presenter.handleClick(button, appManager);
  }

  /** Display popup that user did not get a matching pair. */
  public void showNoMatchPopup() {
    popUp.setVisibility(View.VISIBLE);
  }

  /** Hide the popup. */
  public void hideNoMatchPopup() {
    popUp.setVisibility(View.INVISIBLE);
  }

  /**
   * Hide the inputted buttons.
   *
   * @param buttons buttons that are faced up.
   */
  public void hideFaceUpButtons(List<Button> buttons) {
    for (Button card : buttons) {
      card.setVisibility(View.INVISIBLE);
    }
  }

  /** Flip all of the buttons that are faced up. */
  public void flipFaceUpButtons() {
    for (Button button : buttonList) {
      colourButton(button, R.drawable.square_red, R.drawable.square_blue, R.drawable.square_green);
      button.setText(BACKOFCARD);
    }
  }

  /**
   * Assign an image to the inputted button.
   *
   * @param button the button in question.
   * @param cardsToValues a map of cards to values.
   */
  public void setButtonImage(Button button, Map<Button, String> cardsToValues) {
    switch (Objects.requireNonNull(cardsToValues.get(button))) {
      case "line":
        colourButton(
            button,
            R.drawable.match_line_red,
            R.drawable.match_line_blue,
            R.drawable.match_line_green);
        break;
      case "square":
        colourButton(
            button,
            R.drawable.match_square_red,
            R.drawable.match_square_blue,
            R.drawable.match_square_green);
        break;
      case "triangle":
        colourButton(
            button,
            R.drawable.match_triangle_red,
            R.drawable.match_triangle_blue,
            R.drawable.match_triangle_green);
        break;
      case "wave":
        colourButton(
            button,
            R.drawable.match_wave_red,
            R.drawable.match_wave_blue,
            R.drawable.match_wave_green);
        break;
      case "circle":
        colourButton(
            button,
            R.drawable.match_circle_red,
            R.drawable.match_circle_blue,
            R.drawable.match_circle_green);
        break;
      case "equal":
        colourButton(
            button,
            R.drawable.match_equal_red,
            R.drawable.match_equal_blue,
            R.drawable.match_equal_green);
        break;
    }
  }

  /** Update profile statistics. */
  public void updateProfileStats(int score, int moves) {
    this.appManager.updateProfileScore(this, score);
    this.appManager.updateProfileMoves(this, moves);
  }
}
