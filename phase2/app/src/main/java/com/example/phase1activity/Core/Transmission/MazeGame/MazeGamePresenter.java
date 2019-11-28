package com.example.phase1activity.Core.Transmission.MazeGame;

import android.view.MotionEvent;

import com.example.phase1activity.Core.Logic.MazeGame.Character;
import com.example.phase1activity.Core.Logic.MazeGame.Maze;
import com.example.phase1activity.Core.Logic.MazeGame.MazeManager;
import com.example.phase1activity.UI.MazeGame.MazeGameActivity;
import com.example.phase1activity.UI.MazeGame.MazeGameViewInterface;

import javax.inject.Inject;

public class MazeGamePresenter implements MazeGamePresenterInterface {

  /** The MazeManager for this presenter */
  public MazeManager mazeManager;
  /** The MazeGameViewInterface for this presenter */
  public MazeGameViewInterface view;
  /** The score of the user */
  public int score;
  /** The MazeGameActivity.DrawView for this presenter */
  private MazeGameActivity.DrawView drawView;

  /**
   * Constructor for the presenter.
   *
   * @param view The MazeGameViewInterface.
   */
  @Inject
  public MazeGamePresenter(MazeGameViewInterface view) {
    Maze maze = new Maze(8, 11);
    mazeManager = new MazeManager(maze);
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

  /**
   * Returns true if and only if the character is on the winning block and the user has one the game
   *
   * @return Whether the player is currently standing on the winning block or not.
   */
  public boolean checkWin() {
    return mazeManager.mazeObject.player.currentBlock == mazeManager.mazeObject.winningBlock;
  }

  public void gotCoin() {
    if (mazeManager.mazeObject.player.currentBlock == mazeManager.mazeObject.coin.mazeBlock
        && !(mazeManager.mazeObject.coin.isVisited())) {
      score = score + 5000;
      mazeManager.mazeObject.removeCoin();
    }
  }
  /**
   * Calculates the score of the player and returns it
   *
   * @return The updated score of the player after they move
   */
  public int calculateScore() {
    return (int) (20000 / Math.pow((1.1), mazeManager.mazeObject.player.moves) + 1000);
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

    float playerX = mazeManager.mazeObject.player.coordinateX();
    float playerY = mazeManager.mazeObject.player.coordinateY();
    float diffX = x - playerX;
    float diffY = y - playerY;

    float absDiffX = Math.abs(x - playerX);
    float absDifY = Math.abs(y - playerY);
    if (event.getAction() == MotionEvent.ACTION_DOWN) {
      score = calculateScore();
      return true;
    }
    if (event.getAction() == MotionEvent.ACTION_MOVE && !checkWin()) {

      if (absDiffX > absDifY) { // if the user moves more in the x direction than y direction
        if (diffX > 50) { // if the user drags the character to the right
          mazeManager.mazeObject.player.move(Character.Direction.RIGHT);
        } else if (diffX < -50) { // if the user drags the character to the left
          mazeManager.mazeObject.player.move(Character.Direction.LEFT);
        }
      } else { // if the user moves more in the y direction than x direction
        if (diffY > 50) { // if the user drags the character upwards
          mazeManager.mazeObject.player.move(Character.Direction.DOWN);
        } else if (diffY < -50) { // if the user drags the character downwards
          mazeManager.mazeObject.player.move(Character.Direction.UP);
        }
      }
      if (checkWin()) { // checks if the player is on the winning block
        view.finishMaze();
      }
      gotCoin();
      score = calculateScore(); // calculates the new score after the user moves the character
      drawView.invalidate(); // updates the location of the character on the phone screen and the
      // user's score
      return true;
    }

    return true;
  }
}
