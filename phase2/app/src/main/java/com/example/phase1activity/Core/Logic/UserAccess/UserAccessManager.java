/*
 * Copyright 2019. Mark Vartolaş, Karl Hendrik Nurmeots, Olivia Li, Ramzi Dajani, Henry Leung.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.phase1activity.Core.Logic.UserAccess;

import android.content.Context;

import com.example.phase1activity.Core.Transmission.Overseers.AppManager;

/** Responsible for logic computations for UserAccess. Used by UserAccessPresenter. */
public abstract class UserAccessManager {

  /** The minimum and maximum lengths for text input */
  private final int MAX_ENTRY_LENGTH = 16;

  private final int MIN_ENTRY_LENGTH = 0;

  /** User is attempting to login/register using entered username and password, handle this attempt.*/
  public abstract Result UserAccessAction(
      Context context, String username, String password, AppManager app);

  /**
   * Checks if the given string is valid for a username
   *
   * @param username The username that the user wants
   * @return true iff username is valid
   */
  boolean isValidUsername(String username) {
    return username.length() > MIN_ENTRY_LENGTH
        && username.length() < MAX_ENTRY_LENGTH
        && !username.contains(",");
  }

  /**
   * Checks if the given string is valid for a password
   *
   * @param password The password that the user wants
   * @return true iff password is valid
   */
  boolean isValidPassword(String password) {
    return password.length() > MIN_ENTRY_LENGTH
        && password.length() < MAX_ENTRY_LENGTH
        && !password.contains(",");
  }

  public enum Result {
    INCORRECT,
    TAKEN,
    ERROR_USERNAME,
    ERROR_PASSWORD,
    VALID
  }
}
