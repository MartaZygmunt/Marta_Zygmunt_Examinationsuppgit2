package cashregister;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CashRegister extends JFrame {

    //  Skapar produktobjekt med namn, pris och moms (%)
    Product product1 = new Product("Apple", 36.78, 12.0);
    Product product2 = new Product("Cake", 66.77, 25.0);
    Product product3 = new Product("Cheese", 25.00, 12.0);
    Product product4 = new Product("Bread", 15.00, 12.0);
    Product product5 = new Product("Butter", 22.00, 12.0);
    Product product6 = new Product("Wine", 40.00, 25.0);

    Product selectedProduct;
    Receipt currentReceipt = new Receipt();

    JTextField displayField = new JTextField(15);
    JTextField inputField = new JTextField("1", 5);
    JTextArea receiptArea = new JTextArea();


    JPanel mainPanel = new JPanel(new BorderLayout());
    JPanel leftPanel = new JPanel(new BorderLayout());

    //  Skapar huvudfönstret centrerad på skärmen
    public CashRegister() {
        setTitle("MARTAS Cash Register");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Skapar och lägger till paneler via void metoder i leftPanel
        createCenterPanel();
        createBottomPanel();

        //Skapar kvittoområdet till höger
        receiptArea.setEditable(false);
        receiptArea.setFont(new Font("Monospaced", Font.PLAIN,12));

        JScrollPane scrollPane = new JScrollPane(receiptArea);
        scrollPane.setPreferredSize(new Dimension(350,0));

        //Lägger till vänstra sidan i mitten och kvittot till höger i huvudpanelen
        mainPanel.add(leftPanel,BorderLayout.CENTER);
        mainPanel.add(scrollPane,BorderLayout.EAST);

        add(mainPanel);
    }

    // Hjälpmetod för att skapa mittpanelen och koppla listeners
    void createCenterPanel() {
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10, 10));
        centerPanel.setBackground(Color.ORANGE);

        JButton button1 = new JButton("Apple");
        JButton button2 = new JButton("Cake");
        JButton button3 = new JButton("Cheese");
        JButton button4 = new JButton("Bread");
        JButton button5 = new JButton("Butter");
        JButton button6 = new JButton("Wine");

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

        leftPanel.add(centerPanel, BorderLayout.CENTER);
    }

        // Hjälpmetod för att skapa den nedre panelen med inmatning och funktioner
        void createBottomPanel() {
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,20,15));
        bottomPanel.setBackground(Color.BLACK);
        bottomPanel.setPreferredSize(new Dimension(0, 65));

        //Användaren kan inte skriva i det manuellt
        displayField.setEditable(false);

        JLabel label = new JLabel("Quantity: ");
        label.setForeground(Color.WHITE);

        JButton addButton = new JButton("Add");
        JButton payButton = new JButton("Pay");

        // Hanterar klick på Add button: kollar om produkt är vald, validerar antalet, räknar ut priset och lägger till på kvittot.
            addButton.addActionListener(e ->{
            if (selectedProduct == null){
                JOptionPane.showMessageDialog(this, "Please select a product first!");
                return;
            }
            // Try catch skyddar programmet mot krasch om användaren skriver bokstäver i stället för siffror
            try {
                int quantity = Integer.parseInt((inputField.getText().trim()));

                if (quantity <= 0) {
                    JOptionPane.showMessageDialog(this, "Quantity must be greater than 0!");
                    return;
                }
                ReceiptRow row = new ReceiptRow(selectedProduct, quantity);
                currentReceipt.addRow(row);
                double lineTotal = row.getTotalprice();

                String line = String.format("%-15s %2d x %6.2f kr = %7.2f kr\n",
                        selectedProduct.getName(), quantity, selectedProduct.getPrice(), lineTotal);
                receiptArea.append(line);
                receiptArea.append(String.format("Total: %6.2f kr\n", currentReceipt.getTotalSum()));

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number of quantity!");
            }
        });

            // Slutför köpet, räknar ut moms (12 % och 25 %) och skriver ut kvittot
            payButton.addActionListener(e -> {

                if (currentReceipt.getRows().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Receipt is empty!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                double moms12 = 0;
                double netto12 = 0;
                double brutto12 = 0;

                double moms25 = 0;
                double netto25 = 0;
                double brutto25 = 0;


                for (ReceiptRow row : currentReceipt.getRows()) {
                    double rowTotal = row.getTotalprice();
                    double rowMoms = row.getMoms();
                    double rowNetto = rowTotal - rowMoms;

                    if (row.getProduct().getMoms() == 12.0) {
                        moms12 += rowMoms;
                        netto12 += rowNetto;
                        brutto12 += rowTotal;

                    } else if (row.getProduct().getMoms() == 25.0) {
                        moms25 += rowMoms;
                        netto25 += rowNetto;
                        brutto25 += rowTotal;
                    }
                }
                receiptArea.append("------------------------------------------------\n");
                receiptArea.append(String.format("RECEIPT NR: %d\n", currentReceipt.getReceiptNumber()));
                receiptArea.append("------------------------------------------------\n");
                receiptArea.append(String.format("%-10s %10s %10s %10s\n", "Moms%", "Moms", "Netto", "Brutto"));

                // Skriver ut raden för 12% om den har använts
                if (brutto12 > 0) {
                    receiptArea.append(String.format("%6.2f     %10.2f %10.2f %10.2f\n", 12.0, moms12, netto12, brutto12));
                }

                // Skriver ut raden för 25% om den har använts
                if (brutto25 > 0) {
                    receiptArea.append(String.format("%6.2f     %10.2f %10.2f %10.2f\n", 25.0, moms25, netto25, brutto25));
                }

                receiptArea.append("------------------------------------------------\n");
                receiptArea.append(String.format("TOTAL SUMMA: %16.2f kr\n",currentReceipt.getTotalSum()));
                receiptArea.append(String.format("TOTAL MOMS: %17.2f kr\n",currentReceipt.getTotalMoms()));
                receiptArea.append("THANK YOU FOR YOUR PURCHASE!\n\n");

                JOptionPane.showMessageDialog(leftPanel, String.format("Thank you! Please don't check your bank balance today!\nTotal: %.2f kr", currentReceipt.getTotalSum()));

                currentReceipt = new Receipt();
                selectedProduct = null;
                displayField.setText("");
                inputField.setText("1");
                receiptArea.setText("");
            });

            bottomPanel.add(displayField);
            bottomPanel.add(label);
            bottomPanel.add(inputField);
            bottomPanel.add(addButton);
            bottomPanel.add(payButton);

            // Lägger till det svarta fältet längst ner i vänsterpanelen
            leftPanel.add(bottomPanel, BorderLayout.SOUTH);
        }

        public static void main(String[] args) {
        CashRegister window = new CashRegister();
        window.setVisible(true);
    }
}