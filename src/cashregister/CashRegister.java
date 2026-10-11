package cashregister;

import javax.swing.*;

public class CashRegister extends JFrame {

    Product product1 = new Product("Apple", 36.78);
    Product product2 = new Product("Cake", 66.77);
    Product product3 = new Product("Cheese", 25.00);
    Product product4 = new Product("Bread", 15.00);
    Product product5 = new Product("Butter", 22.00);
    Product product6 = new Product("Wine", 40.00);

    Product selectedProduct;
    double grandTotal = 0.0;

    public CashRegister() {
        setTitle("MARTAS Cash Register");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

        public static void main(String[] args) {
        CashRegister window = new CashRegister();
        window.setVisible(true);
    }
}
