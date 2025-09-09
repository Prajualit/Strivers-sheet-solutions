public class CanJump {

    public static boolean canJump(int[] nums) {
        int maxReach = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > maxReach) return false;
            maxReach = Math.max(maxReach, nums[i] + i);
        }
        return true;
    }

    public int jump(int[] nums) {
        int jumps = 0;
        int currentFarthest = 0;
        int nextFarthest = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            nextFarthest = Math.max(nextFarthest, i + nums[i]);

            if (i == currentFarthest) {
                jumps += 1;
                currentFarthest = nextFarthest;
            }


        }

        return jumps;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        int[] nums2 = {2, 3, 1, 0, 4};
        System.out.println(canJump(nums));
        System.out.println(canJump(nums2));

    }
}
