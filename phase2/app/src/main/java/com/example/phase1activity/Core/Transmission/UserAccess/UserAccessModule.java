package com.example.phase1activity.Core.Transmission.UserAccess;

import android.content.Context;

import com.example.phase1activity.Core.Logic.UserAccess.LogInManager;
import com.example.phase1activity.Core.Logic.UserAccess.RegisterManager;
import com.example.phase1activity.Core.Logic.UserAccess.UserAccessManager;
import com.example.phase1activity.Core.Transmission.Overseers.AppManager;
import com.example.phase1activity.UI.UserAccess.UserAccessView;

import dagger.Module;
import dagger.Provides;

@Module
public class UserAccessModule {

    UserAccessView view;
    String action;

    public UserAccessModule(UserAccessView view){
        this.view = view;
        this.action = view.getAction();
    }

    @Provides
    public UserAccessPresenter providePresenter(){
        UserAccessPresenterImpl presenter = new UserAccessPresenterImpl(view);
        presenter.setManager(provideManager());
        return presenter;
    }

    @Provides
    public UserAccessManager provideManager(){
        if(action.equals("login")){
            return new LogInManager();
        }
        else{
            return new RegisterManager();
        }
    }
}
