package com.example.phase1activity.Infrastructure;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.example.phase1activity.Domain.Character;
import com.example.phase1activity.Domain.MazeManager;

/**
 * class for the activity_the_maze.xml
 *
 */
public class TheMaze extends AbstractActivities {
    /**
     * The drawView attribute that allows the app to draw the maze and character
     */
    DrawView drawView;

    /**
     * A new maze manager when the game starts
     */
    MazeManager newMazeManager;

    /**
     * The score of the user
     */
    public int score;

    /**
     * The nickname for the user
     */
    public String playerNickname;

    /**
     * Creates a new drawView, mazeManager, sets the score to 0 and sets the character color to the customized color that the user chose
     * @param savedInstanceState the bundle that creates the drawView
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        drawView = new DrawView(this);
        drawView.setBackgroundColor(Color.WHITE);
        setContentView(drawView);
        newMazeManager = new MazeManager();
        score = 0;
        playerNickname = app.getProfile().getNickname();
        newMazeManager.mazeObject.player.setPaintText(app.getProfile().getColour());
    }

    /**
     *
     * DrawView class that allows the drawing of the everything on the screen for the maze game
     */
    public class DrawView extends View {
        public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

        Paint paint = new Paint();

        private void init() {
            paint.setColor(Color.BLACK);
            paint.setTextSize(50);
        }

        public DrawView(Context context) {
            super(context);
            init();
        }

        @Override
        /**
         *
         * Draws the maze, character and displays the current score of the player
         */
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            newMazeManager.draw(canvas); // draws the maze and character

            // Draws the string displaying if the user has escaped the maze and the current score of the user
            canvas.drawText(playerNickname + " current score: " + score, 250, 1000, paint);
            if (checkWin()) {
                app.updateProfileScore(score); // updates the score for the user's profile
                app.updateProfileMoves(newMazeManager.mazeObject.player.moves); //updates the number of moves for the user's profile
                canvas.drawText( playerNickname + " escaped the maze!", 250, 800, paint);
            } else {
                canvas.drawText("Can you escape the maze?", 250, 800, paint);
            }
        }

        /**
         * Moves according to where the user touches the screen
         *
         * @param event the event that the user touches the screen
         * @return true always
         */
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                score = calculateScore();
                return true;
            }
            if (event.getAction() == MotionEvent.ACTION_MOVE && !checkWin()) {
                float x = event.getX();
                float y = event.getY();

                float playerX = newMazeManager.mazeObject.player.coordinateX();
                float playerY = newMazeManager.mazeObject.player.coordinateY();
                float diffX = x - playerX;
                float diffY = y - playerY;

                float absDiffX = Math.abs(x - playerX);
                float absDifY = Math.abs(y - playerY);

                if (absDiffX > absDifY) { // if the user moves more in the x direction than y direction
                    if (diffX > 0) { // if the user drags the character to the right
                        newMazeManager.mazeObject.player.move(Character.Direction.RIGHT);
                    } else { // if the user drags the character to the left
                        newMazeManager.mazeObject.player.move(Character.Direction.LEFT);
                    }
                } else { // if the user moves more in the y direction than x direction
                    if (diffY > 0) { // if the user drags the character upwards
                        newMazeManager.mazeObject.player.move(Character.Direction.DOWN);
                    } else { // if the user drags the character downwards
                        newMazeManager.mazeObject.player.move(Character.Direction.UP);
                    }
                }
                if (checkWin()) { //checks if the player is on the winning block after the user moves the character
                    Intent intent = new Intent(TheMaze.this, MazeFinish.class);
                    intent.putExtra(EXTRA_MESSAGE, score);
                    startActivity(intent);
                }
                score = calculateScore(); // calculates the new score after the user moves the character
                drawView.invalidate(); // updates the location of the character on the phone screen and the user's score
                return true;
            }

            return super.onTouchEvent(event);
        }

    }

    /**
     * Returns true if and only if the character is on the winning block and the user has one the game
     *
     * @return Whether the player is currently standing on the winning block or not.
     */

    public boolean checkWin() {
        return newMazeManager.mazeObject.player.currentBlock == newMazeManager.mazeObject.winningBlock;
    }


    /**
     * Calculates the score of the player and returns it
     *
     * @return The updated score of the player after they move
     */
    public int calculateScore(){ return Math.max(10000 - (newMazeManager.mazeObject.player.moves)*100, 0); }
}
