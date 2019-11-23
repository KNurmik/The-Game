package com.example.phase1activity.Core.Transmission.UserAccess;

import com.example.phase1activity.Core.Logic.UserAccess.UserAccessManager;
import com.example.phase1activity.UI.UserAccess.UserAccessView;

import javax.inject.Inject;

public class UserAccessPresenterImpl implements UserAccessPresenter {

    @Inject
    UserAccessManager manager;
    UserAccessView view;

    @Inject
    public UserAccessPresenterImpl(UserAccessView view){this.view = view;}

    public void setManager(UserAccessManager manager){
        this.manager = manager;
    }
}
