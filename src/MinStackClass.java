import java.util.Stack;

public class MinStackClass {
    static class MinStack {
        static Stack<Integer> stack = new Stack<>();
        static Stack<Integer> minStack = new Stack<>();

        public MinStack() {

        }

        public void push(int val) {
            stack.push(val);
            if (minStack.isEmpty() || val <= minStack.peek()) {
                minStack.push(val);
            }
        }

        public void pop() {
            if (stack.peek().equals(minStack.peek())) {
                minStack.pop();
            }
            stack.pop();

        }

        public int top() {
            return stack.isEmpty() ? -1 : stack.peek();
        }

        public int getMin() {
            return minStack.isEmpty() ? -1 : minStack.peek();
        }
    }

    public static void main(String[] args) {

    }
}
