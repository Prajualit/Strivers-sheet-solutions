import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class StockSpanner {

    Stack<int[]> stack = new Stack<>();
    int index = -1;

    public StockSpanner() {
        index = -1;
        stack.clear();
    }

    public int next(int price) {
        index += 1;
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            stack.pop();
        }
        int ans = index - (stack.isEmpty() ? -1 : stack.peek()[1]);
        stack.push(new int[]{price, index});
        return ans;
    }
}
