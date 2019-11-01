package com.example.phase1activity.Profile;

import android.app.Application;
import android.content.res.Configuration;
import android.media.MediaPlayer;

import com.example.phase1activity.R;

public class AppManager extends Application {
    // Called when the application is starting, before any other application objects have been created.
    // Overriding this method is totally optional!
    private Profile profile;
    private int songNumber = 0;

    private final int song1 = R.raw.sillychicken;
    private final int song2 = R.raw.jazzy;
    private final int[] Tracks = new int[]{song1, song2};

    @Override
    public void onCreate() {
        super.onCreate();



        MediaPlayer player = MediaPlayer.create(this, Tracks[songNumber]);
        player.setLooping(true);
        player.setVolume(100,100);
        player.start();

//        if (this.profile.getSong() == 1){
//            player.stop();
//            MediaPlayer player2 = MediaPlayer.create(this, R.raw.sillychicken);
//            player2.setLooping(true);
//            player2.setVolume(100,100);
//            player2.start();
//        }


    }

    // Called by the system when the device configuration changes while your component is running.
    // Overriding this method is totally optional!
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

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Profile getProfile() {
        return this.profile;
    }

    public void setSongNumber(int n){
        this.songNumber = n;
    }
}

