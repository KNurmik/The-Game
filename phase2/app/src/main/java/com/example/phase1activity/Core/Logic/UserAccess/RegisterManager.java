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
import android.graphics.Color;

import com.example.phase1activity.Core.Transmission.Overseers.AppManager;
import com.example.phase1activity.Core.Transmission.Overseers.ProfileBuilder;
import com.example.phase1activity.Core.Transmission.Saving.AndroidSaver;
import com.example.phase1activity.Core.Transmission.Saving.ISaver;

/** A class that manages registration of new users. */
public class RegisterManager extends UserAccessManager {

  /**
   * Function to sign up.
   *
   * @param context context of the app
   * @param username username you input
   * @param password password you input
   * @param app instance of the AppManager that is running
   * @return a string "valid combination" if the registration is successful
   */
  public Result UserAccessAction(
      Context context, String username, String password, AppManager app) {
    // Deal with invalid username-password combinations.
    ISaver iSaver = new AndroidSaver(context);
    if (iSaver.getExistingUsernames().contains(username)) {
      return Result.TAKEN;
    } else if (!isValidPassword(password)) {
      return Result.ERROR_PASSWORD;
    } else if (!isValidUsername(username)) {
      return Result.ERROR_USERNAME;
    } else {
      // Save the new user's information with default values.
      final String DEFAULT_VALUES = "," + Color.RED + ",0,0,0,5,0";
      iSaver.saveData(username + "," + password + "," + username + DEFAULT_VALUES);
      app.setProfile(
          new ProfileBuilder()
              .setUsername(username)
              .setPassword(password)
              .setColour(Color.RED)
              .setNickname(username)
              .setGameLevel(0)
              .setSong(0)
              .setTotalScoreStat(0)
              .setTotalMovesStat(0)
              .setFastestRxnStat(0)
              .getProfile());
      return Result.VALID;
    }
  }
}
