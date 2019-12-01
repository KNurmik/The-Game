package com.example.phase1activity.ui.user_access;


import dagger.Component;

@Component (modules = UserAccessModule.class)
public interface UserAccessComponent {

    UserAccessPresenter injectUserAccessPresenter();
}
