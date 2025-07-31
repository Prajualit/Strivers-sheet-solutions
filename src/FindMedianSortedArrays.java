import java.util.ArrayList;
import java.util.List;

public class FindMedianSortedArrays {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        double result = 0;

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            list.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            list.add(nums2[i]);
        }
        list.sort(null);
        if (list.size() % 2 == 1) {
            result = (int) Math.ceil((double) list.get(list.size() / 2));
        } else {
            result = (double) (list.get(list.size() / 2) + list.get((list.size() / 2) - 1)) / 2;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }
}
