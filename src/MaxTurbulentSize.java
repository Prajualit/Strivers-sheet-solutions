public class MaxTurbulentSize {

    public static int maxTurbulenceSize(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return 1;
        }

        int maxLength = 1;
        int increasing = 1;
        int decreasing = 1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1]) {
                increasing = decreasing + 1;
                decreasing = 1;
            } else if (arr[i] < arr[i - 1]) {
                decreasing = increasing + 1;
                increasing = 1;
            } else {
                increasing = 1;
                decreasing = 1;
            }

            maxLength = Math.max(maxLength, Math.max(increasing, decreasing));
        }

        return maxLength;
    }

    public static void main(String[] args) {
        int[] arr = {9, 4, 2, 10, 7, 8, 8, 1, 9};
        System.out.println(maxTurbulenceSize(arr));
    }
}