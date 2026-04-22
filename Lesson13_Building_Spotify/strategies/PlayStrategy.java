package Lesson13_Building_Spotify.strategies;

import Lesson13_Building_Spotify.models.Playlist;
import Lesson13_Building_Spotify.models.Song;

public interface PlayStrategy {
    void setPlaylist(Playlist playlist);

    boolean hasNext();

    Song next();

    boolean hasPrevious();

    Song previous();

    default void addToNext(Song song) {
    }
}