package com.example.phase1activity.ui.maze_game;

import com.example.phase1activity.domain.maze_game.Maze;
import com.example.phase1activity.domain.maze_game.MazeManager;
import com.example.phase1activity.domain.maze_game.MazeManagerImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module for constructing dependencies.
 */
@Module
class MazeGameModule {

    /**
     * The view to be injected.
     */
    private MazeGameView view;

    /**
     * The desired difficulty of the MazeGame.
     */
    private boolean difficulty;

    MazeGameModule(MazeGameView view, boolean difficulty) {
        this.view = view;
        this.difficulty = difficulty;
    }

    @Provides
    MazeGamePresenter providePreseter() {
        MazeGamePresenter pres = new MazeGamePresenterImpl(view, difficulty);
        pres.setMazeManager(provideManager());
        return pres;
    }

    @Provides
    MazeManager provideManager() {
        return new MazeManagerImpl(provideMaze(), difficulty);
    }

    @Provides
    Maze provideMaze() {
        return new Maze(8, 11);
    }
}
