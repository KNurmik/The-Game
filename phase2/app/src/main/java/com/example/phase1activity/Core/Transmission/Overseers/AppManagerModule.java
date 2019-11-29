package com.example.phase1activity.Core.Transmission.Overseers;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/** Dagger module for telling Dagger how to create Music and GlobalStats objects. */
@Module
public class AppManagerModule {

  private Context context;
  private int songNumber;
  private String nameOne;
  private String nameTwo;
  private String nameThree;
  private Double totalScore;
  private Double reactionTime;
  private Double totalMoves;

  AppManagerModule(
      Context context,
      int n,
      String one,
      String two,
      String three,
      Double score,
      Double reaction,
      Double moves) {
    this.context = context;
    songNumber = n;
    nameOne = one;
    nameTwo = two;
    nameThree = three;
    totalScore = score;
    reactionTime = reaction;
    totalMoves = moves;
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
