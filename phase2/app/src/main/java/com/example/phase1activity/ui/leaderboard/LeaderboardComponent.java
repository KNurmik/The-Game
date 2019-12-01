package com.example.phase1activity.ui.leaderboard;

import com.example.phase1activity.domain.leaderboard.LeaderBoardSorting;

import dagger.Component;

/** Dagger component for injecting LeaderBoardSorting. */
@Component(modules = LeaderboardModule.class)
public interface LeaderboardComponent {
  LeaderBoardSorting injectLeaderBoardSorting();
}
