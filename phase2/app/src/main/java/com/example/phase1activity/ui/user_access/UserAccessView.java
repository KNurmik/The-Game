package com.example.phase1activity.ui.user_access;

public interface UserAccessView {

  /** @return whether user is trying to login (return "login) or register (return "register"). */
  String getAction();

  /**
   * Update text for guiding user through.
   *
   * @param toThis text to set instructionText to.
   * @param color colour of text to present.
   */
  void updateInstructionText(String toThis, int color);

  /** Clear the text fields for entering your username and password. */
  void clearTextFields();

  /** Successful login/registration, proceed to main menu. */
  void endActivity();
}
