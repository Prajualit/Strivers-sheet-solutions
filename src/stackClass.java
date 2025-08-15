import java.util.LinkedList;
import java.util.List;

public class stackClass {
    static class MyStack {
        static List<Integer> list = new LinkedList<>();

        public MyStack() {

        }

        public void push(int x) {
            list.addLast(x);
        }

        public int pop() {
            if (list.isEmpty()) {
                return -1;
            }
            return list.removeLast();
        }

        public int top() {
            return list.getLast();
        }

        public boolean empty() {
            return list.isEmpty();
        }
    }

    public static void main(String[] args) {

    }
}
