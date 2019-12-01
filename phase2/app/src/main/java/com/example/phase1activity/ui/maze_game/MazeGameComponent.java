package com.example.phase1activity.ui.maze_game;

import dagger.Component;
import dagger.Module;

/** Dagger component for controlling injection of MazeGameViewImpl. */
@Component(modules = MazeGameModule.class)
public interface MazeGameComponent {

  /** @return the presenter to be injected. */
  MazeGamePresenter injectPresenter();
}
