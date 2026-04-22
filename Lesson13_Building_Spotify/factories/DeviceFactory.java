package Lesson13_Building_Spotify.factories;

import Lesson13_Building_Spotify.device.BluetoothSpeakerAdapter;
import Lesson13_Building_Spotify.device.HeadphonesAdapter;
import Lesson13_Building_Spotify.device.IAudioOutputDevice;
import Lesson13_Building_Spotify.device.WiredSpeakerAdapter;
import Lesson13_Building_Spotify.enums.DeviceType;
import Lesson13_Building_Spotify.external.BluetoothSpeakerAPI;
import Lesson13_Building_Spotify.external.HeadphonesAPI;
import Lesson13_Building_Spotify.external.WiredSpeakerAPI;

public class DeviceFactory {

    public static IAudioOutputDevice getDevice(DeviceType deviceType) throws IllegalStateException {
        switch (deviceType) {
            case DeviceType.BLUETOOTH_SPEAKER:
                return new BluetoothSpeakerAdapter(new BluetoothSpeakerAPI());

            case DeviceType.WIRED_SPEAKER:
                return new WiredSpeakerAdapter(new WiredSpeakerAPI());

            case DeviceType.HEADPHONES:
                return new HeadphonesAdapter(new HeadphonesAPI());

            default:
                throw new IllegalStateException("Invalid device type : %s".formatted(deviceType));
        }
    }
}
