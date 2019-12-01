package com.example.phase1activity.ui.maze_game;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.example.phase1activity.domain.maze_game.MazeBlock;
import com.example.phase1activity.domain.maze_game.MazeItem;
import com.example.phase1activity.domain.maze_game.Wall;
import com.example.phase1activity.ui.abstraction.AbstractActivity;

import javax.inject.Inject;

import static android.graphics.Color.rgb;

/** The maze game activity. */
public class MazeGameViewImpl extends AbstractActivity implements MazeGameView {
  public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
  /** The nickname for the user */
  public String playerNickname;
  /** The color of the user */
  public int playerColor;

  /** Presenter responsible for handling user input. Injected using Dagger. */
  @Inject MazeGamePresenter presenter;
  /** The drawView attribute that allows the app to draw the maze and character */
  private DrawView drawView;

  /**
   * Create a new drawView, mazeManager, sets the score to 0 and sets the character color to the
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
    presenter = new MazeGamePresenterImpl(this, app.getMazeGameDifficulty());
    presenter.setPaintText(app.getProfileColour());
  }

  /** @return the drawView instances of this View. */
  public DrawView getView() {
    return drawView;
  }

  /** The maze is finished then move onto the next screen. */
  public void finishMaze() {
    Intent intent = new Intent(MazeGameViewImpl.this, MazeFinishActivity.class);
    intent.putExtra(EXTRA_MESSAGE, presenter.getScore());
    startActivity(intent);
  }

  /** Update the player's score and moves. */
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

    /**
     * Draw all the contents of the maze game.
     *
     * @param canvas the Canvas.
     */
    public void drawMaze(Canvas canvas) {

      // Draws the player, coin and teleporting blocks.
      presenter.getMazePlayer().draw(canvas);
      presenter.getCoin().draw(canvas);
      drawTeleportBlocks(canvas);

      if (!app
          .getMazeGameDifficulty()) { // If the user chooses the extreme setting, only draw walls
        // that are near the character in the maze
        drawHardGame(canvas);
      } else { // Draw all walls in the maze if the user chose the easy setting.
        drawEasyGame(canvas);
      }
    }

    /**
     * Draw all the walls of the neighbours and their neighbours of the current block of the player.
     *
     * @param canvas the Canvas.
     */
    private void drawHardGame(Canvas canvas) {
      for (Wall wall : presenter.getOuterWalls()) {
        wall.draw(canvas);
      }
      for (Wall wall : presenter.getMazeWalls()) {
        for (MazeBlock currentNeighbour :
            presenter.getMazePlayer().getCurrentBlock().getNeighboursNeighbour()) {
          for (MazeItem neighbourWall : currentNeighbour.getNeighbourWalls()) {
            if (wall == neighbourWall) { // If the wall is a wall of the currentNeighbour or
              // currentNeighbour2 (which is a neighbour of currentNeighbour)
              wall.draw(canvas);
            }
          }
        }
      }
    }

    /**
     * Draw all the maze walls.
     *
     * @param canvas The canvas.
     */
    private void drawEasyGame(Canvas canvas) {
      for (Wall wall : presenter.getMazeWalls()) {
        wall.draw(canvas);
      }
    }

    /**
     * Draw the two teleporting blocks if they have not been used yet.
     *
     * @param canvas the canvas.
     */
    public void drawTeleportBlocks(Canvas canvas) {
      Paint paint = new Paint();
      paint.setColor(Color.BLACK);
      paint.setTextSize(75);
      if (presenter.getTeleportBlock1() != null) {
        canvas.drawCircle(
            presenter.getTeleportBlock1().getX() * 100 + 173,
            presenter.getTeleportBlock1().getY() * 100 + 210,
            40,
            paint);
        canvas.drawCircle(
            presenter.getTeleportBlock2().getX() * 100 + 173,
            presenter.getTeleportBlock2().getY() * 100 + 210,
            40,
            paint);
      }
    }
    /** Draw the maze, character and displays the current score of the player */
    @Override
    public void onDraw(Canvas canvas) {
      super.onDraw(canvas);
      paint.setColor(playerColor);
      canvas.drawRect(825, 160, 920, 255, paint); // Draws the exit
      // Draws the current score of the player
      canvas.drawText(playerNickname + " score: " + presenter.getScore(), 75, 1500, paint);
      drawMaze(canvas); // draws the maze and character
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
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
      return presenter.onTouchEvent(event);
    }
  }
}
