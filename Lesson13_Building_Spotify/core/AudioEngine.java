package Lesson13_Building_Spotify.core;

import Lesson13_Building_Spotify.device.IAudioOutputDevice;
import Lesson13_Building_Spotify.models.Song;

public class AudioEngine {
    private Song currentSong;
    private boolean songIsPaused;

    public AudioEngine() {
        this.currentSong = null;
        this.songIsPaused = false;
    }

    public boolean songIsPaused() {
        return songIsPaused;
    }

    public String getCurrentSongTitle() {
        return currentSong.getTitle();
    }

    public void play(IAudioOutputDevice audioOutputDevice, Song song) {
        if (song == null)
            throw new NullPointerException("Song is null.");
        // if the song passed is the current song
        if (songIsPaused && song == currentSong) {
            songIsPaused = false;
            System.out.println("Resuming song : " + song.getTitle());
            audioOutputDevice.playSong(song);
            return;
        }

        currentSong = song;
        songIsPaused = false;
        System.out.println("Playing song : " + song.getTitle());
        audioOutputDevice.playSong(song);
    }

    public void pause() {
        if (currentSong == null)
            throw new NullPointerException("No song is currently playing.");

        if (songIsPaused)
            throw new IllegalStateException("Song is already paused!");

        songIsPaused = true;
        System.out.println("Pausing song : " + currentSong.getTitle());
    }
}
