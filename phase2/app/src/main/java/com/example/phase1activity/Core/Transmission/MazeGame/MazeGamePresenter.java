package com.example.phase1activity.Core.Transmission.MazeGame;

import android.view.MotionEvent;

import com.example.phase1activity.Core.Logic.MazeGame.Character;
import com.example.phase1activity.Core.Logic.MazeGame.Coin;
import com.example.phase1activity.Core.Logic.MazeGame.Maze;
import com.example.phase1activity.Core.Logic.MazeGame.MazeBlock;
import com.example.phase1activity.Core.Logic.MazeGame.MazeManager;
import com.example.phase1activity.Core.Logic.MazeGame.MazeManagerImpl;
import com.example.phase1activity.Core.Logic.MazeGame.Wall;
import com.example.phase1activity.UI.MazeGame.MazeGameActivity;
import com.example.phase1activity.UI.MazeGame.MazeGameViewInterface;

import java.util.List;

import javax.inject.Inject;

/** A presenter for some maze game, that relays information to and from its view and manager. */
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
    mazeManager = new MazeManagerImpl(maze, difficulty);
    this.view = view;
    score = 0;
    drawView = view.getView();
  }

  /**
   * Sets the mazeManager to manager
   *
   * @param manager The new MazeManager
   */
  public void setManager(MazeManagerImpl manager) {
    this.mazeManager = manager;
  }

  /** @return the score of this maze game. */
  public int getScore() {
    return score;
  }

  /** @return true iff the character block is on the winning block */
  public boolean checkWin() {
    return mazeManager.checkWin();
  }

  /** @param color the color that the user chose in customizations. */
  public void setPaintText(int color) {
    mazeManager.getMazeObject().player.setPaintText(color);
  }

  /** @return the number of moves of the player for this maze game. */
  public int getPlayerMoves() {
    return mazeManager.getMazeObject().player.getMoves();
  }

  /** @return the character instances from this classes MazeManger instance. */
  public Character getMazePlayer() {
    return mazeManager.getMazePlayer();
  }

  /** @return the coin instance from this classes MazeManger instance. */
  public Coin getCoin() {
    return mazeManager.getCoin();
  }

  /** @return the first teleport maze block instance from this classes MazeManger instance. */
  public MazeBlock getTeleportBlock1() {
    return mazeManager.getTeleportBlock1();
  }

  /** @return the second teleport maze block instance from this classes MazeManger instance. */
  public MazeBlock getTeleportBlock2() {
    return mazeManager.getTeleportBlock2();
  }

  /** @return the list of maze walls from this classes MazeManger instance. */
  public List<Wall> getMazeWalls() {
    return mazeManager.getMazeWalls();
  }

  /** @return the list of outer maze walls from this classes MazeManger instance. */
  public List<Wall> getOuterWalls() {
    return mazeManager.getOuterWalls();
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
