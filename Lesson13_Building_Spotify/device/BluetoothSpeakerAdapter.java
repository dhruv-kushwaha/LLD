package Lesson13_Building_Spotify.device;

import Lesson13_Building_Spotify.external.BluetoothSpeakerAPI;
import Lesson13_Building_Spotify.models.Song;

public class BluetoothSpeakerAdapter implements IAudioOutputDevice {
    private BluetoothSpeakerAPI bluetoothSpeakerAPI;

    public BluetoothSpeakerAdapter(BluetoothSpeakerAPI api) {
        this.bluetoothSpeakerAPI = api;
    }

    @Override
    public void playSong(Song song) {
        var payload = "%s by %s".formatted(song.getTitle(), song.getArtist());
        bluetoothSpeakerAPI.playAudioViaB(payload);
    }
}