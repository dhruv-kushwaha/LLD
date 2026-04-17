package Lesson09_Building_Notification_Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

interface INotification {
    String getContent();
}

class TextNotification implements INotification {
    private String msg;

    public TextNotification(String message) {
        this.msg = message;
    }

    @Override
    public String getContent() {
        return this.msg;
    }
}

class HtmlNotification implements INotification {
    private String msg;

    public HtmlNotification(String message) {
        this.msg = message;
    }

    @Override
    public String getContent() {
        return this.msg;
    }
}

abstract class INotificationDecorator implements INotification {
    protected INotification notification;

    public INotificationDecorator(INotification notification) {
        this.notification = notification;
    }
}

class TimeStampDecorator extends INotificationDecorator {
    public TimeStampDecorator(INotification notification) {
        super(notification);
    }

    @Override
    public String getContent() {
        return this.notification.getContent() + " At time: " + LocalDateTime.now();
    }
}

class HtmlFooterDecorator extends INotificationDecorator {
    public HtmlFooterDecorator(INotification notification) {
        super(notification);
    }

    @Override
    public String getContent() {
        return this.notification.getContent() + "\n<footer>This is the footer</footer>";
    }
}

interface IObservable {
    void add(IObserver observer);

    void remove(IObserver observer);

    void notifyObservers();
}

interface IObserver {
    void update();
}

class NotificationObservable implements IObservable {
    private INotification currentNotification;
    private List<IObserver> observers;

    public NotificationObservable() {
        observers = new ArrayList<>();
    }

    @Override
    public void add(IObserver observer) {
        if (!this.observers.contains(observer))
            this.observers.add(observer);
    }

    @Override
    public void remove(IObserver observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (IObserver observer : observers) {
            observer.update();
        }
    }

    public void setNotification(INotification notification) {
        this.currentNotification = notification;
        notifyObservers();
    }

    public INotification getNotification() {
        return currentNotification;
    }

    public String getNotificationContent() {
        return currentNotification.getContent();
    }
}

class LoggingEngine implements IObserver {
    private NotificationObservable observable;
    private List<ILoggingStrategy> loggingStrategies;

    public LoggingEngine(NotificationObservable observable) {
        this.observable = observable;
        loggingStrategies = new ArrayList<>();
    }

    @Override
    public void update() {
        String message = observable.getNotificationContent();

        for (ILoggingStrategy iLoggingStrategy : loggingStrategies) {
            iLoggingStrategy.log(message);
        }
    }

    public void addLoggingStrategy(ILoggingStrategy ls) {
        if (!loggingStrategies.contains(ls))
            loggingStrategies.add(ls);
    }
}

interface ILoggingStrategy {
    void log(String message);
}

class ConsoleLogger implements ILoggingStrategy {
    @Override
    public void log(String message) {
        System.out.println("Logging to console: " + message);
    }
}

class FileLogger implements ILoggingStrategy {
    @Override
    public void log(String message) {
        System.out.println("Logging to file: " + message);
    }
}

class NotificationEngine implements IObserver {
    private NotificationObservable observable;
    private List<INotificationStrategy> notificationStrategies;

    public NotificationEngine(NotificationObservable observable) {
        this.observable = observable;
        notificationStrategies = new ArrayList<>();
    }

    @Override
    public void update() {
        String message = observable.getNotificationContent();

        for (INotificationStrategy iNotificationStrategy : notificationStrategies) {
            iNotificationStrategy.log(message);
        }
    }

    public void addNotificationStrategy(INotificationStrategy ns) {
        if (!notificationStrategies.contains(ns))
            notificationStrategies.add(ns);
    }
}

interface INotificationStrategy {
    void log(String message);
}

class SmsStrategy implements INotificationStrategy {
    private String mobileNumber;

    public SmsStrategy(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public void log(String message) {
        System.out.println("Sending sms via " + mobileNumber + ": " + message);
    }
}

class EmailStrategy implements INotificationStrategy {
    private String email;

    public EmailStrategy(String email) {
        this.email = email;
    }

    @Override
    public void log(String message) {
        System.out.println("Sending email via " + email + ": " + message);
    }
}

class NotificationService {
    private static NotificationService instance;
    private NotificationObservable observable;
    private List<INotification> notifications = new ArrayList<>();

    private NotificationService() {
        observable = new NotificationObservable();
    }

    public static NotificationService getInstance() {
        if (instance == null)
            instance = new NotificationService();
        return instance;
    }

    public NotificationObservable getObservable() {
        return observable;
    }

    public void sendNotification(INotification notification) {
        notifications.add(notification);
        observable.setNotification(notification);
    }
}

public class NotificationSystem {
    public static void main(String[] args) {
        NotificationService notificationService = NotificationService.getInstance();
        NotificationObservable observable = notificationService.getObservable();

        NotificationEngine notificationEngine = new NotificationEngine(observable);
        notificationEngine.addNotificationStrategy(new EmailStrategy("dhruv.kushwaha@gmail.com"));
        notificationEngine.addNotificationStrategy(new SmsStrategy("+91 99988 77755"));

        LoggingEngine loggingEngine = new LoggingEngine(observable);
        loggingEngine.addLoggingStrategy(new ConsoleLogger());

        observable.add(notificationEngine);
        observable.add(loggingEngine);

        INotification notification = new TextNotification("50 % discount on mens apparel");
        notification = new TimeStampDecorator(notification);

        notificationService.sendNotification(notification);

        loggingEngine.addLoggingStrategy(new FileLogger());

        notification = new HtmlFooterDecorator(notification);
        notificationService.sendNotification(notification);
    }
}
