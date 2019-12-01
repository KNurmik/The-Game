package com.example.phase1activity.ui.matching_game;

import android.widget.Button;

import com.example.phase1activity.domain.matching_game.MatchingGameManagerImpl;

import java.util.List;

import dagger.Module;
import dagger.Provides;

/** Dagger module allowing for MatchingGamePresenter to be injected as an interface. */
@Module
public class MatchingGameModule {

  /** Activity that is injected with the presenter. */
  private MatchingGameActivityInterface view;

  /** A list of the cards in the game. */
  private List<Button> buttonList;

  /** The number of cards in the game. */
  private int numCards;

  /**
   * Initialize this MatchingGameModule.
   *
   * @param view the Activity to be injected.
   * @param buttonList a list of buttons within view.
   */
  public MatchingGameModule(
      MatchingGameActivityInterface view, List<Button> buttonList, int numCards) {
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
  MatchingGameManagerImpl provideManager() {
    return new MatchingGameManagerImpl(buttonList.size());
  }
}
