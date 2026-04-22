package Lesson13_Building_Spotify.strategies;

import Lesson13_Building_Spotify.models.Playlist;
import Lesson13_Building_Spotify.models.Song;

public class SequentialPlayStrategy implements PlayStrategy {
    private Playlist currentPlaylist;
    private int currentIndex;

    public SequentialPlayStrategy() {
        currentPlaylist = null;
        currentIndex = -1;
    }

    @Override
    public void setPlaylist(Playlist playlist) {
        currentPlaylist = playlist;
        currentIndex = -1;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < currentPlaylist.getSize() - 1;
    }

    @Override
    public Song next() {
        if (currentPlaylist == null || currentPlaylist.getSize() == 0) {
            throw new IllegalStateException(
                    "Either playlist is not selected or the selected playlist does not have any songs");
        }

        if (!hasNext()) {
            throw new IllegalStateException("No more songs are");
        }
        return currentPlaylist.getSongs().get(++currentIndex);
    }

    @Override
    public boolean hasPrevious() {
        return (currentIndex - 1 > 0);
    }

    @Override
    public Song previous() {
        if (currentPlaylist == null || currentPlaylist.getSize() == 0) {
            throw new IllegalStateException(
                    "Either playlist is not selected or the selected playlist does not have any songs");
        }

        if (!hasPrevious()) {
            throw new IllegalStateException("No more songs are present in the playlist");
        }

        return currentPlaylist.getSongs().get(--currentIndex);
    }

}
