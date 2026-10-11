package cashregister;

import javax.swing.*;
import java.awt.*;

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

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel centerPanel = createCenterPanel();

        JPanel bottomPanel = createBottomPanel();

        JTextArea receiptArea = new JTextArea();
        receiptArea.setEditable(false);
        receiptArea.setFont(new Font("Monospaced", Font.PLAIN,12));

        JScrollPane scrollPane = new JScrollPane(receiptArea);
        scrollPane.setPreferredSize(new Dimension(300,0));

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        mainPanel.add(scrollPane, BorderLayout.EAST);

        add(mainPanel);
    }

    private static JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
        bottomPanel.setBackground(Color.BLACK);

        JTextField displayField = new JTextField(15);
        displayField.setEditable(false);

        JLabel label = new JLabel("Quantity: ");
        label.setForeground(Color.WHITE);

        JTextField inputField = new JTextField("1", 5);

        JButton addButton = new JButton("Add");
        JButton payButton = new JButton("Pay");

        bottomPanel.add(displayField);
        bottomPanel.add(label);
        bottomPanel.add(inputField);
        bottomPanel.add(addButton);
        bottomPanel.add(payButton);
        return bottomPanel;
    }

    private static JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10, 10));
        centerPanel.setBackground(Color.ORANGE);

        JButton button1 = new JButton("Apple");
        JButton button2 = new JButton("Cake");
        JButton button3 = new JButton("Cheese");
        JButton button4 = new JButton("Bread");
        JButton button5 = new JButton("Butter");
        JButton button6 = new JButton("Wine");

        centerPanel.add(button1);
        centerPanel.add(button2);
        centerPanel.add(button3);
        centerPanel.add(button4);
        centerPanel.add(button5);
        centerPanel.add(button6);
        return centerPanel;
    }

    public static void main(String[] args) {
        CashRegister window = new CashRegister();
        window.setVisible(true);
    }
}
