package com.example.phase1activity.Levels;


import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class TheMaze extends AppCompatActivity {

    DrawView drawView;
    MazeManager newMazeManager;
    private double score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        drawView = new DrawView(this);
        drawView.setBackgroundColor(Color.WHITE);
        setContentView(drawView);
        newMazeManager = new MazeManager();

    }

    public class DrawView extends View {

        Paint paint = new Paint();
        MazeManager newMazeManager = new MazeManager();

        private void init() {
            paint.setColor(Color.BLACK);

        }

        public DrawView(Context context) {
            super(context);
            init();
        }

        @Override
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            newMazeManager.draw(canvas);
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
                return true;
            }
            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                float x = event.getX();
                float y = event.getY();

                float playerX = newMazeManager.mazeObject.player.currentBlock.getX() * 100 + 150;
                float playerY = newMazeManager.mazeObject.player.currentBlock.getY() * 100 + 160;
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
                drawView.invalidate();
                if (checkWin()) {

                }
                return true;
            }
            drawView.invalidate();
            return super.onTouchEvent(event);
        }

    }

    /**
     * Checks if the player has won the game
     *
     * @return Whether the player is currently standing on the winning block or not.
     */
    public boolean checkWin(){
        return newMazeManager.mazeObject.player.currentBlock == newMazeManager.mazeObject.winningBlock;
    }


}
