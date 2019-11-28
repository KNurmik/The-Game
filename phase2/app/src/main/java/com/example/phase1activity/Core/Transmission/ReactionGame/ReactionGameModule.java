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
package com.example.phase1activity.Core.Transmission.ReactionGame;

import com.example.phase1activity.Core.Logic.ReactionGame.ReactionGameManager;
import com.example.phase1activity.UI.ReactionGame.ReactionGameViewInterface;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module, which allows for ReactionGamePresenter to be injected as a
 * ReactionGamePresenterInterface type object.
 */
@Module
public class ReactionGameModule {

  /** View that is injected with the presenter. */
  ReactionGameViewInterface view;

  /**
   * Assign view.
   *
   * @param view ReactionGameViewInterface to be injected.
   */
  public ReactionGameModule(ReactionGameViewInterface view) {
    this.view = view;
  }

  /**
   * Provide a presenter as an interface object.
   *
   * @return Presenter as PresenterInterface.
   */
  @Provides
  public ReactionGamePresenterInterface providePresenter() {
    ReactionGamePresenter presenter = new ReactionGamePresenter(view, view.getColorStateList());
    presenter.setManager(provideManager());
    return presenter;
  }

  /**
   * Provide a ReactionGameManager object.
   *
   * @return the manager.
   */
  @Provides
  public ReactionGameManager provideManager() {
    return new ReactionGameManager("easy");
  }
}
