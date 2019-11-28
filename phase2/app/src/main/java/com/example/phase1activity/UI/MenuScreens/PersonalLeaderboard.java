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
