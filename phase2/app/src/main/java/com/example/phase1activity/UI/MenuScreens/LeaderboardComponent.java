package com.example.phase1activity.UI.MenuScreens;

import dagger.Component;

/** Dagger component for injecting LeaderBoardSorting. */
@Component(modules = LeaderboardModule.class)
public interface LeaderboardComponent {
  LeaderBoardSorting injectLeaderBoardSorting();
}
