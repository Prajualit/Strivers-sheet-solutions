public class FindKthPositive {

    public int findKthPositive(int[] arr, int k) {

        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            int missingCount = arr[mid] - (mid + 1);

            if (missingCount < k) {
                start = mid + 1;
            } else {
                end = mid + 1;
            }
        }
        return start + k;


    }

    public static void main(String[] args) {

    }
}
