package com.example.phase1activity.ui.user_access;

import com.example.phase1activity.domain.user_access.LogInManager;
import com.example.phase1activity.domain.user_access.RegisterManager;
import com.example.phase1activity.domain.user_access.UserAccessManager;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module telling Dagger how to create objects of type UserAccessPresenter and
 * UserAccessManager.
 */
@Module
class UserAccessModule {

  /** The view to be injected. */
  private UserAccessView view;
  /** The action the view is trying to do. */
  private String action;

  UserAccessModule(UserAccessView view) {
    this.view = view;
    this.action = view.getAction();
  }

  /** Provide an object of UserAccessPresenterImpl as UserAccessPresenter. */
  @Provides
  UserAccessPresenter providePresenter() {
    UserAccessPresenterImpl presenter = new UserAccessPresenterImpl(view);
    presenter.setManager(provideManager());
    return presenter;
  }

  /** Provide an object of UserAccessManager */
  @Provides
  UserAccessManager provideManager() {
    if (action.equals("login")) {
      return new LogInManager();
    } else {
      return new RegisterManager();
    }
  }
}
