package Lesson13_Building_Spotify.device;

import Lesson13_Building_Spotify.external.WiredSpeakerAPI;
import Lesson13_Building_Spotify.models.Song;

public class WiredSpeakerAdapter implements IAudioOutputDevice {
    private WiredSpeakerAPI wiredSpeakerAPI;

    public WiredSpeakerAdapter(WiredSpeakerAPI wiredSpeakerAPI) {
        this.wiredSpeakerAPI = wiredSpeakerAPI;
    }

    @Override
    public void playSong(Song song) {
        String payload = "%s by %s".formatted(song.getTitle(), song.getArtist());
        wiredSpeakerAPI.playAudioViaWired(payload);
    }
}
