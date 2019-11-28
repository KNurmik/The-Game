package com.example.phase1activity.Core.Transmission.UserAccess;


import com.example.phase1activity.Core.Transmission.UserAccess.UserAccessModule;
import com.example.phase1activity.Core.Transmission.UserAccess.UserAccessPresenter;

import dagger.Component;

@Component (modules = UserAccessModule.class)
public interface UserAccessComponent {

    UserAccessPresenter injectUserAccessPresenter();
}
