package com.example.asus.musicplayer;

import android.media.MediaPlayer;

public class MediaPlayerCreator {
    private static MediaPlayerCreator instance = null;
    MediaPlayer mediaPlayer;

    private MediaPlayerCreator()
    {
        mediaPlayer = new MediaPlayer();
    }

    public static MediaPlayerCreator getInstance() {
        if (instance == null)
        {
            instance = new MediaPlayerCreator();
        }
        return instance;
    }
}
