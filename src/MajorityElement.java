public class MajorityElement {

    public static int majorityElement(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            int freq = 0;
            for (int j = 0; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    freq++;
                }
                if (freq > nums.length / 2) {
                    return nums[i];
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {

        int[] nums = {3, 2, 3};
        System.out.println(majorityElement(nums));
    }
}
