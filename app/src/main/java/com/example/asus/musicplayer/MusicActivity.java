package com.example.asus.musicplayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.session.MediaController;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;

public class MusicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        TextView nowPlayingTitle = (TextView) findViewById(R.id.now_playing_title);
        TextView nowPlayingArtist = (TextView) findViewById(R.id.now_playing_artist);
        ImageButton previousButton = (ImageButton) findViewById(R.id.pre_button);
        final ImageButton playPauseButton = (ImageButton) findViewById(R.id.play_pause_button);
        ImageButton nextButton = (ImageButton) findViewById(R.id.next_button);

        Intent intent = getIntent();

        String path = intent.getExtras().getString("path");
        String title = intent.getExtras().getString("title");
        String artist = intent.getExtras().getString("artist");

        nowPlayingTitle.setText(title);
        nowPlayingArtist.setText(artist);
        MediaPlayerCreator mediaPlayerCreator = MediaPlayerCreator.getInstance();

        final MediaPlayer mediaPlayer = mediaPlayerCreator.mediaPlayer;
        if (mediaPlayer.isPlaying())
        {
            mediaPlayer.stop();
            mediaPlayer.reset();
        }

        try {
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        playPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying())
                {
                    mediaPlayer.pause();
                    playPauseButton.setImageResource(R.drawable.playbutton);
                }
                else
                {

                    mediaPlayer.start();
                    mediaPlayer.start();
                    playPauseButton.setImageResource(R.drawable.pausebutton);
                }
            }
        });


    }
}
