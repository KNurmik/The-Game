package com.example.phase1activity.UI.UserAccess;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.phase1activity.Core.Transmission.Overseers.AppManager;
import com.example.phase1activity.Core.Transmission.UserAccess.DaggerUserAccessComponent;
import com.example.phase1activity.Core.Transmission.UserAccess.UserAccessModule;
import com.example.phase1activity.Core.Transmission.UserAccess.UserAccessPresenter;
import com.example.phase1activity.R;
import com.example.phase1activity.Core.Logic.UserAccess.RegisterManager;
import com.example.phase1activity.UI.Abstract.AbstractActivities;
import com.example.phase1activity.UI.MenuScreens.StartActivity;

import javax.inject.Inject;

/** Activity for the user to register a new account */
public class RegisterActivity extends AbstractActivities implements UserAccessView {
  /** The text field for the username */
  EditText usernameText;

  /** The text field for the password */
  EditText passwordText;

  /** Instructions displayed to the user */
  TextView instructionText;

  /** The button used to attempt to login */
  Button btn;

  /**
   * The UserAccessPresenter responsible for handling user input. Injected using Dagger dependency
   * injection.
   */
  @Inject UserAccessPresenter presenter;

  /**
   * Populate the Activity with objects. Inject presenter.
   *
   * @param savedInstanceState the saved instance state.
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);
    final AppManager app = (AppManager) getApplication();

    // Dependency injection using Dagger.
    presenter =
        DaggerUserAccessComponent.builder()
            .userAccessModule(new UserAccessModule(this))
            .build()
            .injectUserAccessPresenter();

    usernameText = findViewById(R.id.NewUsernameText);
    passwordText = findViewById(R.id.NewPasswordText);
    instructionText = findViewById(R.id.logInInstructionText);
    btn = findViewById(R.id.RegisterButton);

    final Activity thisActivity = this;

    btn.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            presenter.handleUserAccessAttempt(thisActivity, getUsername(), getPassword(), app);
          }
        });
  }

  /** @return what the user has entered in usernameText. */
  private String getUsername() {
    return usernameText.getText().toString();
  }

  /** @return what the user has entered in passwordText. */
  private String getPassword() {
    return passwordText.getText().toString();
  }

  /**
   * Updates the instructions given to the user.
   *
   * @param toThis The new message that will be displayed for the user
   * @param color The color of the message that will be displayed.
   */
  public void updateInstructionText(String toThis, int color) {
    instructionText.setText(toThis);
    instructionText.setTextColor(color);
  }

  /** @return that this Activity is trying to register a new account. */
  public String getAction() {
    return "register";
  }

  /** Clear usernameText and passwordText. */
  public void clearTextFields() {
    usernameText.setText("");
    passwordText.setText("");
  }

  /** End this Activity and open up the main menu. */
  public void endActivity() {
    startActivity(new Intent(RegisterActivity.this, StartActivity.class));
  }
}
