package com.example.asus.musicplayer;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private SongAdapter songAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.song_list_id);


        SongList allSongList = SongList.getInstance(this);
        List<SongInfo> songList = allSongList.songList;

        songAdapter = new SongAdapter(this, songList);
        listView.setAdapter(songAdapter);


        final List<SongInfo> finalSongList = songList;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // When clicked perform some action...
                SongInfo currentSong = finalSongList.get(position);
                Toast.makeText(MainActivity.this, currentSong.song_title, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, MusicActivity.class);
                intent.putExtra("index", position);
                startActivity(intent);
            }
        });
    }


}
