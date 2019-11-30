package com.example.phase1activity.Core.Transmission.MazeGame;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.example.phase1activity.Core.Logic.MazeGame.Character;
import com.example.phase1activity.Core.Logic.MazeGame.Maze;
import com.example.phase1activity.Core.Logic.MazeGame.MazeManager;
import com.example.phase1activity.UI.MazeGame.MazeGameActivity;
import com.example.phase1activity.UI.MazeGame.MazeGameViewInterface;

import javax.inject.Inject;

public class MazeGamePresenter implements MazeGamePresenterInterface {

  /** The MazeManager for this presenter */
  private MazeManager mazeManager;
  /** The MazeGameViewInterface for this presenter */
  private MazeGameViewInterface view;
  /** The score of the user */
  private int score;
  /** The MazeGameActivity.DrawView for this presenter */
  private MazeGameActivity.DrawView drawView;

  /**
   * Constructor for the presenter.
   *
   * @param view The MazeGameViewInterface.
   */
  @Inject
  public MazeGamePresenter(MazeGameViewInterface view, boolean difficulty) {
    Maze maze = new Maze(8, 11);
    mazeManager = new MazeManager(maze, difficulty);
    this.view = view;
    score = 0;
    drawView = view.getView();
  }

  /**
   * Sets the mazeManager to manager
   *
   * @param manager The new MazeManager
   */
  public void setManager(MazeManager manager) {
    this.mazeManager = manager;
  }

  public int getScore() {
    return score;
  }

  public void drawTheView(Canvas canvas) {
    mazeManager.draw(canvas);
  }

  public boolean checkWin() {
    return mazeManager.checkWin();
  }

  public void setPaintText(int color) {
    mazeManager.getMazeObject().player.setPaintText(color);
  }

  public int getPlayerMoves() {
    return mazeManager.getMazeObject().player.getMoves();
  }
  /**
   * Every time the player touches the screen, record the movement and if it moves the player to a
   * new MazeBlock move it and redraw the maze to show so.
   *
   * @param event the event of the player moving the maze character
   * @return True always
   */
  @Override
  public boolean onTouchEvent(MotionEvent event) {
    float x = event.getX();
    float y = event.getY();

    float playerX = mazeManager.getPlayerX();
    float playerY = mazeManager.getPlayerY();
    float diffX = x - playerX;
    float diffY = y - playerY;

    float absDiffX = Math.abs(x - playerX);
    float absDifY = Math.abs(y - playerY);
    if (event.getAction() == MotionEvent.ACTION_DOWN) {
      score = mazeManager.calculateScore();
      return true;
    }

    if (event.getAction() == MotionEvent.ACTION_MOVE && !mazeManager.checkWin()) {

      if (absDiffX > absDifY) { // if the user moves more in the x direction than y direction
        if (diffX > 50) { // if the user drags the character to the right
          mazeManager.movePlayer(Character.Direction.RIGHT);
        } else if (diffX < -50) { // if the user drags the character to the left
          mazeManager.movePlayer(Character.Direction.LEFT);
        }
      } else { // if the user moves more in the y direction than x direction
        if (diffY > 50) { // if the user drags the character upwards
          mazeManager.movePlayer(Character.Direction.DOWN);
        } else if (diffY < -50) { // if the user drags the character downwards
          mazeManager.movePlayer(Character.Direction.UP);
        }
      }
      if (mazeManager.checkWin()) { // checks if the player is on the winning block
        view.finishMaze();
      }
      mazeManager.teleport();
      mazeManager.gotCoin();
      score =
          mazeManager
              .calculateScore(); // calculates the new score after the user moves the character
      drawView.invalidate(); // updates the location of the character on the phone screen and the
      // user's score
      return true;
    }

    return true;
  }
}
