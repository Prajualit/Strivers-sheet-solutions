import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinations {

    public static List<String> letterCombinations(String digits) {

        Map<Integer, String> map = new HashMap<>();
        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");

        List<String> result = new ArrayList<>();

        findCombo(map, result, new StringBuilder(), digits, 0);

        return result;
    }

    public static void findCombo(Map<Integer, String> map, List<String> result, StringBuilder currentCombo, String digits, int index) {
        if (digits.isEmpty()) {
            return;
        }
        if (currentCombo.length() == digits.length()) {
            result.add(currentCombo.toString());
        }
        for (int i = index; i < digits.length(); i++) {
            int key = Integer.parseInt(String.valueOf(digits.charAt(i)));
            for (int j = 0; j < map.get(key).length(); j++) {
                currentCombo.append(map.get(key).charAt(j));
                findCombo(map, result, currentCombo, digits, i + 1);
                currentCombo.deleteCharAt(currentCombo.length() - 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(letterCombinations("23"));
    }
}
