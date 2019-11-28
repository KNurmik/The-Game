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
package com.example.phase1activity.Core.Logic.MazeGame;

public class MazeItem {

  /** The MazeItem's x coordinate */
  private int x;

  /** The MazeItem's y coordinate */
  private int y;

  /**
   * The MazeItem constructor
   *
   * @param a The initial x value of the MazeItem
   * @param b The initial y value of the MazeItem
   */
  MazeItem(int a, int b) {
    x = a;
    y = b;
  }

  /**
   * Getter for MazeItem.x
   *
   * @return x
   */
  int getX() {
    return x;
  }

  /**
   * Setter for MazeItem.x
   *
   * @param x The new x value of this MazeItem
   */
  public void setX(int x) {
    this.x = x;
  }

  /**
   * Getter for MazeItem.y
   *
   * @return y
   */
  int getY() {
    return y;
  }

  /**
   * Setter for MazeItem.y
   *
   * @param y The new y value of this MazeItem
   */
  public void setY(int y) {
    this.y = y;
  }
}
