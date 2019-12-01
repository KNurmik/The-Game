package com.example.phase1activity.ui.matching_game;

import dagger.Component;

/** Dagger component responsible for controlling what object to inject. */
@Component(modules = MatchingGameModule.class)
public interface MatchingGameComponent {

  /** @return MatchingGamePresenterInterface object to be injected. */
  MatchingGamePresenter injectMatchingGamePresenter();
}
