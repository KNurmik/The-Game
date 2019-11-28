package com.example.phase1activity.UI.MazeGame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.view.MotionEvent;


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

  public DrawView getView(){
    return drawView;
  }
  /**
   * Returns true if and only if the character is on the winning block and the user has one the game
   *
   * @return Whether the player is currently standing on the winning block or not.
   */
  public boolean checkWin() {
    return presenter.mazeManager.mazeObject.player.currentBlock == presenter.mazeManager.mazeObject.winningBlock;
  }

  public void finishMaze(){
    Intent intent = new Intent(MazeGameActivity.this, MazeFinishActivity.class);
    intent.putExtra(EXTRA_MESSAGE, presenter.score);
    startActivity(intent);
  }
//  /**
//   * Calculates the score of the player and returns it
//   *
//   * @return The updated score of the player after they move
//   */
//  public int calculateScore() {
//    return 5000 - (newMazeManager.mazeObject.player.moves) * 100;
//  }

  public void updateProfileStatistics() {
    app.updateProfileMoves(presenter.mazeManager.mazeObject.player.moves);
    app.updateProfileScore(presenter.score);
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
      presenter.mazeManager.draw(canvas); // draws the maze and character
      paint.setColor(playerColor);
      canvas.drawRect(825, 160, 920, 255, paint); // Draws the exit
      // Draws the string displaying if the user has escaped the maze and the current score
      canvas.drawText(playerNickname + " current score: " + presenter.score, 75, 1500, paint);
      if (checkWin()) {
        updateProfileStatistics(); // updates the number of score and moves for the user's profile
        canvas.drawText(playerNickname + " escaped the maze!", 75, 1400, paint);
      } else {
        canvas.drawText("Can you escape the maze?", 75, 1400, paint);
      }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
      return presenter.onTouchEvent(event);
    }
  }

//  /**
//   * Moves according to where the user touches the screen
//   *
//   * @param event the event that the user touches the screen
//   * @return true always
//   */
//  @Override
//  public boolean onTouchEvent(MotionEvent event) {
//    float x = event.getX();
//    float y = event.getY();
//
//    float playerX = newMazeManager.mazeObject.player.coordinateX();
//    float playerY = newMazeManager.mazeObject.player.coordinateY();
//    float diffX = x - playerX;
//    float diffY = y - playerY;
//
//    float absDiffX = Math.abs(x - playerX);
//    float absDifY = Math.abs(y - playerY);
//    if (event.getAction() == MotionEvent.ACTION_DOWN) {
//      score = calculateScore();
//      return true;
//    }
//    if (event.getAction() == MotionEvent.ACTION_MOVE && !checkWin()) {
//
//      if (absDiffX > absDifY) { // if the user moves more in the x direction than y direction
//        if (diffX > 50) { // if the user drags the character to the right
//          newMazeManager.mazeObject.player.move(Character.Direction.RIGHT);
//        } else if (diffX < -50) { // if the user drags the character to the left
//          newMazeManager.mazeObject.player.move(Character.Direction.LEFT);
//        }
//      } else { // if the user moves more in the y direction than x direction
//        if (diffY > 50) { // if the user drags the character upwards
//          newMazeManager.mazeObject.player.move(Character.Direction.DOWN);
//        } else if (diffY < -50) { // if the user drags the character downwards
//          newMazeManager.mazeObject.player.move(Character.Direction.UP);
//        }
//      }
//      if (checkWin()) { // checks if the player is on the winning block after the user moves the
//        // character
//        Intent intent = new Intent(MazeGameActivity.this, MazeFinishActivity.class);
//        intent.putExtra(EXTRA_MESSAGE, score);
//        startActivity(intent);
//      }
//      score = calculateScore(); // calculates the new score after the user moves the character
//      drawView.invalidate(); // updates the location of the character on the phone screen and the
//      // user's score
//      return true;
//    }
//
//    return super.onTouchEvent(event);
//  }
}
