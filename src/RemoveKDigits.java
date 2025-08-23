import java.util.Stack;

public class RemoveKDigits {

    public static String removeKdigits(String num, int k) {

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < num.length(); i++) {
            while (!stack.isEmpty() && k > 0 && num.charAt(i) - '0' < stack.peek() - '0') {
                stack.pop();
                k -= 1;
            }
            stack.push(num.charAt(i));
        }
        while (k > 0) {
            stack.pop();
            k--;
        }
        if (stack.isEmpty()) return "0";

        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        result.reverse();
        while (!result.isEmpty() && result.charAt(0) == '0') {
            result.deleteCharAt(0);
        }

        if (result.isEmpty()) return "0";

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(removeKdigits("1432219", 3));
        System.out.println(removeKdigits("10200", 1));
        System.out.println(removeKdigits("10", 2));
    }
}
