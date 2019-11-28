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
package com.example.phase1activity.Core.Transmission.MatchingGame;

import android.widget.Button;

import com.example.phase1activity.Core.Logic.MatchingGame.MatchingGameManager;
import com.example.phase1activity.UI.MatchingGame.MatchingGameActivityInterface;

import java.util.List;

import dagger.Module;
import dagger.Provides;

/** Dagger module allowing for MatchingGamePresenter to be injected as an interface. */
@Module
public class MatchingGameModule {

  /** Activity that is injected with the presenter. */
  private MatchingGameActivityInterface view;

  private List<Button> buttonList;

  private int numCards;

  /**
   * Assign view and buttonList.
   *
   * @param view the Activity to be injected.
   * @param buttonList a list of buttons within view.
   */
  public MatchingGameModule(MatchingGameActivityInterface view, List<Button> buttonList, int numCards) {
    this.view = view;
    this.buttonList = buttonList;
    this.numCards = numCards;
  }

  /** @return the interface object to be injected as a MatchingGamePresenter object. */
  @Provides
  public MatchingGamePresenterInterface providePresenter() {
    MatchingGamePresenter presenter = new MatchingGamePresenter(buttonList, view, numCards);
    presenter.setManager(provideManager());
    return presenter;
  }

  /** @return the manager object to be injected into presenter. */
  @Provides
  MatchingGameManager provideManager() {
    return new MatchingGameManager(buttonList.size());
  }
}
