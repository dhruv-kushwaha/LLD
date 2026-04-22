package Lesson13_Building_Spotify.device;

import Lesson13_Building_Spotify.external.HeadphonesAPI;
import Lesson13_Building_Spotify.models.Song;

public class HeadphonesAdapter implements IAudioOutputDevice {
    private HeadphonesAPI bluetoothSpeakerAPI;

    public HeadphonesAdapter(HeadphonesAPI api) {
        this.bluetoothSpeakerAPI = api;
    }

    @Override
    public void playSong(Song song) {
        var payload = "%s by %s".formatted(song.getTitle(), song.getArtist());
        bluetoothSpeakerAPI.playAudioViaH(payload);
    }
}