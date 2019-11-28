package com.example.phase1activity.Core.Transmission.Overseers;

import dagger.Component;

/** Dagger component interface for generating boilerplate code. */
@Component(modules = AppManagerModule.class)
public interface AppManagerComponent {

  /** @return Music object to inject. */
  Music injectAppManagerPlayer();

  /** @return GlobalStats object to inject. */
  GlobalStats injectAppManagerGlobalStats();
}
