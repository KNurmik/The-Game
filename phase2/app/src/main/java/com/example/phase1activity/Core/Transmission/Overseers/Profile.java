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
package com.example.phase1activity.Core.Transmission.Overseers;

import android.app.Activity;
import android.graphics.Color;

import com.example.phase1activity.Core.Transmission.Saving.AndroidSaver;
import com.example.phase1activity.Core.Transmission.Saving.ISaver;

 class Profile {
    /**
     * A string representing the id of the user
     */
    private String username;

    /**
     * The user's password
     */
    private String password;

    /**
     * The current/last-played level
     */
    private int gameLevel;

    /**
     * A customization option for the color of various objects in the levels
     */
    private int colour;

    /**
     * The song that will be playing while the user has the app open
     */
    private int song;

    /**
     * A nickname that is displayed in each of the levels. Set to username by default
     */
    private String nickname;

    /**
     * Fastest reaction time statistic (Reaction Game)
     */
    private double fastestRxnStat;

    /**
     * The cumulative score for all three levels
     */
    private int totalScoreStat;

    /**
     * The cumulative number of moves for all three levels
     */
    private int totalMovesStat;


     Profile(String username, String password, String nickname, int colour, int gameLevel, int song, int totalScore, double fastestReactionTime, int totalMoves) {
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
     *
     * @return
     */
     String getUsername() {
        return this.username;
    }

    /**
     * Changes the color preference of the user
     *
     * @param colour The new color that will be used in the games.
     */
     void setColour(int colour) {
        this.colour = colour;
    }

    /**
     * Returns the Color that the user saved to their profile.
     *
     * @return The color of the user.
     */
     int getColour() {
        if (colour == Color.RED) {
            return Color.RED;
        } else if (colour == Color.BLUE) {
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
     void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Getter for the current level the user is playing
     *
     * @return an integer representation of the current level
     */
     int getGameLevel() {
        return this.gameLevel;
    }

    /**
     * Setter for the current level the user is playing
     *
     * @param activity The activity used to save the data
     * @param n        The new level
     */
     void setGameLevel(Activity activity, int n) {
        ISaver iSaver = new AndroidSaver(activity);
        this.gameLevel = n;
//        iSaver.saveData(username + "," + password + "," + nickname + "," + colour + "," + song + "," + gameLevel + "," + totalScoreStat + "," + fastestRxnStat + "," + totalMovesStat);
        iSaver.saveAttribute(username, String.valueOf(gameLevel), AndroidSaver.AttributeType.GAME_LEVEL);
    }

    /**
     * Getter for the user's nickname
     *
     * @return String representation of the user's nickname
     */
     String getNickname() {
        return this.nickname;
    }

    /**
     * Changes the song the app plays while open
     */
     void setSong(int n) {
        this.song = n;
    }

    /**
     * Gets the song that is currently playing
     *
     * @return the numerical id of the song being played
     */
     int getSong() {
        return this.song;
    }

    /**
     * Sets the Fastest reaction time statistic
     *
     * @param time the fastest time a user reacts in the Reaction Game.
     */
     void setFastestRxnStat(double time) {
        this.fastestRxnStat = time;
    }

    /**
     * Increments total moves statistic
     *
     * @param moves totalMovesStat is incremented by moves
     */
     void updateTotalMovesStat(int moves) {
        this.totalMovesStat += moves;
    }

    /**
     * Increments total score statistic
     *
     * @param score totalScoreStat is incremented by score
     */
    void updateTotalScoreStat(int score) {
        this.totalScoreStat += score;
    }

    /**
     * Reset totalScoreStat to 0.
     * */
    void resetTotalScoreStat(){totalScoreStat = 0;}

    /**
     * Reset totalMovesStat to 0.
     */
    void resetTotalMovesStat(){totalMovesStat = 0;}

    /**
     * Reset fastestRxnStat to 5.
     */
    void resetFastestRxnStat(){fastestRxnStat = 5;}

    /**
     * @return fastestRxnStat.
     */
     double getFastestRxnStat(){
        return this.fastestRxnStat;
    }

    /**
     * @return totalScoreStat.
     */
     int getTotalScoreStat(){
        return this.totalScoreStat;
    }

    /**
     * @return totalMovesStat.
     */
     int getTotalMovesStat(){
        return this.totalMovesStat;
    }

    /**
     * @return password.
     */
     String getPassword(){
        return this.password;
    }


}
