import java.util.*;

class Asset {
    String ticker;
    double returnRate;
    double volatility;
    public Asset(String ticker, double returnRate, double volatility) {
        this.ticker = ticker;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }
    @Override
    public String toString() {
        return ticker + ":" + (int)returnRate + "%";
    }
}
public class P4_PortfolioSorter {
    public static void mergeSort(Asset[] assets, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(assets, left, mid);
            mergeSort(assets, mid + 1, right);
            merge(assets, left, mid, right);
        }
    }
    private static void merge(Asset[] assets, int left, int mid, int right) {
        Asset[] temp = new Asset[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            if (assets[i].returnRate <= assets[j].returnRate) temp[k++] = assets[i++];
            else temp[k++] = assets[j++];
        }
        while (i <= mid) temp[k++] = assets[i++];
        while (j <= right) temp[k++] = assets[j++];
        for (i = 0; i < temp.length; i++) assets[left + i] = temp[i];
    }
    public static void hybridQuickSort(Asset[] assets, int low, int high) {
        if (low + 10 > high) {
            insertionSort(assets, low, high);
            return;
        }
        if (low < high) {
            int pi = partition(assets, low, high);
            hybridQuickSort(assets, low, pi - 1);
            hybridQuickSort(assets, pi + 1, high);
        }
    }
    private static int partition(Asset[] assets, int low, int high) {
        int mid = low + (high - low) / 2;
        int pivotIndex = selectMedian(assets, low, mid, high);
        swap(assets, pivotIndex, high);
        Asset pivot = assets[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (assets[j].returnRate > pivot.returnRate ||
                    (assets[j].returnRate == pivot.returnRate && assets[j].volatility < pivot.volatility)) {
                i++;
                swap(assets, i, j);
            }
        }
        swap(assets, i + 1, high);
        return i + 1;
    }
    private static int selectMedian(Asset[] a, int l, int m, int h) {
        if ((a[l].returnRate < a[m].returnRate) != (a[l].returnRate < a[h].returnRate)) return l;
        if ((a[m].returnRate < a[l].returnRate) != (a[m].returnRate < a[h].returnRate)) return m;
        return h;
    }
    private static void swap(Asset[] a, int i, int j) {
        Asset temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    private static void insertionSort(Asset[] a, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            Asset key = a[i];
            int j = i - 1;
            while (j >= low && a[j].returnRate < key.returnRate) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }
    public static void main(String[] args) {
        Asset[] portfolio = {
                new Asset("AAPL", 12.0, 1.5),
                new Asset("TSLA", 8.0, 2.8),
                new Asset("GOOG", 15.0, 1.2)
        };
        Asset[] mData = portfolio.clone();
        mergeSort(mData, 0, mData.length - 1);
        System.out.println("Merge (asc): " + Arrays.toString(mData));
        Asset[] qData = portfolio.clone();
        hybridQuickSort(qData, 0, qData.length - 1);
        System.out.println("Quick (desc): " + Arrays.toString(qData));
    }
}