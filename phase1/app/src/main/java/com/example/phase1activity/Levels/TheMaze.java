package com.example.phase1activity.Levels;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.phase1activity.AbstractActivities;

public class TheMaze extends AbstractActivities {
    DrawView drawView;
    MazeManager newMazeManager;
    public int score;
    Button mainMenu;
    public String playerNickname;

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
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            newMazeManager.draw(canvas);

            canvas.drawText(playerNickname + " current score: " + score, 250, 1000, paint);
            if (checkWin()) {
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

                if (absDiffX > absDifY) {
                    if (diffX > 0) {
                        newMazeManager.mazeObject.player.move(Character.Direction.RIGHT);
                    } else {
                        newMazeManager.mazeObject.player.move(Character.Direction.LEFT);
                    }
                } else {
                    if (diffY > 0) {
                        newMazeManager.mazeObject.player.move(Character.Direction.DOWN);
                    } else {
                        newMazeManager.mazeObject.player.move(Character.Direction.UP);
                    }
                }
                if (checkWin()) {
                    Intent intent = new Intent(TheMaze.this, MazeFinish.class);
                    intent.putExtra(EXTRA_MESSAGE, score);
                    startActivity(intent);
                }
                score = calculateScore();
                drawView.invalidate();
                return true;
            }
            score = calculateScore();
            return super.onTouchEvent(event);
        }

    }

    /**
     * Checks if the player has won the game
     *
     * @return Whether the player is currently standing on the winning block or not.
     */

    public boolean checkWin() {
        return newMazeManager.mazeObject.player.currentBlock == newMazeManager.mazeObject.winningBlock;
    }


    /**
     * Updates the score of the player after they move
     *
     * @return The updated score of the player after they move
     */
    public int calculateScore(){ return Math.max(10000 - (newMazeManager.mazeObject.player.moves)*150, 0); }
}
