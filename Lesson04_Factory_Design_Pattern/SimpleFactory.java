package Lesson04_Factory_Design_Pattern;

interface Burger {
    void prepare();
}

class BasicBurger implements Burger {
    @Override
    public void prepare() {
        System.out.println("Preparing basic burger");
    }
}

class StandardBurger implements Burger {
    @Override
    public void prepare() {
        System.out.println("Preparing standard burger");
    }
}

class PremiumBurger implements Burger {
    @Override
    public void prepare() {
        System.out.println("Preparing premium burger");
    }
}

class BurgerFactory {
    public Burger createBurger(String burger) {
        if (burger.equalsIgnoreCase(("basic"))) {
            return new BasicBurger();
        } else if (burger.equalsIgnoreCase("standard")) {
            return new StandardBurger();
        } else if (burger.equalsIgnoreCase(("premium"))) {
            return new PremiumBurger();
        } else {
            throw new IllegalArgumentException("Invalid burger type : " + burger);
        }
    }
}

public class SimpleFactory {
    public static void main(String[] args) {
        String type = "standard";
        BurgerFactory newBurgerFactory = new BurgerFactory();

        Burger burger = newBurgerFactory.createBurger(type);

        burger.prepare();
    }
}
