# Strategy vs Factory — Simple Notes (Payment + Pizza Examples)

---

# 🧠 Core Difference

* **Factory** → *kaunsa object banana hai*
* **Strategy** → *kaunsa behavior use karna hai*

👉 Factory = **creation**
👉 Strategy = **behavior**

---

# 1️⃣ Strategy Pattern (Payment Example)

## Idea

Different payment methods, but **same operation → pay()**
Behavior change hota hai runtime par.

### Step 1 — Strategy interface

```java
interface PaymentStrategy {
    void pay(int amount);
}
```

### Step 2 — Different strategies

```java
class UPIPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paying via UPI: " + amount);
    }
}

class CardPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paying via Card: " + amount);
    }
}
```

### Step 3 — Context

```java
class PaymentContext {
    private PaymentStrategy strategy;

    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void checkout(int amount) {
        System.out.println("Starting payment...");
        strategy.pay(amount);
        System.out.println("Payment completed");
    }
}
```

### Step 4 — Client

```java
PaymentContext context = new PaymentContext();

context.setStrategy(new UPIPayment());
context.checkout(100);

context.setStrategy(new CardPayment());
context.checkout(200);
```

### Samajh lo

* Context ko nahi pata UPI kya hai
* Bas jo strategy milegi usko call karega
* Behavior runtime par change ho gaya

---

# 2️⃣ Factory Pattern (Payment Example)

## Idea

Object create karne ka logic hide karna

### Factory

```java
class PaymentFactory {
    public static PaymentStrategy create(String type) {
        if(type.equals("UPI"))
            return new UPIPayment();
        else if(type.equals("CARD"))
            return new CardPayment();
        throw new IllegalArgumentException();
    }
}
```

### Client

```java
PaymentStrategy strategy = PaymentFactory.create("UPI");

PaymentContext context = new PaymentContext();
context.setStrategy(strategy);
context.checkout(100);
```

### Samajh lo

* Client ko `new UPIPayment()` nahi likhna
* Factory decide karega kaunsa object banana hai

---

# 🍕 Pizza Example — Strategy

## Behavior change: cooking style

```java
interface CookingStrategy {
    void cook();
}

class ThinCrust implements CookingStrategy {
    public void cook() {
        System.out.println("Cooking thin crust pizza");
    }
}

class CheeseBurst implements CookingStrategy {
    public void cook() {
        System.out.println("Cooking cheese burst pizza");
    }
}
```

### Context

```java
class Pizza {
    private CookingStrategy strategy;

    public void setStrategy(CookingStrategy strategy) {
        this.strategy = strategy;
    }

    public void prepare() {
        System.out.println("Preparing pizza...");
        strategy.cook();
    }
}
```

### Client

```java
Pizza pizza = new Pizza();

pizza.setStrategy(new ThinCrust());
pizza.prepare();

pizza.setStrategy(new CheeseBurst());
pizza.prepare();
```

👉 Same pizza
👉 Cooking style change

---

# 🍕 Pizza Example — Factory

```java
class PizzaFactory {
    public static Pizza create(String type) {
        if(type.equals("VEG"))
            return new Pizza();
        else if(type.equals("NONVEG"))
            return new Pizza();
        return null;
    }
}
```

Client:

```java
Pizza pizza = PizzaFactory.create("VEG");
```

👉 Factory decides kaunsa pizza object banega

---

# 🔥 Final Comparison

| Pattern  | Kya change hota hai | Example             |
| -------- | ------------------- | ------------------- |
| Strategy | Behavior            | UPI vs Card         |
| Factory  | Object creation     | Veg vs NonVeg pizza |

---

# 🧠 Memory Trick

* Factory → **"new ko hide karo"**
* Strategy → **"if-else ko replace karo"**
* Context → **strategy ko call karta hai**

---
