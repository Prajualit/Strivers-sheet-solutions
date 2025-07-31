public class SplitArray {

    public int splitArray(int[] nums, int k) {
        int start = 0;
        int end = 0;
        for (int num : nums) {
            start = Math.max(num, start);
            end += num;
        }

        int ans = end;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (canSplit(nums, mid, k)) {
                ans = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return ans;
    }

    public static boolean canSplit(int[] nums, int maxSumAllowed, int k) {
        int subArraysNeeded = 1;
        int currentSum = 0;

        for (int num : nums) {
            if (currentSum + num > maxSumAllowed) {
                subArraysNeeded++;
                currentSum = num;
            } else {
                currentSum += num;
            }
        }
        return subArraysNeeded <= k;
    }

    public static void main(String[] args) {

    }
}
