package Lesson03_Strategy_Design_Pattern;

// Strategies
interface Walkable {
    void walk();
}

class NormalWalk implements Walkable {
    @Override
    public void walk() {
        System.out.println("Walking normally.");
    }
}

class NoWalk implements Walkable {
    @Override
    public void walk() {
        System.out.println("Cannot walk.");
    }
}

interface Flyable {
    void fly();
}

class NormalFly implements Flyable {
    @Override
    public void fly() {
        System.out.println("Flying normally.");
    }
}

class NoFly implements Flyable {
    @Override
    public void fly() {
        System.out.println("Cannot fly.");
    }
}

interface Talkable {
    void talk();
}

class NormalTalk implements Talkable {
    @Override
    public void talk() {
        System.out.println("Talking normally.");
    }
}

class NoTalk implements Talkable {
    @Override
    public void talk() {
        System.out.println("Cannot talk.");
    }
}

class Robot {
    private Walkable walkBehavior;
    private Flyable flyBehavior;
    private Talkable talkBehavior;

    public Robot(Walkable walkBehavior, Flyable flyBehavior, Talkable talkBehavior) {
        this.walkBehavior = walkBehavior;
        this.flyBehavior = flyBehavior;
        this.talkBehavior = talkBehavior;
    }

    public void walk() {
        walkBehavior.walk();
    }

    public void fly() {
        flyBehavior.fly();
    }

    public void talk() {
        talkBehavior.talk();
    }
}

public class StrategyDesignPattern {
    public static void main(String[] args) {
        // Now we can change the behavior at runtime
        Robot myRobot = new Robot(new NoWalk(), new NormalFly(), new NoTalk());

        myRobot.walk();
        myRobot.fly();
        myRobot.talk();
    }
}