import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AsteroidCollision {

    public static int[] asteroidCollision(int[] asteroids) {

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < asteroids.length; i++) {
            boolean flag = true;
            while (!stack.isEmpty() && stack.peek() > 0 && asteroids[i] < 0) {
                if (Math.abs(stack.peek()) < Math.abs(asteroids[i])) {
                    stack.pop();
                    continue;
                } else if (Math.abs(stack.peek()) == Math.abs(asteroids[i])) {
                    stack.pop();
                }
                flag = false;
                break;
            }
            if (flag) {
                stack.push(asteroids[i]);
            }
        }
        System.out.println(stack);
        int[] result = new int[stack.size()];

        for (int i = 0; i < result.length; i++) {
            if (!stack.isEmpty()) {
                result[result.length - i - 1] = stack.pop();
            }
        }
        System.out.println(stack);

        return result;
    }

    public static void main(String[] args) {
        int[] asteroids = {5, 10, -5};
        int[] asteroids1 = {8, -8};
        int[] asteroids2 = {10, 2, -5};
        int[] result = asteroidCollision(asteroids);
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}
