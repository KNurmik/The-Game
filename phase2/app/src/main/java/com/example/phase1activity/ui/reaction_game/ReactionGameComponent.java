package com.example.phase1activity.ui.reaction_game;

import dagger.Component;

/** Dagger component for controlling which object to inject. */
@Component(modules = ReactionGameModule.class)
public interface ReactionGameComponent {

  /**
   * Inject object of type ReactionGamePresenter.
   *
   * @return Presenter object as a PresenterInterface.
   */
  ReactionGamePresenter injectReactionGamePresenter();
}
