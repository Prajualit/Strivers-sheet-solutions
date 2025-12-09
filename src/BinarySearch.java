public class BinarySearch {

    public static int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (target > nums[mid]) {
                start = mid + 1;
            } else if (target < nums[mid]) {
                end = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void bubbleSort(int[] nums, int n) {
        if (n == nums.length) return;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                int temp = nums[i];
                nums[i] = nums[i - 1];
                nums[i - 1] = temp;
            }
            bubbleSort(nums, n + 1);
        }
    }

    public static void selectionSort(int[] nums, int n) {
        if (n == nums.length) return;

        int index = n;
        for (int i = n + 1; i < nums.length; i++) {
            if (nums[index] > nums[i]) {
                index = i;
            }
        }

        if (index != n) {
            int temp = nums[n];
            nums[n] = nums[index];
            nums[index] = temp;
        }

        selectionSort(nums, n + 1);
    }

    public static void insertionSort(int[] nums) {

        for (int i = 1; i < nums.length; i++) {

            int key = nums[i];
            int j = i - 1;

            while (j >= 0 && nums[j] > key) {
                nums[j + 1] = nums[j];
                j -= 1;
            }

            nums[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        int[] nums1 = {5, 3, 7, 2, 0, 9};
        int[] nums2 = {9, 4, 7, 2, 5, 6};

//        for (int j : nums1) {
//            System.out.print(j + " ");
//        }
//        System.out.println();
//        bubbleSort(nums1, 1);
//        for (int j : nums1) {
//            System.out.print(j + " ");
//        }
//
//        selectionSort(nums2, 0);
//        for (int j : nums2) {
//            System.out.print(j + " ");
//        }

        insertionSort(nums2);
        for (int j : nums2) {
            System.out.print(j + " ");
        }
//        System.out.println(search(nums, 9));
    }
}
