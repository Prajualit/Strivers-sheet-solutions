import java.util.Stack;

public class LargestRectangleArea {

    //    brute force
    public static int largestRectangleArea(int[] heights) {

        int largest = 0;

        for (int i = 0; i < heights.length; i++) {
            int minimum = Integer.MAX_VALUE;
            for (int j = i; j < heights.length; j++) {
                if (minimum > heights[j]) {
                    minimum = heights[j];
                }
                int height = Math.min(heights[i], minimum);
                int width = j - i + 1;
                if (height * width > largest) {
                    largest = height * width;
                }
            }
        }
        return largest;
    }

    //    using stacks
    public static int largestRectangleArea1(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;

        for (int i = 0; i <= heights.length; i++) {
            int h = (i == heights.length ? 0 : heights[i]);
            while (!stack.isEmpty() && h < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }
        return maxArea;
    }


    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};
        int[] heights1 = {2, 2};
        System.out.println(largestRectangleArea(heights));
        System.out.println(largestRectangleArea(heights1));
        System.out.println(largestRectangleArea1(heights));
        System.out.println(largestRectangleArea1(heights1));
    }
}
