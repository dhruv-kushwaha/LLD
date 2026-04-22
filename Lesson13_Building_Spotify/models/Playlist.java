package Lesson13_Building_Spotify.models;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    List<Song> songList;
    String name;

    public Playlist(String name) {
        this.name = name;
        this.songList = new ArrayList<>();
    }

    public void addSong(Song song) {
        if (song == null)
            throw new NullPointerException("No song passed to add to the playlist!");

        songList.add(song);
    }

    public int getSize() {
        return songList.size();
    }

    public List<Song> getSongs() {
        return songList;
    }
}