package com.example.phase1activity.Profile;

import android.app.Application;
import android.content.res.Configuration;
import android.media.MediaPlayer;

import com.example.phase1activity.R;

public class AppManager extends Application {
    // Called when the application is starting, before any other application objects have been created.
    // Overriding this method is totally optional!
    private Profile profile;
    /**
     * The selected song, set to 0 by default
     */
    private int songNumber = 0;

    /**
     * The first option for a song
     */
    private final int song1 = R.raw.sillychicken;

    /**
     * The second option for a song
     */
    private final int song2 = R.raw.jazzy;

    /**
     * An array containing all the songs
     */
    private final int[] Tracks = new int[]{song1, song2};
    MediaPlayer player;

    @Override
    public void onCreate() {
        super.onCreate();


        player = MediaPlayer.create(this, Tracks[songNumber]);
        player.setLooping(true);
        player.setVolume(100, 100);
        player.start();
    }

    /**
     * Changes the music to whatever the user picks in their profile settings
     */
    public void changeMusic() {
        player.release();
        player = MediaPlayer.create(this, Tracks[songNumber]);
        player.setLooping(true);
        player.setVolume(100, 100);
        player.start();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    // This is called when the overall system is running low on memory,
    // and would like actively running processes to tighten their belts.
    // Overriding this method is totally optional!
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    /**
     * Changes the profile that the app is currently using
     *
     * @param profile The new profile
     */
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    /**
     * Returns the user's profile
     *
     * @return profile
     */
    public Profile getProfile() {
        return this.profile;
    }

    /**
     * Getter for the profile's colour
     *
     * @return The selected profile's color
     */
    public int getProfileColour() {
        return getProfile().getColour();
    }

    /**
     * Changes the selected song
     *
     * @param n The new song number
     */
    public void setSongNumber(int n) {
        this.songNumber = n;
    }

    /**
     * Sets the profile's fastest reaction time
     *
     * @param time the new fastest time
     */
    public void setProfileReactionTime(double time) {
        profile.setFastestRxnStat(time);
    }

    /**
     * Sets the profile's total moves statistic
     *
     * @param moves The new number of moves
     */
    public void updateProfileMoves(int moves) {
        profile.updateTotalMovesStat(moves);
    }

    /**
     * Resets total score
     */
    public void resetProfileScore() {
        profile.resetTotalScoreStat();
    }

    /**
     * Resets total moves
     */
    public void resetProfileMoves() {
        profile.resetTotalMovesStat();
    }

    /**
     * Resets fastest reaction time
     */
    public void resetProfileRxnStat() {
        profile.resetFastestRxnStat();
    }

    /**
     * Increments Total score
     * @param score the amount Total score is incremented by
     */
    public void updateProfileScore(int score) {
        profile.updateTotalScoreStat(score);
    }
}

