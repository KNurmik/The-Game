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
    private int colour;

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

    public ProfileBuilder setColour(int colour) {
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
