package com.example.phase1activity.ui.user_access;

import com.example.phase1activity.domain.user_access.LogInManager;
import com.example.phase1activity.domain.user_access.RegisterManager;
import com.example.phase1activity.domain.user_access.UserAccessManager;

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
