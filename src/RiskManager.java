import java.util.*;

class Client {
    String name;
    int riskScore;
    double accountBalance;

    public Client(String name, int riskScore, double accountBalance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return name + ":" + riskScore;
    }
}
public class RiskManager {
    public static void bubbleSortAsc(Client[] clients) {
        int n = clients.length;
        int swaps = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (clients[j].riskScore > clients[j + 1].riskScore) {
                    // Swap
                    Client temp = clients[j];
                    clients[j] = clients[j + 1];
                    clients[j + 1] = temp;
                    swaps++;
                }
            }
        }
        System.out.println("Bubble (asc): " + Arrays.toString(clients) + " // Swaps: " + swaps);
    }
    public static void insertionSortDesc(Client[] clients) {
        int n = clients.length;
        for (int i = 1; i < n; i++) {
            Client key = clients[i];
            int j = i - 1;
            while (j >= 0 && (clients[j].riskScore < key.riskScore ||
                    (clients[j].riskScore == key.riskScore && clients[j].accountBalance < key.accountBalance))) {
                clients[j + 1] = clients[j];
                j = j - 1;
            }
            clients[j + 1] = key;
        }
        System.out.println("Insertion (desc): " + Arrays.toString(clients));
    }

    public static void main(String[] args) {
        Client[] clients = {
                new Client("clientC", 80, 1000.0),
                new Client("clientA", 20, 2000.0),
                new Client("clientB", 50, 1500.0)
        };

        System.out.println("Input: " + Arrays.toString(clients));
        Client[] bubbleData = clients.clone();
        bubbleSortAsc(bubbleData);
        Client[] insertionData = clients.clone();
        insertionSortDesc(insertionData);
        System.out.print("Top 3 risks: ");
        for (int i = 0; i < Math.min(3, insertionData.length); i++) {
            System.out.print(insertionData[i].name + "(" + insertionData[i].riskScore + ")" +
                    (i < 2 ? ", " : ""));
        }
    }
}