package com.example.asus.musicplayer;

import android.content.Context;
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

        List<SongInfo>songList = new ArrayList<>();
        songList = getAllAudioFromDevice(this);
        final MediaPlayer mediaPlayer = new MediaPlayer();


        songAdapter = new SongAdapter(this, songList);
        listView.setAdapter(songAdapter);


        final List<SongInfo> finalSongList = songList;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // When clicked perform some action...
                SongInfo currentSong = finalSongList.get(position);
                Toast.makeText(MainActivity.this, position + "Playing", Toast.LENGTH_SHORT).show();
                try {

                    mediaPlayer.setDataSource(currentSong.song_path);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    if (mediaPlayer.isPlaying()) {
                        Toast.makeText(MainActivity.this, currentSong.song_title, Toast.LENGTH_SHORT).show();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Cannot Play Song", Toast.LENGTH_SHORT).show();
                }
            }
        });


        try {
            mediaPlayer.setDataSource(songList.get(0).song_path);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
