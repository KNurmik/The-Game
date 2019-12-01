package com.example.phase1activity.ui.user_access;

import dagger.Component;

/** Dagger component for generating code that injects UserAccessPresenter into View. */
@Component(modules = UserAccessModule.class)
public interface UserAccessComponent {

  /** @return the presenter to be injected. */
  UserAccessPresenter injectUserAccessPresenter();
}
