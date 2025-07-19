import java.util.ArrayList;

public class Leaders {

    public static ArrayList<Integer> leaders(int[] nums) {

        ArrayList<Integer> result = new ArrayList<>();

        int pivotIndex = 0;
        boolean isLeader = false;
        for (int i = 0; i < nums.length; i++) {
            for (int j = pivotIndex; j < nums.length; j++) {
                if (nums[pivotIndex] < nums[j]) {
                    isLeader = false;
                    break;
                } else {
                    isLeader = true;
                }
            }
            if (isLeader) {
                result.add(nums[pivotIndex]);
            }
            pivotIndex++;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-3, 4, 5, 1, -4, -5};
        System.out.println(leaders(nums));
    }
}
