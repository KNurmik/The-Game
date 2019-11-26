package com.example.phase1activity.UI.MenuScreens;

import com.example.phase1activity.UI.Abstract.AbstractActivity;

public class PersonalLeaderboard extends AbstractActivity implements LeaderboardInterface{

    public String getTotalName() {
        return app.getProfileNickname();
    }

    public String getMovesName() {
        return app.getProfileNickname();
    }

    public String getFastestName() {
        return app.getProfileNickname();
    }

    public int getTotalScore() {
        return app.getProfileTotalScoreStat();
    }

    public int getTotalMoves() {
        return app.getProfileTotalMovesStat();
    }

    public double getFastestReaction() {
        return app.getProfileFastestRxnStat();
    }
}
