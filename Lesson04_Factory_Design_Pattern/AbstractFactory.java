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

interface GarlicBread {
    void prepare();
}

class BasicGarlicBread implements GarlicBread {
    @Override
    public void prepare() {
        System.out.println("Preparing basic garlic bread");
    }
}

class StandardGarlicBread implements GarlicBread {
    @Override
    public void prepare() {
        System.out.println("Preparing standard garlic bread");
    }
}

class PremiumGarlicBread implements GarlicBread {
    @Override
    public void prepare() {
        System.out.println("Preparing premium garlic bread");
    }
}

class BasicWheatGarlicBread implements GarlicBread {
    @Override
    public void prepare() {
        System.out.println("Preparing basic wheat garlic bread");
    }
}

class StandardWheatGarlicBread implements GarlicBread {
    @Override
    public void prepare() {
        System.out.println("Preparing standard wheat garlic bread");
    }
}

class PremiumWheatGarlicBread implements GarlicBread {
    @Override
    public void prepare() {
        System.out.println("Preparing premium wheat garlic bread");
    }
}

interface MealFactory {
    Burger createBurger(String burger);

    GarlicBread createGarlicBread(String garlicBread);
}

class SinghBurger implements MealFactory {
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

    public GarlicBread createGarlicBread(String garlicBread) {
        if (garlicBread.equalsIgnoreCase("basic")) {
            return new BasicGarlicBread();
        } else if (garlicBread.equalsIgnoreCase("standard")) {
            return new StandardGarlicBread();
        } else if (garlicBread.equalsIgnoreCase("premium")) {
            return new PremiumGarlicBread();
        } else {
            throw new IllegalArgumentException("Invalid garlic bread type : " + garlicBread);
        }
    }
}

class KingBurger implements MealFactory {
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

    public GarlicBread createGarlicBread(String garlicBread) {
        if (garlicBread.equalsIgnoreCase("basic")) {
            return new BasicWheatGarlicBread();
        } else if (garlicBread.equalsIgnoreCase("standard")) {
            return new StandardWheatGarlicBread();
        } else if (garlicBread.equalsIgnoreCase("premium")) {
            return new PremiumWheatGarlicBread();
        } else {
            throw new IllegalArgumentException("Invalid garlic bread type : " + garlicBread);
        }
    }
}

public class AbstractFactory {
    public static void main(String[] args) {
        String type = "standard";
        MealFactory singhMealFactory = new SinghBurger();

        Burger burger = singhMealFactory.createBurger(type);
        GarlicBread garlicBread = singhMealFactory.createGarlicBread(type);

        burger.prepare();
        garlicBread.prepare();
    }
}
