import java.util.HashMap;
import java.util.Map;

public class FrequencySort {

    public static String frequencySort(String s) {

        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
            } else {
                map.put(s.charAt(i), 1);
            }
        }

        System.out.println(map);

        int maxValue = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() > maxValue) {
                maxValue = entry.getValue();
            }
        }
        System.out.println(maxValue);

        StringBuilder resultBuilder = new StringBuilder();

        map.entrySet().stream()
                .sorted(Map.Entry.<Character, Integer>comparingByValue().reversed())
                .forEach(entry -> {
                    char character = entry.getKey();
                    int count = entry.getValue();
                    resultBuilder.append(String.valueOf(character).repeat(Math.max(0, count)));
                });

        return resultBuilder.toString();
    }

    public static void main(String[] args) {
        String s = "tree";
        System.out.println(frequencySort(s));
    }
}
