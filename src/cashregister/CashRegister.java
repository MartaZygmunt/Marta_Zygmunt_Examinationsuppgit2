package cashregister;

import javax.swing.*;
import java.awt.*;

public class CashRegister extends JFrame {

    //Skapar produktobjekt med namn och pris
    Product product1 = new Product("Apple", 36.78);
    Product product2 = new Product("Cake", 66.77);
    Product product3 = new Product("Cheese", 25.00);
    Product product4 = new Product("Bread", 15.00);
    Product product5 = new Product("Butter", 22.00);
    Product product6 = new Product("Wine", 40.00);

    // Variabler för att spara den valda produkten och totalsumman
    Product selectedProduct;
    double grandTotal = 0.0;

    //Instantvariabel för textfältet så att båda hjälpmetoderna kan nå det
    JTextField displayField = new JTextField(15);

    // Skapar huvudfönstret centrerad på skärmen
    public CashRegister() {
        setTitle("MARTAS Cash Register");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Skapar panelerna med hjälpmetoder
        JPanel centerPanel = createCenterPanel();
        JPanel bottomPanel = createBottomPanel();

        //Skapar kvittoområdet till höger
        JTextArea receiptArea = new JTextArea();
        receiptArea.setEditable(false);
        receiptArea.setFont(new Font("Monospaced", Font.PLAIN,12));

        JScrollPane scrollPane = new JScrollPane(receiptArea);
        scrollPane.setPreferredSize(new Dimension(350,0));

       //Lägger till alla delar i huvudpanelen
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        mainPanel.add(scrollPane, BorderLayout.EAST);

        add(mainPanel);
    }
        // Hjälpmetod för att skapa den nedre panelen
        JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
        bottomPanel.setBackground(Color.BLACK);

        //Användaren kan inte skriva i det manuellt
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
        // Hjälpmetod för att skapa mittpanelen och koppla listeners
        JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10, 10));
        centerPanel.setBackground(Color.ORANGE);

        JButton button1 = new JButton("Apple");
        JButton button2 = new JButton("Cake");
        JButton button3 = new JButton("Cheese");
        JButton button4 = new JButton("Bread");
        JButton button5 = new JButton("Butter");
        JButton button6 = new JButton("Wine");

        //Kopplar funktioner till knapparna: sparar produkten och visar namnet i textfältet
            button1.addActionListener(e -> {
                selectedProduct = product1;
                displayField.setText("Apple");
            });
            button2.addActionListener(e -> {
                selectedProduct = product2;
                displayField.setText("Cake");
            });
            button3.addActionListener(e -> {
                selectedProduct = product3;
                displayField.setText("Cheese");
            });
            button4.addActionListener(e -> {
                selectedProduct = product4;
                displayField.setText("Bread");
            });
            button5.addActionListener(e -> {
                selectedProduct = product5;
                displayField.setText("Butter");
            });
            button6.addActionListener(e -> {
                selectedProduct = product6;
                displayField.setText("Wine");
            });

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
