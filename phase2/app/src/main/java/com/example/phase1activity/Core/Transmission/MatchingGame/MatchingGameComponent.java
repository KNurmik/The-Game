package com.example.phase1activity.Core.Transmission.MatchingGame;

import dagger.Component;

/** Dagger component responsible for controlling what object to inject. */
@Component(modules = MatchingGameModule.class)
public interface MatchingGameComponent {

  /** @return MatchingGamePresenterInterface object to be injected. */
  MatchingGamePresenterInterface injectMatchingGamePresenter();
}
