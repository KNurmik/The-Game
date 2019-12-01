package com.example.phase1activity.ui.reaction_game;

import com.example.phase1activity.domain.reaction_game.ReactionGameManagerImpl;

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
    ReactionGamePresenter presenter = new ReactionGamePresenter(view, view.getColorStateList());
    presenter.setManager(provideManager());
    return presenter;
  }

  /**
   * Provide a ReactionGameManagerImpl object.
   *
   * @return the manager.
   */
  @Provides
  public ReactionGameManagerImpl provideManager() {
    return new ReactionGameManagerImpl("easy");
  }
}
