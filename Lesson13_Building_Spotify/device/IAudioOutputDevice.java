package Lesson13_Building_Spotify.device;

import Lesson13_Building_Spotify.models.Song;

public interface IAudioOutputDevice {
    void playSong(Song song);
}
