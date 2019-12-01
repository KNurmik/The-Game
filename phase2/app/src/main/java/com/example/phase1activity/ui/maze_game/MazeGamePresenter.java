package com.example.phase1activity.ui.maze_game;

import android.view.MotionEvent;

import com.example.phase1activity.domain.maze_game.Character;
import com.example.phase1activity.domain.maze_game.Coin;
import com.example.phase1activity.domain.maze_game.MazeBlock;
import com.example.phase1activity.domain.maze_game.Wall;

import java.util.List;

/** A presenter for some maze game, that relays information to and from its view and manager. */
public interface MazeGamePresenter {

  /**
   * Called by MazeGameView when the player touches the screen in an attempt to move the maze
   * character. It moves the character according to the direction and magnitude of the user's
   * movement on the screen.
   *
   * @param event the event of the player moving the maze character
   */
  boolean onTouchEvent(MotionEvent event);

  /** @param profileColour the color that the user chose in customizations. */
  void setPaintText(int profileColour);

  /** @return the score of this maze game. */
  int getScore();

  /** @return true iff the character block is on the winning block */
  boolean checkWin();

  /** @return the number of moves of the player for this maze game. */
  int getPlayerMoves();

  /** @return the character instances from this classes  MazeManger instance. */
  Character getMazePlayer();

  /** @return the coin instance from this classes MazeManger instance. */
  Coin getCoin();

  /** @return the first teleport maze block instance from this classes MazeManger instance. */
  MazeBlock getTeleportBlock1();

  /** @return the second teleport maze block instance from this classes MazeManger instance. */
  MazeBlock getTeleportBlock2();

  /** @return the list of maze walls from this classes MazeManger instance. */
  List<Wall> getMazeWalls();

  /** @return the list of outer maze walls from this classes MazeManger instance. */
  List<Wall> getOuterWalls();
}
