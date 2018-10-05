package com.example.asus.musicplayer;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

public class SongList{
    List<SongInfo> songList;
    private static SongList instance;
    private Context context;

    SongList(Context context)
    {
        this.context = context;
        songList = getAllAudioFromDevice(context);
    }

    public static SongList getInstance(Context context)
    {
        if (instance == null)
        {
            instance = new  SongList(context);
        }
        return instance;
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
