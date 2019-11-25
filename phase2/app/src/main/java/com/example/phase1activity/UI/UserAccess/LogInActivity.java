package com.example.phase1activity.UI.UserAccess;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.phase1activity.Core.Logic.UserAccess.UserAccessManager;
import com.example.phase1activity.Core.Transmission.UserAccess.DaggerUserAccessComponent;
import com.example.phase1activity.Core.Transmission.UserAccess.UserAccessModule;
import com.example.phase1activity.Core.Transmission.UserAccess.UserAccessPresenter;
import com.example.phase1activity.R;
import com.example.phase1activity.UI.Abstract.AbstractActivity;
import com.example.phase1activity.UI.MenuScreens.StartActivity;

import javax.inject.Inject;

/** Activity where user can enter their credentials and log in with an existing account. */
public class LogInActivity extends AbstractActivity implements UserAccessView {
  /** The manager used for logging in. */
  UserAccessManager loginManager;

  /** The text field for the username */
  EditText usernameText;

  /** The text field for the password */
  EditText passwordText;

  /** Instructions displayed to the user */
  TextView instructionText;

  /** The button used to attempt to login */
  Button btn;

  /** UserAccessPresenter object responsible for handling user input. */
  @Inject UserAccessPresenter presenter;

  /**
   * Populate the Activity with objects. Inject presenter.
   *
   * @param savedInstanceState the saved instance state.
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_log_in);

    // Dependency injection using Dagger.
    presenter =
        DaggerUserAccessComponent.builder()
            .userAccessModule(new UserAccessModule(this))
            .build()
            .injectUserAccessPresenter();

    // Initializes all buttons and Text boxes
    usernameText = findViewById(R.id.UsernameText);
    passwordText = findViewById(R.id.PasswordText);
    instructionText = findViewById(R.id.logInInstructionText);
    btn = findViewById(R.id.logInButton);

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

  /** @return that this Activity is for logging in. */
  public String getAction() {
    return "login";
  }

  /** Clear usernameText and passwordText. */
  public void clearTextFields() {
    usernameText.setText("");
    passwordText.setText("");
  }

  /** End this Activity and proceed to the main menu. */
  public void endActivity() {
    startActivity(new Intent(LogInActivity.this, StartActivity.class));
  }
}
