package com.example.phase1activity.Domain.Overseers;

public class ProfileBuilder {
    /**
     * The user id.
     */
    private String username;

    /**
     * The user's password.
     */
    private String password;

    /**
     * The current level.
     */
    private int gameLevel;

    /**
     * The user's colour.
     */
    private String colour;

    /**
     * The song playing.
     */
    private int song;

    /**
     * The user's nickname.
     */
    private String nickname;

    /**
     * Fastest reaction time statistic (Reaction Game).
     */
    private double fastestRxnStat;

    /**
     * The cumulative score for all three levels.
     */
    private int totalScoreStat;

    /**
     * The cumulative number of moves for all three levels.
     */
    private int totalMovesStat;

    public Profile getProfile() {
        return new Profile(username, password, nickname, colour, gameLevel, song, totalScoreStat, fastestRxnStat, totalMovesStat);
    }

    public ProfileBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public ProfileBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public ProfileBuilder setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public ProfileBuilder setGameLevel(int gameLevel) {
        this.gameLevel = gameLevel;
        return this;
    }

    public ProfileBuilder setColour(String colour) {
        this.colour = colour;
        return this;
    }

    public ProfileBuilder setFastestRxnStat(double fastestRxnStat) {
        this.fastestRxnStat = fastestRxnStat;
        return this;
    }

    public ProfileBuilder setSong(int song) {
        this.song = song;
        return this;
    }

    public ProfileBuilder setTotalScoreStat(int totalScoreStat) {
        this.totalScoreStat = totalScoreStat;
        return this;
    }

    public ProfileBuilder setTotalMovesStat(int totalMovesStat) {
        this.totalMovesStat = totalMovesStat;
        return this;
    }
}
