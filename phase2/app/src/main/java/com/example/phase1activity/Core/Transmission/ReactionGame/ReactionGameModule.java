package com.example.phase1activity.Core.Transmission.ReactionGame;

import com.example.phase1activity.UI.ReactionGame.ReactionGameViewInterface;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module, which allows for ReactionGamePresenter to be injected as a
 * ReactionGamePresenterInterface type object.
 */
@Module
public class ReactionGameModule {

  /** View that is injected with the presenter. */
  ReactionGameViewInterface view;

  /**
   * Assign view.
   *
   * @param view ReactionGameViewInterface to be injected.
   */
  public ReactionGameModule(ReactionGameViewInterface view) {
    this.view = view;
  }

  /**
   * Provide a presenter as an interface object.
   *
   * @return Presenter as PresenterInterface.
   */
  @Provides
  public ReactionGamePresenterInterface providePresenter() {
    return new ReactionGamePresenter(view, view.getColorStateList());
  }
}