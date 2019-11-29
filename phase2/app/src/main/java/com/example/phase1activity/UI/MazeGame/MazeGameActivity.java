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
import com.example.phase1activity.UI.Abstract.AbstractActivity;

import static android.graphics.Color.rgb;

/** class for the activity_the_maze.xml */
public class MazeGameActivity extends AbstractActivity implements MazeGameViewInterface {
  /** The nickname for the user */
  public String playerNickname;
  /** The color of the user */
  public int playerColor;
  /** The drawView attribute that allows the app to draw the maze and character */
  private DrawView drawView;

  public MazeGamePresenter presenter;

  public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

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

    presenter.mazeManager.mazeObject.player.setPaintText(app.getProfileColour());
  }

  public DrawView getView() {
    return drawView;
  }
  /**
   * Returns true if and only if the character is on the winning block and the user has one the game
   *
   * @return Whether the player is currently standing on the winning block or not.
   */
  public boolean checkWin() {
    return presenter.mazeManager.mazeObject.player.currentBlock
        == presenter.mazeManager.mazeObject.winningBlock;
  }

  /** The maze is finished then move onto the next screen. */
  public void finishMaze() {
    Intent intent = new Intent(MazeGameActivity.this, MazeFinishActivity.class);
    intent.putExtra(EXTRA_MESSAGE, presenter.score);
    startActivity(intent);
  }

  /** Updates the player's score and moves. */
  public void updateProfileStatistics() {
    app.updateProfileMoves(this, presenter.mazeManager.mazeObject.player.moves);
    app.updateProfileScore(this, presenter.score);
  }

  /** DrawView class that allows the drawing of the everything on the screen for the maze game */
  public class DrawView extends View {

    Paint paint = new Paint();
    Paint paint2 = new Paint();

    public DrawView(Context context) {
      super(context);
      init();
    }

    private void init() {
      paint.setColor(Color.BLACK);
      paint.setTextSize(75);
      paint2.setColor(Color.BLACK);
      paint2.setTextSize(75);
    }

    /** Draws the maze, character and displays the current score of the player */
    @Override
    public void onDraw(Canvas canvas) {
      super.onDraw(canvas);
      paint.setColor(playerColor);
      canvas.drawRect(825, 160, 920, 255, paint); // Draws the exit
      canvas.drawCircle(
          presenter.mazeManager.mazeObject.teleportBlock1.getX() * 100 + 173,
          presenter.mazeManager.mazeObject.teleportBlock1.getY() * 100 + 210,
          40,
          paint2);
      canvas.drawCircle(
          presenter.mazeManager.mazeObject.teleportBlock2.getX() * 100 + 173,
          presenter.mazeManager.mazeObject.teleportBlock2.getY() * 100 + 210,
          40,
          paint2);
      // Draws the string displaying if the user has escaped the maze and the current score
      canvas.drawText(playerNickname + " current score: " + presenter.score, 75, 1500, paint);
      presenter.mazeManager.draw(canvas); // draws the maze and character
      if (checkWin()) {
        updateProfileStatistics(); // updates the number of score and moves for the user's profile
        canvas.drawText(playerNickname + " escaped the maze!", 75, 1400, paint);
      } else {
        canvas.drawText("Can you escape the maze?", 75, 1400, paint);
      }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
      return presenter.onTouchEvent(event);
    }
  }
}
