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
package com.example.phase1activity.UI.UserAccess;

import android.graphics.Color;

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
