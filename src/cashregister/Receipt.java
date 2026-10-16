package cashregister;

import java.util.ArrayList;

public class Receipt {

    // Räknare för att ge varje kvitto ett unikt numer
    private static int receiptCounter = 1;
    private int receiptNumber;

    //Lista som sparar alla rader på kvittot
    private ArrayList<ReceiptRow> rows;

    public Receipt() {
        this.receiptNumber = receiptCounter++;
        this.rows = new ArrayList<>();
    }
    public void addRow(ReceiptRow row) {
        rows.add(row);
    }

    public ArrayList<ReceiptRow> getRows() {
        return rows;
    }

    public int getReceiptNumber() {
        return receiptNumber;
    }

    // Loopar igenom alla rader för att räkna ut totalsumman
    public double getTotalSum() {
        double sum = 0;
        for (ReceiptRow row : rows) {
            sum += row.getTotalprice();
        }
        return sum;
    }
    // Loopar igenom raderna för att summera den totala momsen
    public double getTotalMoms() {
        double totalMoms = 0;
        for (ReceiptRow row : rows) {
            totalMoms += row.getMoms();
        }
        return totalMoms;
    }
}