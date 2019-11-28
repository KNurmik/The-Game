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
package com.example.phase1activity.Core.Transmission.Saving;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * An interface defining the methods needed for any game-saving mechanism (no matter the OS it is
 * specific to.
 */
public interface ISaver {
    /**
     * Save contents.
     *
     * @param contents the contents to be saved.
     */
    void saveData(String contents);

    /**
     * Load contents.
     *
     * @return previously saved contents.
     */
    String loadData();

    /**
     * Return a string array of user data, split by individual entries to the saving system.
     *
     * @return a string array of user data, split by individual entries to the saving system.
     */
    Map<String, Map<AndroidSaver.AttributeType, String>> getExistingUserData();

    /**
     * Return a map of usernames to a map of username attribute names to their objects.
     *
     * @return a map of usernames to a map of username attribute names to their objects.
     */
    Set<String> getExistingUsernames();

    void saveAttribute(String username, String newAttribute, AndroidSaver.AttributeType attributeType);

    /**
     * Return a map of usernames to a map of highest score types to their respective values.
     *
     * @return a map of usernames to a map of highest score types to their respective values.
     */
    Map<String, Map<AndroidSaver.AttributeType, Double>> getHighScores();
}
