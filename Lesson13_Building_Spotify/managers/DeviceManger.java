package Lesson13_Building_Spotify.managers;

import Lesson13_Building_Spotify.device.IAudioOutputDevice;
import Lesson13_Building_Spotify.enums.DeviceType;
import Lesson13_Building_Spotify.factories.DeviceFactory;

public class DeviceManger {
    private static DeviceManger instance;
    public IAudioOutputDevice currentOutputDevice;

    private DeviceManger() {
        // private constructor
    }

    public static DeviceManger getInstance() {
        if (instance == null) {
            synchronized (instance) {
                if (instance == null) {
                    instance = new DeviceManger();
                }
            }
        }
        return instance;
    }

    public IAudioOutputDevice getAudioOutputDevice() throws NullPointerException {
        if (currentOutputDevice == null) {
            throw new NullPointerException("No output device is connected.");
        }
        return currentOutputDevice;
    }

    public boolean hasOutputDevice() {
        return currentOutputDevice == null ? false : true;
    }

    public void connect(DeviceType dt) {
        currentOutputDevice = DeviceFactory.getDevice(dt);

        switch (dt) {
            case BLUETOOTH_SPEAKER:
                System.out.println("Bluetooth speaker connected!");
                break;
            case WIRED_SPEAKER:
                System.out.println("Wired speaker connected!");
                break;
            case HEADPHONES:
                System.out.println("Headphones connected!");
                break;
        }
    }
}
