import java.lang.runtime.ObjectMethods;
import java.util.*;

public class RemoveOuterParentheses {

    public static String removeOuterParentheses(String s) {
        StringBuilder resultBuilder = new StringBuilder();
        int balance = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                if (balance > 0) {
                    resultBuilder.append(c);
                }
                balance++;
            } else {
                balance--;
                if (balance > 0) {
                    resultBuilder.append(c);
                }
            }
        }

        return resultBuilder.toString();
    }

    public static void main(String[] args) {
        String s = "(()())(())";
        System.out.println(removeOuterParentheses(s));
    }

}
