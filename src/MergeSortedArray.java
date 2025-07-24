import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSortedArray {

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            list.add(nums1[i]);
        }
        for (int i = 0; i < n; i++) {
            list.add(nums2[i]);
        }
        for (int i = 0; i < list.size(); i++) {
            nums1[i] = list.get(i);
        }
        Arrays.sort(nums1);
    }

    public static void main(String[] args) {

        int[] nums1 = {0};
        int[] nums2 = {1};

        merge(nums1, 0, nums2, 1);

        for (int i = 0; i < nums1.length; i++) {
            System.out.print(nums1[i] + " ");
        }
    }
}
