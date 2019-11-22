package com.example.phase1activity.Infrastructure;

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
  ReactionGameModule(ReactionGameViewInterface view) {
    this.view = view;
  }

  /**
   * Provide a presenter as an interface object.
   *
   * @return Presenter as PresenterInterface.
   */
  @Provides
  ReactionGamePresenterInterface providePresenter() {
    return new ReactionGamePresenter(view, view.getColorStateList());
  }
}
