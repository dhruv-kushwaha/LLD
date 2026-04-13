package Lesson05_Singleton_Design_Pattern;

class Singleton {
    private static final Singleton instance = new Singleton();

    private Singleton() {
        System.out.println("Singleton class object is created.");
    }

    public static Singleton getInstance() {
        return instance;
    }
}

public class EagerInitializationSingleton {
    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        Singleton s3 = Singleton.getInstance();
        Singleton s4 = Singleton.getInstance();
    }
}
