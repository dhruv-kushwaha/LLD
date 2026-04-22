package Lesson13_Building_Spotify.managers;

import java.util.HashMap;

import Lesson13_Building_Spotify.models.Playlist;
import Lesson13_Building_Spotify.models.Song;

public class PlaylistManager {
    private static PlaylistManager instance;

    private HashMap<String, Playlist> playlists;

    private PlaylistManager() {
        // private constructor
    }

    public static PlaylistManager getInstance() {
        if (instance == null) {
            synchronized (instance) {
                if (instance == null) {
                    instance = new PlaylistManager();
                }
            }
        }
        return instance;
    }

    public Playlist getPlaylist(String name) {
        if (!playlists.containsKey(name))
            throw new IllegalStateException("No playlist found with name : " + name);
        return playlists.get(name);
    }

    public void createPlaylist(String name) {
        if (playlists.containsKey(name))
            throw new IllegalStateException("Playlist with name : " + name + " already exists");

        playlists.put(name, new Playlist(name));
    }

    public void addSongToPlaylist(Song song, String playlistName) {
        if (song == null)
            throw new NullPointerException("No song passed!");

        if (!playlists.containsKey(playlistName))
            throw new RuntimeException("Playlist \"" + playlistName + "\" not found.");

        playlists.get(playlistName).addSong(song);
    }
}
