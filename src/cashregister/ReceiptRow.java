package cashregister;

public class ReceiptRow {
    //Kopplar ihop produkten med hur många som köps
    private Product product;
    private int quantity;

    // Skapar en rad när man lägger till en produkt
    public ReceiptRow(Product product, int quantity) {
        this.product = product;
        this.quantity= quantity;
    }
    // Räknar ut radens totala pris och moms
    public double getTotalprice() {
        return product.getPrice() * quantity;
    }

    public double getMoms() {
        return getTotalprice() * (product.getMoms() / 100.0);
    }

    public Product getProduct() {
            return product;
    }

    public int getQuantity() {
        return quantity;
        }
}



