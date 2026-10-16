package cashregister;

public class Product {

    // Lagrar produktens namn, pris och momssats.
    private String name;
    private double price;
    private double moms;

    public Product(String name, double price, double moms) {
        this.name = name;
        this.price = price;
        this.moms = moms;
    }

    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public double getMoms() {
            return moms;
        }
}