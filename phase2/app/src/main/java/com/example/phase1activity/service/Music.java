package com.example.phase1activity.service;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.phase1activity.R;

import javax.inject.Inject;

/** A controller for the music musicPlayer. */
class Music {
  private MediaPlayer player;

  /** The first song option */
  private final int song1 = R.raw.sillychicken;

  /** The second song option */
  private final int song2 = R.raw.jazzy;

  /** An array that stores all songs */
  private final int[] Tracks = new int[] {song1, song2};

  @Inject
  Music(Context context, int n) {
    this.player = MediaPlayer.create(context, Tracks[n]);
    player.setLooping(true);
    player.setVolume(100, 100);
  }

  /** Changes the music to the song that the user chooses in their profile */
  void changeMusic(Context context, int n) {
    if (n == 3) {
      stopMusic();
    } else {
      player = MediaPlayer.create(context, Tracks[n]);
      player.setLooping(true);
      player.setVolume(100, 100);
      player.start();
    }
  }

  /** Stop the music from playing. */
  void stopMusic() {
      if (player != null) {
      player.pause();
      player.stop();
      player.release();
          player = null;

    }
  }
}
