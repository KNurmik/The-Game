package com.example.phase1activity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.Paint;

public class Wall extends MazeItem {

    public boolean horizontal;
    public String appearance;

    private Paint paintText =new Paint();

    public Wall(int a, int b, boolean horz) {
        super(a, b);
        horizontal = horz;

        if (horz){
            appearance = "_";
        }
        else {
            appearance = "|";
        }

        paintText.setTextSize(36);
        paintText.setColor(Color.WHITE);
        paintText.setTypeface(Typeface.DEFAULT_BOLD);
    }

    public boolean isHorz() {
        return horizontal;
    }
    public void draw(Canvas canvas) {
        canvas.drawText(appearance, getX(), getY(), paintText);
    }

}
