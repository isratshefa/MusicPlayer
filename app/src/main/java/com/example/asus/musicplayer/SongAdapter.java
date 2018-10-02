package com.example.asus.musicplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SongAdapter extends ArrayAdapter<SongInfo> {
    private Context songContext;
    private List<SongInfo> songList = new ArrayList<>();

    public SongAdapter(Context context, List<SongInfo> list) {
        super(context, 0 , list);
        songContext = context;
        songList = list;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(songContext).inflate(R.layout.song_list_item,parent,false);

        SongInfo currentSong = songList.get(position);

        TextView song_title = (TextView) listItem.findViewById(R.id.song_title_id);
        song_title.setText(currentSong.song_title);

        TextView artist = (TextView) listItem.findViewById(R.id.artist_id);
        artist.setText(currentSong.artist);

        return listItem;
    }

}
