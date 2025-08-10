import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenerateParentheses {

    public List<String> generateParenthesis(int n) {

        List<String> result = new ArrayList<>();

        backTrack(result, new StringBuilder(), 0, 0, n);

        return result;
    }

    public static void backTrack(List<String> result, StringBuilder currentString, int openCount, int closedCount, int n) {
        if (currentString.length() == n * 2) {
            result.add(currentString.toString());
            return;
        }
        if (openCount < n) {
            currentString.append("(");
            backTrack(result, currentString, openCount + 1, closedCount, n);
            currentString.deleteCharAt(currentString.length() - 1);
        }
        if (closedCount < openCount) {
            currentString.append(")");
            backTrack(result, currentString, openCount, closedCount + 1, n);
            currentString.deleteCharAt(currentString.length() - 1);
        }
    }


    public static void main(String[] args) {

    }

}
