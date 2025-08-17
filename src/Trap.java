public class Trap {
    public static int trap(int[] height) {

        int totalWaterStored = 0;

        int[] leftMaxes = new int[height.length];
        int[] rightMaxes = new int[height.length];


        int currentPeak = 0;
        for (int i = 0; i < height.length; i++) {
            if (currentPeak <= height[i]) {
                currentPeak = height[i];
            }
            leftMaxes[i] = currentPeak;
        }
        currentPeak = 0;
        for (int i = height.length - 1; i >= 0; i--) {
            if (currentPeak <= height[i]) {
                currentPeak = height[i];
            }
            rightMaxes[i] = currentPeak;
        }

        for (int i = 0; i < height.length; i++) {
            totalWaterStored += Math.min(leftMaxes[i], rightMaxes[i]) - height[i];
        }

        return totalWaterStored;
    }

    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] height1 = {4, 2, 0, 3, 2, 5};
        System.out.println(trap(height1));
    }
}
