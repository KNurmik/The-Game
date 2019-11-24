package com.example.phase1activity.Core.Transmission.UserAccess;

import android.content.Context;

import com.example.phase1activity.Core.Transmission.Overseers.AppManager;

/** Interface used in UserAccessView, handles user input given by the view. */
public interface UserAccessPresenter {

  /**
   * Handle credentials entered by the user.
   *
   * @param context the Context calling this method.
   * @param username the entered username.
   * @param password the entered password.
   * @param app the AppManager corresponding to the View and the Context that have called this *
   *     method.
   */
  void handleUserAccessAttempt(Context context, String username, String password, AppManager app);
}
