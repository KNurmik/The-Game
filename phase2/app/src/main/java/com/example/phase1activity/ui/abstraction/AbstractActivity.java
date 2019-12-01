package com.example.phase1activity.ui.abstraction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import com.example.phase1activity.service.AppManager;

/** Base activity, every Activity in the application inherits this class */
public abstract class AbstractActivity extends AppCompatActivity {
  public AppManager app;

  /**
   * Creates the abstract activity for all activities.
   *
   * @param savedInstanceState the bundle that creates the app manager.
   */
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    app = (AppManager) getApplication();
  }

  /**
   * Getter for app manager.
   *
   * @return the app manager attribute.
   */
  public AppManager getAppManager() {
    return app;
  }

  /**
   * @param btn the button for color selection.
   * @param red version of png files used in the red UI theme.
   * @param blue version of png files used in the blue UI theme.
   * @param green version of png files used in the green UI theme.
   */
  public void colourButton(
      View btn, @DrawableRes int red, @DrawableRes int blue, @DrawableRes int green) {
    int colour = app.getProfileColour();

    if (colour == Color.RED) { // If the user selects red color in customization.
      btn.setBackgroundResource(red);
    } else if (colour == Color.BLUE) { // If the user selects blue color in customization.
      btn.setBackgroundResource(blue);
    } else { // If the user selects green color in customization.
      btn.setBackgroundResource(green);
    }
  }
}
