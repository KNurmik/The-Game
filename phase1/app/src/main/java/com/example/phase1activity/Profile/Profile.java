package com.example.phase1activity.Profile;

import android.app.Activity;
import android.graphics.Color;

import com.example.phase1activity.SaveAPI.AndroidSaver;
import com.example.phase1activity.SaveAPI.ISaver;

public class Profile {
    /**
     * A string representing the id of the user
     */
    String username;

    /**
     * The user's password
     */
    String password;

    /**
     * The current/last-played level
     */
    int gameLevel;

    /**
     * A customization option for the color of various objects in the levels
     */
    String colour;

    /**
     * The song that will be playing while the user has the app open
     */
    int song;

    /**
     * A nickname that is displayed in each of the levels. Set to username by default
     */
    String nickname;

    /**
     * Fastest reaction time statistic (Reaction Game)
     */
    double fastestRxnStat;

    /**
     * The cumulative score for all three levels
     */
    int totalScoreStat;

    /**
     * The cumulative number of moves for all three levels
     */
    int totalMovesStat;


    public Profile(String username, String password, String nickname, String colour, int gameLevel, int song, double fastestReactionTime, int totalScore, int totalMoves) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.colour = colour;
        this.gameLevel = gameLevel;
        this.song = song;
        this.fastestRxnStat = fastestReactionTime;
        this.totalScoreStat = totalScore;
        this.totalMovesStat = totalMoves;
    }

    /**
     * Getter for the user's username
     * @return
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Changes the color preference of the user
     * @param colour The new color that will be used in the games.
     */
    public void setColour(String colour) {
        this.colour = colour;
    }

    /**
     * Returns the Color that the user saved to their profile.
     *
     * @return The color of the user.
     */
    public int getColour() {
        if (colour.equals("red")) {
            return Color.RED;
        } else if (colour.equals("blue")) {
            return Color.BLUE;
        } else {
            return Color.GREEN;
        }
    }

    /**
     * Sets the user's nickname to the parameter
     *
     * @param nickname The profile's new nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Getter for the current level the user is playing
     *
     * @return an integer representation of the current level
     */
    public int getGameLevel() {
        return this.gameLevel;
    }

    /**
     * Setter for the current level the user is playing
     * @param activity The activity used to save the data
     * @param n The new level
     */
    public void setGameLevel(Activity activity, int n) {
        ISaver iSaver = new AndroidSaver(activity);
        this.gameLevel = n;
        iSaver.saveData(username + "," + password + "," + nickname + "," + colour + "," + song + "," + gameLevel);
    }

    /**
     * Getter for the user's nickname
     * @return String representation of the user's nickname
     */
    public String getNickname() {
        return this.nickname;
    }

    /**
     * Changes the song the app plays while open
     */
    public void setSong(int n) {
        this.song = n;
    }

    /**
     * Gets the song that is currently playing
     * @return the numerical id of the song being played
     */
    public int getSong() {
        return this.song;
    }

    /**
     * Sets the Fastest reaction time statistic
     * @param time the fastest time a user reacts in the Reaction Game.
     */
    public void setFastestRxnStat(double time) {
        this.fastestRxnStat = time;
    }

    public void updateTotalMovesStat(int moves){this.totalMovesStat += moves;}

    void updateTotalScoreStat(int score){this.totalScoreStat += score;}





}
