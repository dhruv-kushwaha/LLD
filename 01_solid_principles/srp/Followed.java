package srp;

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

    public ArrayList<Product> getItems() {
        return items;
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
}

class ShoppingCartPrinter {
    private ShoppingCart cart;

    public ShoppingCartPrinter(ShoppingCart cart) {
        this.cart = cart;
    }

    public void printInvoice() {
        System.out.println("Invoice:");
        cart.getItems().forEach(product -> System.out.println(product.name + ": $" + product.price));
        System.out.println("Total: $" + cart.calculateTotal());
    }
}

class ShoppingCartStorage {
    private ShoppingCart cart;

    public ShoppingCartStorage(ShoppingCart cart) {
        this.cart = cart;
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

public class Followed {
    public static void main(String[] args) {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", 999.99));

        products.add(new Product("Mouse", 49.99));

        ShoppingCart cart = new ShoppingCart(products);
        System.out.println("Total: $" + cart.calculateTotal());
        ShoppingCartPrinter printer = new ShoppingCartPrinter(cart);
        ShoppingCartStorage storage = new ShoppingCartStorage(cart);
        printer.printInvoice();
        storage.saveToDatabase();
    }
}