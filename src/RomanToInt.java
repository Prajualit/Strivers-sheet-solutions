import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class RomanToInt {

    public static int romanToInt(String s) {
        Map<Character, Integer> romanValues = new HashMap<>();
        romanValues.put('I', 1);
        romanValues.put('V', 5);
        romanValues.put('X', 10);
        romanValues.put('L', 50);
        romanValues.put('C', 100);
        romanValues.put('D', 500);
        romanValues.put('M', 1000);

        int total = 0;

        for (int i = 0; i < s.length() - 1; i++) {
            int currentValue = romanValues.get(s.charAt(i));
            int nextValue = romanValues.get(s.charAt(i + 1));

            if (currentValue < nextValue) {
                total -= currentValue;
            } else {
                total += currentValue;
            }
        }
        total += romanValues.get(s.charAt(s.length() - 1));
        return total;
    }

    public static String intToRoman(int num) {
        // This TreeMap setup is perfect!
        Map<Integer, String> romanMap = new TreeMap<>(Collections.reverseOrder());
        romanMap.put(1, "I");
        romanMap.put(4, "IV");
        romanMap.put(5, "V");
        romanMap.put(9, "IX");
        romanMap.put(10, "X");
        romanMap.put(40, "XL");
        romanMap.put(50, "L");
        romanMap.put(90, "XC");
        romanMap.put(100, "C");
        romanMap.put(400, "CD");
        romanMap.put(500, "D");
        romanMap.put(900, "CM");
        romanMap.put(1000, "M");

        StringBuilder result = new StringBuilder();

        for (Map.Entry<Integer, String> entry : romanMap.entrySet()) {
            int value = entry.getKey();
            String symbol = entry.getValue();

            while (num >= value) {
                result.append(symbol);
                num -= value;
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String s = "MCMXCIV";
        System.out.println("Roman 'MCMXCIV' is: " + romanToInt(s)); // Expected: 1994

        int n = 58;
        System.out.println("Integer 58 is: " + intToRoman(n)); // Expected: LVIII
    }
}