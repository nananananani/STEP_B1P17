import java.util.ArrayList;
import java.util.List;

// This class represents a single Bank Receipt
class Transaction {
    String id;
    double fee;
    long timestamp; // Time represented as a number (e.g., 1015 for 10:15)

    public Transaction(String id, double fee, long timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    // This tells IntelliJ how to "print" the receipt in the console
    @Override
    public String toString() {
        return id + ":" + fee + (timestamp > 0 ? "@" + timestamp : "");
    }
}

public class FeeSorter{

    // BUBBLE SORT: Good for tiny batches (swaps neighbors)
    public static void bubbleSort(List<Transaction> transactions) {
        int n = transactions.size();
        int swaps = 0;
        int passes = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            passes++;
            for (int j = 0; j < n - i - 1; j++) {
                if (transactions.get(j).fee > transactions.get(j + 1).fee) {
                    // Swap the items
                    Transaction temp = transactions.get(j);
                    transactions.set(j, transactions.get(j + 1));
                    transactions.set(j + 1, temp);
                    swapped = true;
                    swaps++;
                }
            }
            if (!swapped) break; // Stop if already sorted!
        }
        System.out.println("BubbleSort (fees): " + transactions + " // " + passes + " passes, " + swaps + " swaps");
    }

    // INSERTION SORT: Good for medium batches (slides items into place)
    public static void insertionSort(List<Transaction> transactions) {
        int n = transactions.size();
        for (int i = 1; i < n; ++i) {
            Transaction key = transactions.get(i);
            int j = i - 1;

            // Check Fee first; if Fees are equal, check the Time (Timestamp)
            while (j >= 0 && (transactions.get(j).fee > key.fee ||
                    (transactions.get(j).fee == key.fee && transactions.get(j).timestamp > key.timestamp))) {
                transactions.set(j + 1, transactions.get(j));
                j = j - 1;
            }
            transactions.set(j + 1, key);
        }
        System.out.println("InsertionSort (fee+ts): " + transactions);
    }

    // OUTLIER CHECK: Looks for suspicious fees
    public static void flagOutliers(List<Transaction> transactions) {
        List<String> outliers = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.fee > 50.0) {
                outliers.add(t.id);
            }
        }
        System.out.print("High-fee outliers: ");
        if (outliers.isEmpty()) {
            System.out.println("none");
        } else {
            System.out.println(outliers);
        }
    }

    public static void main(String[] args) {
        // Create our messy pile of transactions
        List<Transaction> data = new ArrayList<>();
        data.add(new Transaction("id1", 10.5, 1000));
        data.add(new Transaction("id2", 25.0, 930));
        data.add(new Transaction("id3", 5.0, 1015));

        // Run the audit
        System.out.println("--- Audit Report ---");
        bubbleSort(new ArrayList<>(data)); // We use 'new ArrayList' to keep the original safe
        insertionSort(new ArrayList<>(data));
        flagOutliers(data);
    }
}