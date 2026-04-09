import java.util.ArrayList;
import java.util.List;
class Transaction {
    String id;
    double fee;
    long timestamp;

    public Transaction(String id, double fee, long timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }
    @Override
    public String toString() {
        return id + ":" + fee + (timestamp > 0 ? "@" + timestamp : "");
    }
}

public class P1_FeeSorter{
    public static void bubbleSort(List<Transaction> transactions) {
        int n = transactions.size();
        int swaps = 0;
        int passes = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            passes++;
            for (int j = 0; j < n - i - 1; j++) {
                if (transactions.get(j).fee > transactions.get(j + 1).fee) {
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
    public static void insertionSort(List<Transaction> transactions) {
        int n = transactions.size();
        for (int i = 1; i < n; ++i) {
            Transaction key = transactions.get(i);
            int j = i - 1;
            while (j >= 0 && (transactions.get(j).fee > key.fee ||
                    (transactions.get(j).fee == key.fee && transactions.get(j).timestamp > key.timestamp))) {
                transactions.set(j + 1, transactions.get(j));
                j = j - 1;
            }
            transactions.set(j + 1, key);
        }
        System.out.println("InsertionSort (fee+ts): " + transactions);
    }
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
    }public static void main(String[] args) {
        List<Transaction> data = new ArrayList<>();
        data.add(new Transaction("id1", 10.5, 1000));
        data.add(new Transaction("id2", 25.0, 930));
        data.add(new Transaction("id3", 5.0, 1015));
        System.out.println("--- Audit Report ---");
        bubbleSort(new ArrayList<>(data));
        insertionSort(new ArrayList<>(data));
        flagOutliers(data);
    }
}