package com.example.phase1activity.UI.Abstract;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import com.example.phase1activity.Core.Transmission.Overseers.AppManager;
import com.example.phase1activity.R;
import com.example.phase1activity.Core.Transmission.Overseers.AppManager;

/**
 * Base activity, every Activity in the application inherits this class
 */
public abstract class AbstractActivities extends AppCompatActivity {
    public AppManager app;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (AppManager) getApplication();
    }

    public AppManager getAppManager() {
        return app;
    }

    public void colourButton(View btn, @DrawableRes int red, @DrawableRes int blue, @DrawableRes int green){
        int colour = app.getProfileColour();

        if (colour == Color.RED){
            btn.setBackgroundResource(red);
        }
        else if (colour == Color.BLUE){
            btn.setBackgroundResource(blue);
        }
        else{
            btn.setBackgroundResource(green);
        }
    }


}
