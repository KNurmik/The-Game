package com.example.phase1activity.service;

import android.content.Context;

import com.example.phase1activity.domain.leaderboard.GlobalStats;

import dagger.Module;
import dagger.Provides;

/** Dagger module for telling Dagger how to create Music and GlobalStats objects. */
@Module
class AppManagerModule {

  /**
   * The context calling this module.
   */
  private Context context;

  /** The number of the song to use. */
  private int songNumber;

  AppManagerModule(Context context, int n) {
    this.context = context;
    songNumber = n;
  }

  /** @return Music object to inject. */
  @Provides
  Music providePlayer() {
    return new Music(context, songNumber);
  }

  /** @return GlobalStats object to inject. */
  @Provides
  GlobalStats provideGlobalStats() {
    return new GlobalStats(context);
  }
}
