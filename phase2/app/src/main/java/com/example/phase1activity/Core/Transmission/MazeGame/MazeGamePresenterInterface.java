package com.example.phase1activity.Core.Transmission.MazeGame;

import android.view.MotionEvent;

import com.example.phase1activity.Core.Logic.MazeGame.Character;
import com.example.phase1activity.Core.Logic.MazeGame.Coin;
import com.example.phase1activity.Core.Logic.MazeGame.MazeBlock;
import com.example.phase1activity.Core.Logic.MazeGame.Wall;

import java.util.List;

/** Interface for MazeGamePresenter. */
public interface MazeGamePresenterInterface {

  /**
   * Called by MazeGameView when the player touches the screen in an attempt to move the maze
   * character. It moves the character according to the direction and magnitude of the user's
   * movement on the screen.
   *
   * @param event the event of the player moving the maze character
   */
  boolean onTouchEvent(MotionEvent event);

  void setPaintText(int profileColour);

  int getScore();

  boolean checkWin();
  // void drawTheView(Canvas canvas);
  int getPlayerMoves();

  Character getMazePlayer();

  Coin getCoin();

  MazeBlock getTeleportBlock1();

  MazeBlock getTeleportBlock2();

  List<Wall> getMazeWalls();

  List<Wall> getOuterWalls();
}
