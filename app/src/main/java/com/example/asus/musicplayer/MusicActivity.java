package com.example.asus.musicplayer;

import android.media.MediaPlayer;
import android.media.session.MediaController;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

public class MusicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        TextView nowPlayingTitle = (TextView) findViewById(R.id.now_playing_title);
        TextView nowPlayingArtist = (TextView) findViewById(R.id.now_playing_artist);
        ImageButton previousButton = (ImageButton) findViewById(R.id.pre_button);
        ImageButton playPauseButton = (ImageButton) findViewById(R.id.play_pause_button);
        ImageButton nextButton = (ImageButton) findViewById(R.id.next_button);

    }
}
