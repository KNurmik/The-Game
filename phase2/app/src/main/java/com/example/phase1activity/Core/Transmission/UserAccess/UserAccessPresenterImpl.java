package com.example.phase1activity.Core.Transmission.UserAccess;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import com.example.phase1activity.Core.Logic.UserAccess.UserAccessManager;
import com.example.phase1activity.Core.Transmission.Overseers.AppManager;
import com.example.phase1activity.UI.MenuScreens.StartActivity;
import com.example.phase1activity.UI.UserAccess.LogInActivity;
import com.example.phase1activity.UI.UserAccess.UserAccessView;

import javax.inject.Inject;

/**
 * Implementation of UserAccessPresenter. Serves as a middleman between UserAccessView and
 * UserAccessManager. Prompts Manager to do logic based on user input given by View, then uses the
 * results to tell the View what to display.
 */
public class UserAccessPresenterImpl implements UserAccessPresenter {

  /** Manager responsible for logic computations. Injected using Dagger dependency injection. */
  @Inject UserAccessManager manager;
  /** View responsible for taking in user input and displaying things on the screen. */
  UserAccessView view;

  /**
   * Create an instance of this class. Tell UserAccessModule how to create this object.
   *
   * @param view the UserAccessView object creating this Presenter.
   */
  @Inject
  public UserAccessPresenterImpl(UserAccessView view) {
    this.view = view;
  }

  /** @param manager UserAccessManager to set this.manager to. */
  public void setManager(UserAccessManager manager) {
    this.manager = manager;
  }

  /**
   * User has entered their username and password, prompt manager to do the logic and use the result
   * in handleUserAccessResult().
   *
   * @param context the Context that called this method.
   * @param username the entered username.
   * @param password the entered password
   * @param app the AppManager corresponding to the View and the Context that have called this
   *     method.
   */
  public void handleUserAccessAttempt(
      Context context, String username, String password, AppManager app) {
    UserAccessManager.Result result = manager.UserAccessAction(context, username, password, app);
    handleUserAccessResult(result);
  }

  /**
   * Based on the manager's computations, either prompt view to display text guiding the user, or
   * continue to the main menu if it is a successful login/registration attempt.
   *
   * @param result the result of the computations done by manager.
   */
  public void handleUserAccessResult(UserAccessManager.Result result) {
    // If there is no match with the username or password.
    if (result == UserAccessManager.Result.INCORRECT) {
      view.updateInstructionText("Incorrect username/password.", Color.RED);
      view.clearTextFields();
    } else if (result == UserAccessManager.Result.TAKEN) {
      view.updateInstructionText("Username is taken!", Color.RED);
      view.clearTextFields();
    }
    // Password is not in valid format.
    else if (result == UserAccessManager.Result.ERROR_PASSWORD) {
      view.updateInstructionText("Password length must be <16, and >0! No commas!", Color.RED);
      view.clearTextFields();
    }
    // Password is not in valid format.
    else if (result == UserAccessManager.Result.ERROR_USERNAME) {
      view.updateInstructionText("Username length must be <16, and >0! No commas!", Color.RED);
      view.clearTextFields();
    }
    // Successful login/registration.
    else {
      view.endActivity();
    }
  }
}
