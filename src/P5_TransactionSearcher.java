public class P5_TransactionSearcher {
    public static void linearSearch(String[] logs, String target) {
        int firstIdx = -1;
        int lastIdx = -1;
        int comparisons = 0;

        for (int i = 0; i < logs.length; i++) {
            comparisons++;
            if (logs[i].equals(target)) {
                if (firstIdx == -1) firstIdx = i;
                lastIdx = i;
            }
        }
        System.out.println("Linear first " + target + ": index " + firstIdx +
                " (" + comparisons + " comparisons)");
    }
    public static void binarySearchWithCount(String[] logs, String target) {
        int low = 0;
        int high = logs.length - 1;
        int comparisons = 0;
        int foundIndex = -1;
        while (low <= high) {
            comparisons++;
            int mid = low + (high - low) / 2;
            int res = target.compareTo(logs[mid]);
            if (res == 0) {
                foundIndex = mid;
                break; // Found it!
            } else if (res > 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        int count = 0;
        if (foundIndex != -1) {
            count = 1;
            int left = foundIndex - 1;
            while (left >= 0 && logs[left].equals(target)) {
                count++;
                left--;
            }
            int right = foundIndex + 1;
            while (right < logs.length && logs[right].equals(target)) {
                count++;
                right++;
            }
        }
        System.out.println("Binary " + target + ": index " + foundIndex +
                " (" + comparisons + " comparisons), count=" + count);
    }
    public static void main(String[] args) {
        String[] logs = {"accA", "accB", "accB", "accC"};
        linearSearch(logs, "accB");
        binarySearchWithCount(logs, "accB");
    }
}