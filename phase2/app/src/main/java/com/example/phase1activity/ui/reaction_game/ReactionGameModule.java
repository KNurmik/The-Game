package com.example.phase1activity.ui.reaction_game;

import com.example.phase1activity.domain.reaction_game.ReactionGameManagerImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module, which allows for ReactionGamePresenterImpl to be injected as a
 * ReactionGamePresenter type object.
 */
@Module
public class ReactionGameModule {

  /** View that is injected with the presenter. */
  ReactionGameView view;

  /**
   * Assign view.
   *
   * @param view ReactionGameView to be injected.
   */
  public ReactionGameModule(ReactionGameView view) {
    this.view = view;
  }

  /**
   * Provide a presenter as an interface object.
   *
   * @return Presenter as PresenterInterface.
   */
  @Provides
  public ReactionGamePresenter providePresenter() {
    ReactionGamePresenterImpl presenter = new ReactionGamePresenterImpl(view, view.getColorStateList());
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
