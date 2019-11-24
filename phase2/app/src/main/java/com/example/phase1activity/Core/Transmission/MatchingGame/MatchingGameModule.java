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

  /**
   * Assign view and buttonList.
   *
   * @param view the Activity to be injected.
   * @param buttonList a list of buttons within view.
   */
  public MatchingGameModule(MatchingGameActivityInterface view, List<Button> buttonList) {
    this.view = view;
    this.buttonList = buttonList;
  }

  /** @return the interface object to be injected as a MatchingGamePresenter object. */
  @Provides
  public MatchingGamePresenterInterface providePresenter() {
    MatchingGamePresenter presenter = new MatchingGamePresenter(buttonList, view);
    presenter.setManager(provideManager());
    return presenter;
  }

  /** @return the manager object to be injected into presenter. */
  @Provides
  MatchingGameManager provideManager() {
    return new MatchingGameManager(buttonList.size());
  }
}
