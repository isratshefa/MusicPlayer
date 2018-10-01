package com.example.asus.musicplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private SongAdapter songAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.song_list_id);

        ArrayList<SongInfo>songList = new ArrayList<>();
        SongInfo song1 = new SongInfo("Etota Valobashi", "Recall");
        SongInfo song2 = new SongInfo("Etota Valobashi 2", "Recall 2");
        SongInfo song3 = new SongInfo("Etota Valobashi 3", "Recall 3");
        SongInfo song4 = new SongInfo("Etota Valobashi 4", "Recall 4");
        SongInfo song5 = new SongInfo("Etota Valobashi 5", "Recall 5");
        songList.add(song1);
        songList.add(song2);
        songList.add(song3);
        songList.add(song4);
        songList.add(song5);

        songAdapter = new SongAdapter(this, songList);
        listView.setAdapter(songAdapter);

    }
}
