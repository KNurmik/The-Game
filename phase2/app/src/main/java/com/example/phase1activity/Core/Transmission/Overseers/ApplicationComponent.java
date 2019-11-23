package com.example.phase1activity.Core.Transmission.Overseers;

import com.example.phase1activity.Core.Transmission.ReactionGame.ReactionGameModule;
import com.example.phase1activity.Core.Transmission.ReactionGame.ReactionGamePresenterInterface;

import dagger.Component;

/**
 * Dagger component for controlling which object to inject.
 */
@Component (modules = ReactionGameModule.class)
public interface ApplicationComponent {

    /**
     * Inject object of type ReactionGamePresenterInterface.
     * @return Presenter object as a PresenterInterface.
     */
    ReactionGamePresenterInterface injectReactionGamePresenter();
}

