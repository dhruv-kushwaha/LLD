package Lesson07_Observer_Design_Pattern;

import java.util.ArrayList;
import java.util.List;

interface ISubscriber {
    void update();
}

interface IChannel {
    void subscribe(ISubscriber subscriber);

    void unsubscribe(ISubscriber subscriber);

    void notifySubscribers();
}

class Channel implements IChannel {
    private List<ISubscriber> subscribers;
    private String name;
    private String latestVideo;

    public Channel(String name) {
        this.name = name;
        this.subscribers = new ArrayList<>();
    }

    @Override
    public void subscribe(ISubscriber subscriber) {
        if (!subscribers.contains(subscriber))
            subscribers.add(subscriber);
    }

    @Override
    public void unsubscribe(ISubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers() {
        for (ISubscriber sub : subscribers) {
            sub.update();
        }
    }

    public void uploadVideo(String videoName) {
        latestVideo = videoName;
        System.out.println("\n[" + name + " uploaded \"" + videoName + "\"]");
        notifySubscribers();
    }

    public String getVideoData() {
        return "\nCheckout our new Video : " + latestVideo + "\n";
    }
}

class Subscriber implements ISubscriber {
    private String name;
    private Channel channel;

    public Subscriber(String name, Channel channel) {
        this.name = name;
        this.channel = channel;
    }

    @Override
    public void update() {
        System.out.println("Hey " + name + "," + channel.getVideoData());
    }
}

public class ObserverDesignPattern {
    public static void main(String[] args) {
        Channel channel = new Channel("DhruvIsAHero");

        Subscriber sub1 = new Subscriber("Dhruv", channel);
        Subscriber sub2 = new Subscriber("Parth", channel);

        channel.subscribe(sub1);
        channel.subscribe(sub2);

        // Upload a video: both Varun and Tarun are notified
        channel.uploadVideo("Observer Pattern Tutorial");

        // Varun unsubscribes; Tarun remains subscribed
        channel.unsubscribe(sub1);

        // Upload another video: only Tarun is notified
        channel.uploadVideo("Decorator Pattern Tutorial");
    }
}
