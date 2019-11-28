/*
 * Copyright 2019. Mark Vartolaş, Karl Hendrik Nurmeots, Olivia Li, Ramzi Dajani, Henry Leung.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
