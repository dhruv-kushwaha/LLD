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

class BasicWheatBurger implements Burger {
    @Override
    public void prepare() {
        System.out.println("Preparing basic wheat burger");
    }
}

class StandardWheatBurger implements Burger {
    @Override
    public void prepare() {
        System.out.println("Preparing standard wheat burger");
    }
}

class PremiumWheatBurger implements Burger {
    @Override
    public void prepare() {
        System.out.println("Preparing premium wheat burger");
    }
}

interface BurgerFactory {
    Burger createBurger(String burger);
}

class SinghBurger implements BurgerFactory {
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

class KingBurger implements BurgerFactory {
    public Burger createBurger(String burger) {
        if (burger.equalsIgnoreCase(("basic"))) {
            return new BasicWheatBurger();
        } else if (burger.equalsIgnoreCase("standard")) {
            return new StandardWheatBurger();
        } else if (burger.equalsIgnoreCase(("premium"))) {
            return new PremiumWheatBurger();
        } else {
            throw new IllegalArgumentException("Invalid burger type : " + burger);
        }
    }
}

public class FactoryMethod {
    public static void main(String[] args) {
        String type = "standard";
        BurgerFactory singhBurgerFactory = new SinghBurger();

        Burger burger = singhBurgerFactory.createBurger(type);

        burger.prepare();
    }
}
