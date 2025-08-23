import java.util.Stack;

public class MaximalRectangle {

    public int maximalRectangle(char[][] matrix) {

        if (matrix == null || matrix.length == 0) return 0;

        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] heights = new int[cols];
        int maxArea = 0;

        for (char[] chars : matrix) {
            for (int c = 0; c < cols; c++) {
                if (chars[c] == '1') {
                    heights[c] += 1;
                } else {
                    heights[c] = 0;
                }
            }
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }

        return maxArea;
    }

    public static int largestRectangleArea(int[] heights) {
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

    }
}
