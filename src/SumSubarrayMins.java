import java.util.Stack;

public class SumSubarrayMins {
    public static int sumSubarrayMins(int[] arr) {
        int MOD = 1_000_000_007;
        int n = arr.length;

        int[] left = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                left[i] = i + 1;
            } else {
                left[i] = i - stack.peek();
            }
            stack.push(i);
        }

        int[] right = new int[n];
        stack.clear();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                right[i] = n - i;
            } else {
                right[i] = stack.peek() - i;
            }
            stack.push(i);
        }

        long totalSum = 0;
        for (int i = 0; i < n; i++) {
            long contribution = (long) arr[i] * left[i] * right[i];
            totalSum = (totalSum + contribution) % MOD;
        }

        return (int) totalSum;
    }

    public static void main(String[] args) {
        int[] arr1 = {3, 1, 2, 4};
        System.out.println("Sum for [3, 1, 2, 4]: " + sumSubarrayMins(arr1));

        int[] arr2 = {11, 81, 94, 43, 3};
        System.out.println("Sum for [11, 81, 94, 43, 3]: " + sumSubarrayMins(arr2));
    }
}
