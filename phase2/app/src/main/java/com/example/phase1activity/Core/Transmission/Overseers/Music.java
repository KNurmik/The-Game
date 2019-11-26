package com.example.phase1activity.Core.Transmission.Overseers;

import android.app.Application;
import android.media.MediaPlayer;

import com.example.phase1activity.R;

public class Music extends Application{
    private MediaPlayer player;

    /** The first song option */
    private final int song1 = R.raw.sillychicken;

    /** The second song option */
    private final int song2 = R.raw.jazzy;


    /** An array that stores all songs */
    private final int[] Tracks = new int[] {song1, song2};


    /** The default song selected is the silly chicken song */
    private int songNumber = 1;


    public void startMusic(){
        this.player = MediaPlayer.create(this, Tracks[songNumber]);
        player.setLooping(true);
        player.setVolume(100, 100);
    }

    /** Changes the music to the song that the user chooses in their profile */
    public void changeMusic(int n) {
        player.release();
        player = MediaPlayer.create(this, Tracks[n]);
        player.setLooping(true);
        player.setVolume(100, 100);
        player.start();
    }

    MediaPlayer getPlayer(){
        return this.player;
    }

}
