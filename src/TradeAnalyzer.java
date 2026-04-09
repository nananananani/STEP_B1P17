import java.util.*;

class Trade {
    String id;
    int volume;

    public Trade(String id, int volume) {
        this.id = id;
        this.volume = volume;
    }

    @Override
    public String toString() {
        return id + ":" + volume;
    }
}

public class TradeAnalyzer {
    public static void mergeSort(Trade[] trades, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(trades, left, mid);
            mergeSort(trades, mid + 1, right);
            merge(trades, left, mid, right);
        }
    }

    private static void merge(Trade[] trades, int left, int mid, int right) {
        Trade[] temp = new Trade[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (trades[i].volume <= trades[j].volume) temp[k++] = trades[i++];
            else temp[k++] = trades[j++];
        }
        while (i <= mid) temp[k++] = trades[i++];
        while (j <= right) temp[k++] = trades[j++];

        for (i = 0; i < temp.length; i++) trades[left + i] = temp[i];
    }
    public static void quickSort(Trade[] trades, int low, int high) {
        if (low < high) {
            int pi = partition(trades, low, high);
            quickSort(trades, low, pi - 1);
            quickSort(trades, pi + 1, high);
        }
    }

    private static int partition(Trade[] trades, int low, int high) {
        int pivot = trades[high].volume;
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (trades[j].volume > pivot) {
                i++;
                Trade temp = trades[i];
                trades[i] = trades[j];
                trades[j] = temp;
            }
        }
        Trade temp = trades[i + 1];
        trades[i + 1] = trades[high];
        trades[high] = temp;
        return i + 1;
    }

    public static void main(String[] args) {
        Trade[] morning = { new Trade("trade3", 500), new Trade("trade1", 100), new Trade("trade2", 300) };
        Trade[] mSortData = morning.clone();
        mergeSort(mSortData, 0, mSortData.length - 1);
        System.out.println("MergeSort: " + Arrays.toString(mSortData));
        Trade[] qSortData = morning.clone();
        quickSort(qSortData, 0, qSortData.length - 1);
        System.out.println("QuickSort (desc): " + Arrays.toString(qSortData));
        int totalVolume = 0;
        for (Trade t : morning) totalVolume += t.volume;
        System.out.println("Merged morning+afternoon total: " + totalVolume);
    }
}