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

import android.graphics.Canvas;

public class MazeManager {

  /** The maze that will be displayed on the phone */
  public Maze mazeObject;

  /** Constructs the MazeManager */
  public MazeManager(Maze maze) {
    this.mazeObject = maze;
    mazeObject.createPlayer();
  }

  /**
   * Draws every Wall in the maze, as well as the player
   *
   * @param canvas The canvas that the phone will use
   */
  public void draw(Canvas canvas) {
    for (Wall wall : mazeObject.mazeWalls) {
      wall.draw(canvas);
    }
    mazeObject.player.draw(canvas);
  }
}
