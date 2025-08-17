import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class NextGreaterElement {

    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {

        HashMap<Integer, Integer> map = new HashMap<>();

        Stack<Integer> stack = new Stack<>();

        for (int n : nums2) {
            while (!stack.isEmpty() && n > stack.peek()) {
                map.put(stack.pop(), n);
            }
            stack.push(n);
        }

        int[] result = new int[nums1.length];

        for (int i = 0; i < nums1.length; i++) {
            result[i] = map.getOrDefault(nums1[i], -1);
        }
        return result;
    }

    public static int[] nextGreaterElements(int[] nums) {

        Stack<Integer> stack = new Stack<>();
        int[] result = new int[nums.length];
        Arrays.fill(result, -1);

        for (int i = 0; i < 2 * nums.length; i++) {
            int nextIndex = i % nums.length;
            while (!stack.isEmpty() && nums[nextIndex] > nums[stack.peek()]) {
                result[stack.pop()] = nums[nextIndex];
            }
            stack.push(nextIndex);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};
        int[] nums3 = {5, 4, 3, 2, 1};
        int[] nums = nextGreaterElement(nums1, nums2);
        int[] nums0 = nextGreaterElements(nums3);
        for (int num : nums0) {
            System.out.print(num + " ");
        }
    }
}
