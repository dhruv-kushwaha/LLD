package Lesson13_Building_Spotify.strategies;

import java.util.List;
import java.util.Random;
import java.util.Stack;

import Lesson13_Building_Spotify.models.Playlist;
import Lesson13_Building_Spotify.models.Song;

public class RandomPlayStrategy implements PlayStrategy {
    private List<Playlist> currentPlaylist;
    private List<Song> remainingSongs;
    private Stack<Song> history;
    private Random random;

    @Override
    public void setPlaylist(Playlist playlist) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPlaylist'");
    }

    @Override
    public boolean hasNext() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hasNext'");
    }

    @Override
    public Song next() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'next'");
    }

    @Override
    public boolean hasPrevious() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hasPrevious'");
    }

    @Override
    public Song previous() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'previous'");
    }

}
