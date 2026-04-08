package Lesson01_SolidPrinciples.srp;

import java.util.ArrayList;

class ShoppingCart {
    private ArrayList<Product> items;

    public ShoppingCart(ArrayList<Product> items) {
        this.items = items;
    }

    public void addProduct(Product p) {
        items.add(p);
    }

    public void removeProduct(Product product) {
        Product itemToRemove = items.stream()
                .filter(p -> p.name.equalsIgnoreCase(product.name))
                .findFirst()
                .orElse(null);

        if (itemToRemove != null) {
            items.remove(itemToRemove);
        }
    }

    public double calculateTotal() {
        return items.stream()
                .map(product -> product.price)
                .reduce(0.0, (sum, price) -> sum + price);

        // alternative
        // return items.stream()
        // .mapToDouble(p -> p.price)
        // .sum();
    }

    public void printInvoice() {
        System.out.println("Invoice:");
        items.forEach(product -> System.out.println(product.name + ": $" + product.price));
        System.out.println("Total: $" + calculateTotal());
    }

    public void saveToDatabase() {
        // code to save the shopping cart to a database
        // add a timer to simulate the time taken to save to the database
        try {
            Thread.sleep(2000); // simulate time taken to save to database
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Violated {
    public static void main(String[] args) {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", 999.99));

        products.add(new Product("Mouse", 49.99));

        ShoppingCart cart = new ShoppingCart(products);
        System.out.println("Total: $" + cart.calculateTotal());
        cart.printInvoice();
        cart.saveToDatabase();
    }
}