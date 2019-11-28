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
