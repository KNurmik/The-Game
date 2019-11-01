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

import java.text.DecimalFormat;

import androidx.appcompat.app.AppCompatActivity;

import com.example.phase1activity.R;
import com.example.phase1activity.presentation.MainMenu.StartActivity;

public class TheMaze extends AppCompatActivity implements View.OnClickListener {
    private static DecimalFormat df = new DecimalFormat("0.00");
    DrawView drawView;
    MazeManager newMazeManager;
    private double score;
    Button mainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        drawView = new DrawView(this);
        drawView.setBackgroundColor(Color.WHITE);
        setContentView(drawView);
        newMazeManager = new MazeManager();
        score = 0;
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(TheMaze.this, StartActivity.class));
    }

    public class DrawView extends View {

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

            canvas.drawText("Your current score" + df.format(score), 400, 1000, paint);
            if (checkWin()) {
                canvas.drawText("You escaped the maze!", 400, 800, paint);
            } else {
                canvas.drawText("Can you escape the maze?", 400, 800, paint);
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
                if (checkWin()) {
                    mainMenu.setVisibility(View.VISIBLE);
                }
                score = (double) (Math.pow(2.71, (int) ((newMazeManager.mazeObject.player.moves) / (-10))) * 100);
                drawView.invalidate();
                return true;
            }
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


}
