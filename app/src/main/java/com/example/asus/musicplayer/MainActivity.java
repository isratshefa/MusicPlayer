package com.example.asus.musicplayer;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

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

        List<SongInfo>songList = new ArrayList<>();
        songList = getAllAudioFromDevice(this);


        songAdapter = new SongAdapter(this, songList);
        listView.setAdapter(songAdapter);
    }

    public List<SongInfo> getAllAudioFromDevice(final Context context) {

        final List<SongInfo> tempAudioList = new ArrayList<>();

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Audio.AudioColumns.DATA, MediaStore.Audio.AudioColumns.ALBUM, MediaStore.Audio.ArtistColumns.ARTIST,};
        Cursor c = context.getContentResolver().query(uri, projection, null, null, null);

        if (c != null) {
            while (c.moveToNext()) {

                SongInfo song = new SongInfo();
                String path = c.getString(0);
                String album = c.getString(1);
                String artist = c.getString(2);

                String song_title = path.substring(path.lastIndexOf("/") + 1);

                song.artist = artist;
                song.song_title = song_title;
                song.song_path = path;
                song.album = album;

                tempAudioList.add(song);
            }
            c.close();
        }

        return tempAudioList;
    }
}
