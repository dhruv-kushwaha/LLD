package Lesson01_SolidPrinciples.ocp;

import java.util.ArrayList;
import Lesson01_SolidPrinciples.Product;

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

interface ShoppingCartPersistence {
    void save();
}

// Violating OCP: Whenever a new kind of storage is introduced we have to modify
// the existing class

class SqlDbPersistence implements ShoppingCartPersistence {
    @Override
    public void save() {
        System.out.println("Saving to sql db");
    }
}

class MongoDbPersistence implements ShoppingCartPersistence {
    @Override
    public void save() {
        System.out.println("Saving to mongo db");
    }
}

class FilePersistence implements ShoppingCartPersistence {
    @Override
    public void save() {
        System.out.println("Saving to file system");
    }
}

public class OCPFollowed {
    public static void main(String[] args) {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", 999.99));

        products.add(new Product("Mouse", 49.99));

        ShoppingCart cart = new ShoppingCart(products);
        System.out.println("Total: $" + cart.calculateTotal());
        ShoppingCartPrinter printer = new ShoppingCartPrinter(cart);
        ShoppingCartPersistence sqlStorage = new SqlDbPersistence();
        ShoppingCartPersistence mongoStorage = new MongoDbPersistence();
        ShoppingCartPersistence fileStorage = new FilePersistence();

        printer.printInvoice();
        sqlStorage.save();
        mongoStorage.save();
        fileStorage.save();
    }
}