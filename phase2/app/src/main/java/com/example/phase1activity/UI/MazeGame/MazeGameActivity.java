package com.example.phase1activity.UI.MazeGame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.example.phase1activity.Core.Transmission.MazeGame.MazeGamePresenter;
import com.example.phase1activity.Core.Transmission.MazeGame.MazeGamePresenterInterface;
import com.example.phase1activity.UI.Abstract.AbstractActivity;

import javax.inject.Inject;

import static android.graphics.Color.rgb;

/** class for the activity_the_maze.xml */
public class MazeGameActivity extends AbstractActivity implements MazeGameViewInterface {
  public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
  /** The nickname for the user */
  public String playerNickname;
  /** The color of the user */
  public int playerColor;
  @Inject MazeGamePresenterInterface presenter;
  /** The drawView attribute that allows the app to draw the maze and character */
  private DrawView drawView;

  /**
   * Creates a new drawView, mazeManager, sets the score to 0 and sets the character color to the
   * customized color that the user chose
   *
   * @param savedInstanceState the bundle that creates the drawView
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    drawView = new DrawView(this);
    drawView.setBackgroundColor(rgb(240, 237, 214));
    setContentView(drawView);
    playerNickname = app.getProfileNickname();
    playerColor = app.getProfileColour();
    presenter = new MazeGamePresenter(this);
    presenter.setPaintText(app.getProfileColour());
  }

  public DrawView getView() {
    return drawView;
  }

  /** The maze is finished then move onto the next screen. */
  public void finishMaze() {
    Intent intent = new Intent(MazeGameActivity.this, MazeFinishActivity.class);
    intent.putExtra(EXTRA_MESSAGE, presenter.getScore());
    startActivity(intent);
  }

  /** Updates the player's score and moves. */
  public void updateProfileStatistics() {
    app.updateProfileMoves(this, presenter.getPlayerMoves());
    app.updateProfileScore(this, presenter.getScore());
  }

  /** DrawView class that allows the drawing of the everything on the screen for the maze game */
  public class DrawView extends View {

    Paint paint = new Paint();

    public DrawView(Context context) {
      super(context);
      init();
    }

    private void init() {
      paint.setColor(Color.BLACK);
      paint.setTextSize(75);
    }

    /** Draws the maze, character and displays the current score of the player */
    @Override
    public void onDraw(Canvas canvas) {
      super.onDraw(canvas);
      paint.setColor(playerColor);
      canvas.drawRect(825, 160, 920, 255, paint); // Draws the exit
      // Draws the string displaying if the user has escaped the maze and the current score
      canvas.drawText(playerNickname + " current score: " + presenter.getScore(), 75, 1500, paint);
      presenter.drawTheView(canvas); // draws the maze and character
      if (presenter.checkWin()) {
        updateProfileStatistics(); // updates the number of score and moves for the user's profile
        canvas.drawText(playerNickname + " escaped the maze!", 75, 1400, paint);
      } else {
        canvas.drawText("Can you escape the maze?", 75, 1400, paint);
      }
    }

    /**
     * Every time the player touches the screen, record the movement and if it moves the player to a
     * new MazeBlock move it and redraw the maze to show so.
     *
     * @param event Event of the player touching the screen
     * @return true whenever the player touches the screen
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
      return presenter.onTouchEvent(event);
    }
  }
}
